package service;

import model.*;

import java.math.BigDecimal;

public class EmployeeService {

    private Employee employee;

    public EmployeeService(Employee employee) {
        this.employee = employee;
    }

    public EmployeesReport getEmployeesReport() {
        return new EmployeesReport(
                employee.getName(),
                employee.getCpf(),
                employee.getEmail(),
                employee.getBaseSalary(),
                employee.getEmployeeRole(),
                employee.getPerformanceRate()
        );
    }

}
