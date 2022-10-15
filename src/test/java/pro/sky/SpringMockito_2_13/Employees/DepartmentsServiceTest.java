package pro.sky.SpringMockito_2_13.Employees;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.SpringMockito_2_13.exception.EmployeeNotFoundException;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;


@ExtendWith(MockitoExtension.class)
class DepartmentsServiceTest {


    @Mock
    private EmployeeService employeeService;


    @InjectMocks
    private DepartmentsService departmentsService;

    @BeforeEach
    public void setUp() {
        Mockito.when(employeeService.findAll()).thenReturn(List.of(
                        new Employee("Wer_D", 500, 5),
                        new Employee("Aer_E", 200, 5),
                        new Employee("Key_R", 300, 1)
                )
        );
    }
    @Test
    void testWorkMethodMaxSalaryByDepartment() {
        assertThat(departmentsService.maxSalaryByDepartment(5))
                .isEqualTo(new Employee("Wer_D", 500, 5));
        assertThatExceptionOfType(EmployeeNotFoundException.class).isThrownBy(()->departmentsService.maxSalaryByDepartment(3));
    }
    @Test
    void testWorkMethodMinSalaryByDepartment() {
        assertThat(departmentsService.minSalaryByDepartment(5))
                .isEqualTo(new Employee("Aer_E", 200, 5));
        assertThatExceptionOfType(EmployeeNotFoundException.class).isThrownBy(()->departmentsService.minSalaryByDepartment(3));
    }
    @Test
    void testWorkMethodAllOfDepartment() {
        assertThat(departmentsService.allOfDepartment(3))
                .containsExactlyInAnyOrderElementsOf(List.of(
                                new Employee("Aer_E", 200, 5),
                                new Employee("Wer_D", 500, 5)
                        )
                );
    }
    @Test
    void testWorkMethodSortedByDepartment() {
        assertThat(departmentsService.sortedByDepartment())
                .isEqualTo(List.of(
                        new Employee("Key_R", 300, 1),
                        new Employee("Wer_D", 500, 5),
                        new Employee("Aer_E", 200, 5)
                        )
                );
    }
}