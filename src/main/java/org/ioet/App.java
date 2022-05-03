package org.ioet;

import org.ioet.entity.DayWorked;
import org.ioet.entity.Employee;
import org.ioet.service.PaymentDayWorkedService;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashSet;
import java.util.Set;

/**
 * IOET Main Class
 *
 */
public class App
{


    public static void main( String[] args )
    {
        BufferedReader inputFile;
        try {
            if (null != args[0]){
                inputFile = new BufferedReader(new FileReader(args[0]));
                try (BufferedReader br = inputFile) {
                    String row;
                    while ((row = br.readLine()) != null) {
                        try {
                            parseRowAndPrintAmountToPay(row);
                        } catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                }
            } else {
                throw new Exception("It looks like you forgot to pass the correct file");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void parseRowAndPrintAmountToPay(String row) {
        try {
            String employeeAndWorkedDays[] = row.split("=");
            if (null != employeeAndWorkedDays && employeeAndWorkedDays.length > 1) {
                String employeeName = employeeAndWorkedDays[0];
                String[] daysWorked = employeeAndWorkedDays[1].split(",");

                Set<DayWorked> daysWorkedSet = new HashSet<>();
                for (String dayWorked : daysWorked) {
                    daysWorkedSet.add(new DayWorked(dayWorked));
                }

                Employee employee = new Employee(employeeName, daysWorkedSet);
                System.out.println(new PaymentDayWorkedService().calculateTotalAmountByEmployee(employee));
            } else {
                throw new Exception(new StringBuilder().append("Error to calculate the amount to pay in the line -> ").append(row).toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
