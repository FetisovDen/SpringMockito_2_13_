package pro.sky.SpringMockito_2_13.Employees;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    @GetMapping("/add")
    public Map<String, Employee> add(@RequestParam("fullName") String fullName,
                                     @RequestParam("salary") double salary,
                                     @RequestParam("department") int department) {
        return employeeService.addEmployee(fullName, salary, department);
    }

    @GetMapping("/remove")
    public String remove(@RequestParam("fullname") String fullname) {
        return employeeService.removeEmployee(fullname);
    }

    @GetMapping("/find")
    public String find(@RequestParam("fullname") String fullname) {
        return employeeService.containsEmployee(fullname);
    }

    @GetMapping("/findAll")
    public List<Employee> find() {
        return employeeService.findAll();
    }

}


