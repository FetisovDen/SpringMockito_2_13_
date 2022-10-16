package pro.sky.SpringMockito_2_13.Employees;

import org.springframework.stereotype.Service;
import pro.sky.SpringMockito_2_13.exception.EmployeeNotFoundException;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DepartmentsService {
    private EmployeeService employeeService;

    public DepartmentsService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public Employee maxSalaryByDepartment(Integer department) {
        return employeeService.findAll().stream()
                .filter(employee -> employee.getDepartment() == department)
                .max(Comparator.comparingDouble(employee -> employee.getSalary()))
                .orElseThrow(EmployeeNotFoundException::new);
    }

    public Employee minSalaryByDepartment(Integer department) {
        return employeeService.findAll().stream()
                .filter(employee -> employee.getDepartment() == department)
                .min(Comparator.comparingDouble(employee -> employee.getSalary()))
                .orElseThrow(EmployeeNotFoundException::new);
    }

    public List<Employee> allOfDepartment(Integer department) {
        return employeeService.findAll().stream()
                .filter(employee -> employee.getDepartment() == department)
                .collect(Collectors.toList());
    }

    public List<Employee> sortedByDepartment() {
        return employeeService.findAll().stream()
                .sorted(Comparator.comparingInt(employee -> employee.getDepartment()))
                .collect(Collectors.toList());

    }
}