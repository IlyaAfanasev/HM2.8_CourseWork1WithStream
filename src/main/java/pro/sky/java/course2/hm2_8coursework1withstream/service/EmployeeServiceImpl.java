package pro.sky.java.course2.hm2_8coursework1withstream.service;


import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import pro.sky.java.course2.hm2_8coursework1withstream.Employee;
import pro.sky.java.course2.hm2_8coursework1withstream.Exception.EmployeeAlreadyAdded;
import pro.sky.java.course2.hm2_8coursework1withstream.Exception.EmployeeNotFound;

import java.util.*;
import java.util.stream.Collectors;

import static org.apache.commons.lang3.StringUtils.*;

@Service
public class EmployeeServiceImpl implements EmployeeService, EmployeeDepartmentService {
    public final Map<String, Employee> employeesMap;

    public EmployeeServiceImpl() {
        this.employeesMap = new HashMap<>();
    }


    @Override
    public Employee add(String lastName, String firstName, int department, double salary) {
        lastName = checkAndChangeString(lastName);
        firstName = checkAndChangeString(firstName);
        Employee employee = new Employee(lastName, firstName, department, salary);


        if (employeesMap.containsKey(employee.getFullName())) {
            throw new EmployeeAlreadyAdded("Сотрудник " + lastName + " " + firstName + " уже внесен в реестр");
        }
        employeesMap.put(employee.getFullName(), employee);

        return employee;
    }

    @Override
    public Employee remove(String lastName, String firstName) {
        lastName = checkAndChangeString(lastName);
        firstName = checkAndChangeString(firstName);

        Employee employee = new Employee(lastName, firstName);
        if (employeesMap.containsKey(employee.getFullName())) {
            return employeesMap.remove(employee.getFullName());
        }
        throw new EmployeeNotFound("Сотрудник не найден");
    }

    @Override
    public Employee find(String lastName, String firstName) {
        lastName = checkAndChangeString(lastName);
        firstName = checkAndChangeString(firstName);

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
        return employeesMap.values()
                .stream()
                .filter(e -> e.getDepartment() == department)
                .max(Comparator.comparingDouble(Employee::getSalary));

    }

    @Override
    public Optional<Employee> minSalaryDepartment(int department) {
        return employeesMap.values()
                .stream()
                .filter(e -> e.getDepartment() == department)
                .min(Comparator.comparingDouble(Employee::getSalary));
    }

    @Override
    public List<Employee> getDepartmentEmployees(int department) {
        return employeesMap.values().stream().filter(e -> e.getDepartment() == department)
                .collect(Collectors.toList());
    }

    @Override
    public Map<Integer, List<Employee>> getEmployeesByDepartments() {

        return employeesMap.values()
                .stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));
    }

    private static String checkAndChangeString(String name) {
        name = deleteWhitespace(name);

        if (!isAlpha(name)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        name = capitalize(name.toLowerCase());

        return name;
    }
}