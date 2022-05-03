package org.ioet.entity;

import java.util.Set;

public class Employee {
    private String name;
    private Set<DayWorked> daysWorked;

    public Employee (String name, Set<DayWorked> daysWorked){
        this.name = name;
        this.daysWorked = daysWorked;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<DayWorked> getDaysWorked() {
        return daysWorked;
    }

    public void setDaysWorked(Set<DayWorked> daysWorked) {
        this.daysWorked = daysWorked;
    }
}
