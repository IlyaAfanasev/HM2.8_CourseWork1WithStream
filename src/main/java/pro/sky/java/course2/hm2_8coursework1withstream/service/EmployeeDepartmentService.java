package pro.sky.java.course2.hm2_8coursework1withstream.service;

import pro.sky.java.course2.hm2_8coursework1withstream.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeDepartmentService {
    Optional<Employee> maxSalaryDepartment(int department);

    Optional<Employee> minSalaryDepartment(int department);

    List<Employee> printDepartmentEmployees(int department);

    List<Employee> printEmployeesByDepartments();

}

