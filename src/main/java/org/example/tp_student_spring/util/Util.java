package org.example.tp_student_spring.util;

import java.time.LocalDate;

public class Util {
    public static int getAgeFromDate(LocalDate date) {
        int yearDifference = LocalDate.now().getYear() - date.getYear();
        if ((LocalDate.now().getMonthValue() > date.getMonthValue())
                ||
                (LocalDate.now().getMonthValue() >= date.getMonthValue()) && (LocalDate.now().getDayOfMonth() >= date.getDayOfMonth())
        ) {
            return yearDifference;
        }
        return yearDifference-1;
    }
}
