# Ioet Coding Exercise
@Author: Nickolas Menezes da Silva

The goal of this exercise is to calculate the total that the company has to pay an employee, based on the hours they worked and the times during which they worked.

**About implementation:**

It was used a maven archetype with Junit for the tests.
The entity package contains the model (Employee and DayWorked), 
an Employee has a DayWorked collection, implementing using Set.
The package Service is used to control the operations and 
has the PaymentDayWorkedService providing the calculations and return the final message.

**How to run**

It's possible to pass the path for text files input.

`mvn compile exec:java -Dexec.mainClass="org.ioet.App" -Dexec.args="input.txt"`


**Unit tests**
`mvn test`

