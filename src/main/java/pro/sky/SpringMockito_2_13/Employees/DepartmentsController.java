package pro.sky.SpringMockito_2_13.Employees;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/departments")
public class DepartmentsController {
    private final DepartmentsService departmentsService;

    public DepartmentsController(DepartmentsService departmentsService) {
        this.departmentsService = departmentsService;
    }


    @GetMapping("/max-salary")
    public String max_salary(@RequestParam("departmentId") int department) {
        return String.valueOf(departmentsService.maxSalaryByDepartment(department));
    }

    @GetMapping("/min-salary")
    public String min_salary(@RequestParam("departmentId") int department) {
        return String.valueOf(departmentsService.minSalaryByDepartment(department));
    }

    @GetMapping(value = "/all", params = "departmentId")
    public String allOfDepartment(@RequestParam("departmentId") int department) {
        return String.valueOf(departmentsService.allOfDepartment(department));
    }

    @GetMapping("/all")
    public String allSortedByDepartment() {
        return String.valueOf(departmentsService.sortedByDepartment());
    }


}
