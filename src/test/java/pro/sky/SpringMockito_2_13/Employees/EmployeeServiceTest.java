package pro.sky.SpringMockito_2_13.Employees;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.SpringMockito_2_13.exception.BadRequestException;
import pro.sky.SpringMockito_2_13.exception.EmployeeAlreadyAddedException;
import pro.sky.SpringMockito_2_13.exception.EmployeeNotFoundException;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {
    @InjectMocks
    private EmployeeService employeeService;

    @ParameterizedTest
    @CsvSource({"Dasha_M,200,3", "Kira_Z,100,5", "Naitli_M,11,5"})
    void testAddEmployee(String fullname, double salary, int department) {
        Map<String, Employee> employeesBook = employeeService.addEmployee(fullname, salary, department);
        assertEquals(employeesBook, Map.of(fullname, new Employee(fullname, salary, department)));
    }

    @ParameterizedTest
    @CsvSource({"Dasha_M,200,3"})
    void testAddEmployeeAlreadyAdded(String fullname, double salary, int department) {
        employeeService.addEmployee(fullname, salary, department);
        assertThrows(EmployeeAlreadyAddedException.class, () -> {
            employeeService.addEmployee(fullname, salary, department);
        });
    }

    @ParameterizedTest
    @CsvSource({"Dasha_M.,200,3"})
    void testAddEmployeeWithIncorrectValue(String fullname, double salary, int department) {
        assertThrows(BadRequestException.class, () -> {
            employeeService.addEmployee(fullname, salary, department);
        });
    }

    @ParameterizedTest
    @CsvSource({"Dasha,200,3", "D__,200,3", "Dasha Setchina,200,3", "D,200,3"})
    void testAddEmployeeWithIncorrectFormFullname(String fullname, double salary, int department) {
        assertThrows(BadRequestException.class, () -> {
            employeeService.addEmployee(fullname, salary, department);
        });
    }

    @ParameterizedTest
    @CsvSource({"daSHa_ponCHikova,200,3"})
    void testAddEmployeeWithCorrectFormFullname(String fullname, double salary, int department) {
        Map<String, Employee> employeesBook = employeeService.addEmployee(fullname, salary, department);
        assertEquals(employeesBook, Map.of("Dasha_Ponchikova", new Employee("Dasha_Ponchikova", 200, 3)));
    }


    @ParameterizedTest
    @CsvSource({"Dasha_M,200,3", "Kira_Z,100,5", "Portman_M,11,5"})
    void testRemoveEmployee(String fullname, double salary, int department) {
        Map<String, Employee> employeesBook = employeeService.addEmployee(fullname, salary, department);
        employeeService.removeEmployee(fullname);
        assertEquals(employeesBook, new HashMap<>());
    }

    @ParameterizedTest
    @CsvSource({"Dasha_M,200,3", "Kira_Z,100,5", "Portman_M,11,5"})
    void testRemoveNotFoundedEmployee(String fullname, double salary, int department) {
        assertThrows(EmployeeNotFoundException.class, () -> {
            employeeService.removeEmployee(fullname);
        });
    }

    @ParameterizedTest
    @CsvSource({"Dasha_M,200,3", "Kira_Z,100,5", "Portman_M,11,5"})
    void testFindEmployeeInBook(String fullname, double salary, int department) {
        employeeService.addEmployee(fullname, salary, department);
        employeeService.addEmployee("Serg_D", 500, 1);
        assertEquals(employeeService.containsEmployee(fullname), employeeService.employeesBook.get(fullname).toString());
        assertThrows(EmployeeNotFoundException.class, () -> {
            employeeService.containsEmployee("Serg_Dod");
        });
    }


    @ParameterizedTest
    @CsvSource({"Dasha_M,200,3", "Kira_Z,100,5", "Portman_M,11,5"})
    void testFindAllEmployee(String fullname, double salary, int department) {
        employeeService.addEmployee(fullname, salary, department);
        employeeService.addEmployee("Serg_D", 500, 1);
        assertEquals(employeeService.findAll(),
                Map.of(fullname, new Employee(fullname, salary, department), "Serg_D", new Employee("Serg_D", 500, 1)));

    }
}