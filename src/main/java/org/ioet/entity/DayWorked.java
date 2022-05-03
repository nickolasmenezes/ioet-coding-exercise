package org.ioet.entity;

import java.time.LocalTime;

public class DayWorked {
    private String day;
    private LocalTime startTime;
    private LocalTime endTime;

    public DayWorked (String dayWorked){
        this.day = dayWorked.substring(0,2);
        this.startTime = LocalTime.parse(dayWorked.substring(2,7));
        this.endTime = LocalTime.parse(dayWorked.substring(8,13));
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }


}
