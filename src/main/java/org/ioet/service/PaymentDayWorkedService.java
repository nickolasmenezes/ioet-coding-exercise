package org.ioet.service;

import org.ioet.entity.DayWorked;
import org.ioet.entity.Employee;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Set;

public class PaymentDayWorkedService {


    public String calculateTotalAmountByEmployee(Employee employee){
        StringBuilder message = new StringBuilder();
        int totalAmountOfWeek = calculateTotalAmountByEmployee(employee.getDaysWorked());
        return message.append("The amount to pay ")
                .append(employee.getName()).append(" is: ")
                    .append(totalAmountOfWeek).append(" USD").toString();
    }

    private int calculateTotalAmountByEmployee(Set<DayWorked> daysWorked){
        int totalAmountWeek = 0;
        for(DayWorked dayWorked: daysWorked){
            totalAmountWeek+= this.calculateDayPayment(dayWorked);
        }
        return totalAmountWeek;
    }

    public int calculateDayPayment(DayWorked dayWorked) {
        int totalAmount = 0;

        boolean endOfTheDayWorked = false;
        LocalTime currentHour = dayWorked.getStartTime();
        while (!endOfTheDayWorked){
            if (currentHour.isBefore(dayWorked.getEndTime())){
                totalAmount += getAmountPerDayAndHour(currentHour, dayWorked.getDay());
                currentHour = currentHour.plus(1, ChronoUnit.HOURS);
            } else {
                endOfTheDayWorked = true;
            }
        }

        return totalAmount;
    }

    public int getAmountPerDayAndHour(LocalTime hour, String day) {
        if ((hour.isAfter(LocalTime.parse("00:00")) && hour.isBefore(LocalTime.parse("09:00"))) || hour.equals(LocalTime.parse("00:00"))){
            return isWeekend(day) ? 30 : 25;
        }
        if (hour.isAfter(LocalTime.parse("08:59")) && hour.isBefore(LocalTime.parse("18:00"))) {
            return isWeekend(day) ? 20 : 15;
        }
        if (hour.isAfter(LocalTime.parse("17:59")) && hour.isBefore(LocalTime.parse("23:59"))) {
            return isWeekend(day) ? 25 : 20;
        }
        return 0;
    }

    public boolean isWeekend(String day) {
        if (day.equals("SA") || day.equals("SU")) {
            return true;
        }
        return false;
    }
}
