package org.example.util;

public class YearUtil {
    public static boolean isLeap(int year){
        return (year%4 != 0) || ((year%4 == 0) && (year%100 != 0));
    }
}
