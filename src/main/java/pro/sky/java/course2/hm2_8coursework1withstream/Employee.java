package pro.sky.java.course2.hm2_8coursework1withstream;

import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

import static org.apache.commons.lang3.StringUtils.*;

public class Employee {
    private final String lastName, firstName;
    private double salary;
    private int department;

    public Employee(String lastName, String firstName, int department, double salary) {
        this.lastName = capitalize(deleteWhitespace(lastName));
        this.firstName = capitalize(deleteWhitespace(firstName));
        this.department = department;
        this.salary = salary;
    }

    public Employee(String lastName, String firstName) {
        this.lastName = capitalize(deleteWhitespace(lastName));
        this.firstName = capitalize(deleteWhitespace(firstName));
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public int getDepartment() {
        return this.department;
    }

    public void setDepartment(int department) {
        this.department = department;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getFullName() {
        return lastName + " " + firstName;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return lastName.equals(employee.lastName) && firstName.equals(employee.firstName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lastName, firstName);
    }

    @Override
    public String toString() {
        return "Employee{" + "lastName='" + lastName + '\'' + ", firstName='" + firstName + '\'' + '}';
    }
}