package com.Java8.LambdaTest;

import com.Java8.Employee;

public class FilterEmployeeByAge implements MyPredict<Employee> {
    @Override
    public boolean test(Employee employee) {
        return employee.getAge() >= 30;
    }
}
