package pro.sky.java.course2.hm2_8coursework1withstream.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.java.course2.hm2_8coursework1withstream.Employee;
import pro.sky.java.course2.hm2_8coursework1withstream.service.EmployeeDepartmentService;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

@RestController
@RequestMapping("/departments")
public class EmployeeDepartmentController {
    private final EmployeeDepartmentService employeeDepartmentService;

    public EmployeeDepartmentController(EmployeeDepartmentService employeeDepartmentService) {
        this.employeeDepartmentService = employeeDepartmentService;
    }

    @GetMapping("/max-salary")
    public Optional<Employee> maxSalaryDepartment(@RequestParam("department") int department) {
        return employeeDepartmentService.maxSalaryDepartment(department);
    }

    @GetMapping("/min-salary")
    public Optional<Employee> minSalaryDepartment(@RequestParam("department") int department) {
        return employeeDepartmentService.minSalaryDepartment(department);
    }

    @GetMapping(value = "/all", params = {"department"})
    public List<Employee> getDepartmentEmployees(@RequestParam("department") int department) {
        return employeeDepartmentService.getDepartmentEmployees(department);

    }

    @GetMapping("/all")
    public Map<Integer, List<Employee>> getEmployeesByDepartments() {
        return employeeDepartmentService.getEmployeesByDepartments();
    }


}
