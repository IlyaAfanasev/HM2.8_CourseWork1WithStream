package pro.sky.java.course2.hm2_8coursework1withstream.service;



import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private int maxsize = 5;
    private final List<Employee> employees;

    public EmployeeServiceImpl() {
        this.employees = new ArrayList<>();
    }

    @Override
    public Employee add(String lastName, String firstName) {

        Employee employee = new Employee(lastName, firstName);

        if (employees.size() >= maxsize) {
            throw new ArrayIsFull("В реестре больше нет места");
        } else if (employees.contains(employee)) {
            throw new EmployeeAlreadyAdded("Сотрудник " + lastName + " " + firstName + " уже внесен в реестр");
        } else {
            employees.add(employee);
        }
        return employee;
    }

    @Override
    public Employee remove(String lastName, String firstName) {
        Employee employee = new Employee(lastName, firstName);
        if (!employees.contains(employee)) {
            throw new EmployeeNotFound("Сотрудник не найден");
        }
        employees.remove(employee);
        return employee;

    }

    @Override
    public Employee find(String lastName, String firstName) {
        Employee employee = new Employee(lastName, firstName);

        if (!employees.contains(employee)) {
            throw new EmployeeNotFound("Сотрудник не найден");
        }
        return employee;
    }

    @Override
    public Collection<Employee> print() {
        return Collections.unmodifiableList(employees);
    }

}
