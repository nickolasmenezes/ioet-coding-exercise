package org.ioet.service;

import org.ioet.entity.DayWorked;
import org.ioet.entity.Employee;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.HashSet;
import java.util.Set;

public class PaymentDayWorkedServiceTest {

   private Employee employeeTest;
   private PaymentDayWorkedService paymentDayWorkedService;

    @Before
    public void init() {
        Set<DayWorked> daysWorked = new HashSet<DayWorked>();
        daysWorked.add(new DayWorked("MO10:00-12:00"));
        daysWorked.add(new DayWorked("TH12:00-14:00"));
        daysWorked.add(new DayWorked("SU20:00-21:00"));
        employeeTest = new Employee("Employee 1", daysWorked);
        paymentDayWorkedService = new PaymentDayWorkedService();

    }

    @Test
    public void calculateTotalAmountByEmployeeWhenHasAValidEmployee(){
        final String expectedResult = "The amount to pay Employee 1 is: 85 USD";

        String result = paymentDayWorkedService.calculateTotalAmountByEmployee(employeeTest);

        Assert.assertEquals(expectedResult, result);
    }

    @Test
    public void calculateTotalAmountByEmployeeWithEmptyDayWorked(){
        final String expectedResult = "The amount to pay Employee 1 is: 0 USD";
        employeeTest.setDaysWorked(new HashSet<>());

        String result = paymentDayWorkedService.calculateTotalAmountByEmployee(employeeTest);

        Assert.assertEquals(expectedResult, result);
    }

    @Test
    public void calculateDayPaymentWhenIsAValidWorkedDay() {
        final String dayWorkedString = "SU20:00-21:00";
        final int expectedResult = 25;
        DayWorked dayWorkedTest = new DayWorked(dayWorkedString);

        int result = paymentDayWorkedService.calculateDayPayment(dayWorkedTest);

        Assert.assertEquals(expectedResult, result);
    }

    @Test(expected = DateTimeParseException.class)
    public void calculateDayPaymentWhenIsNotAValidWorkedDay() {
        final String dayWorkedString = "WRONG_STRING";
        DayWorked dayWorkedTest = new DayWorked(dayWorkedString);

        paymentDayWorkedService.calculateDayPayment(dayWorkedTest);
    }

    @Test
    public void getAmountPerDayAndHourWithValidHourAndDay() {
        LocalTime hour = LocalTime.parse("07:59");
        String day = "MO";
        int expectedResult = 25;

        int result = paymentDayWorkedService.getAmountPerDayAndHour(hour, day);

        Assert.assertEquals(expectedResult, result);
    }

    @Test
    public void isWeekend() {
        String saturday = "SA";
        String sunday = "SU";
        boolean expectedResult = true;

        boolean result1 = paymentDayWorkedService.isWeekend(saturday);
        boolean result2 = paymentDayWorkedService.isWeekend(sunday);

        Assert.assertEquals(expectedResult, (result1 && result2));
    }

    @Test
    public void isNotWeekend() {
        String monday = "MO";
        String friday = "FR";
        boolean expectedResult = false;

        boolean result1 = paymentDayWorkedService.isWeekend(monday);
        boolean result2 = paymentDayWorkedService.isWeekend(friday);

        Assert.assertEquals(expectedResult, (result1 && result2));
    }
}
