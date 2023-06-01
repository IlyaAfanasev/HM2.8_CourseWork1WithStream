package pro.sky.java.course2.hm2_8coursework1withstream.service;


import org.springframework.stereotype.Service;
import pro.sky.java.course2.hm2_8coursework1withstream.Employee;
import pro.sky.java.course2.hm2_8coursework1withstream.Exception.EmployeeAlreadyAdded;
import pro.sky.java.course2.hm2_8coursework1withstream.Exception.EmployeeNotFound;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService, EmployeeDepartmentService {
    public final Map<String, Employee> employeesMap;

    public EmployeeServiceImpl() {
        this.employeesMap = new HashMap<>();
    }


    @Override
    public Employee add(String lastName, String firstName, int department, double salary) {

        Employee employee = new Employee(lastName, firstName, department, salary);


        if (employeesMap.containsKey(employee.getFullName())) {
            throw new EmployeeAlreadyAdded("Сотрудник " + lastName + " " + firstName + " уже внесен в реестр");
        }
        employeesMap.put(employee.getFullName(), employee);

        return employee;
    }

    @Override
    public Employee remove(String lastName, String firstName) {

        Employee employee = new Employee(lastName, firstName);
        if (employeesMap.containsKey(employee.getFullName())) {
            return employeesMap.remove(employee.getFullName());
        }
        throw new EmployeeNotFound("Сотрудник не найден");
    }

    @Override
    public Employee find(String lastName, String firstName) {

        Employee employee = new Employee(lastName, firstName);
        if (employeesMap.containsKey(employee.getFullName())) {
            return employeesMap.get(employee.getFullName());
        }
        throw new EmployeeNotFound("Сотрудник не найден");
    }

    @Override
    public Collection<Employee> print() {
        return Collections.unmodifiableCollection(employeesMap.values());
    }

    @Override
    public Optional<Employee> maxSalaryDepartment(int department) {
        return Optional.of(employeesMap.values().stream().filter(e -> e.getDepartment() == department).max((e1, e2) -> (int) (e1.getSalary() - e2.getSalary())).get());

    }

    @Override
    public Optional<Employee> minSalaryDepartment(int department) {
        return Optional.of(employeesMap.values().stream().filter(e -> e.getDepartment() == department).min((e1, e2) -> (int) (e1.getSalary() - e2.getSalary())).get());
    }

    @Override
    public List<Employee> printDepartmentEmployees(int department) {
        return employeesMap.values().stream().filter(e -> e.getDepartment() == department).collect(Collectors.toList());
    }

    @Override
    public List<Employee> printEmployeesByDepartments() {
        return employeesMap.values().stream().sorted(Comparator.comparingInt(Employee::getDepartment)).toList();
    }
}