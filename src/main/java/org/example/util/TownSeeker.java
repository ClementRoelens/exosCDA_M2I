package org.example.util;

import org.example.exceptions.NotFoundException;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TownSeeker {
    private static final List<String> towns = Arrays.asList(
            "Paris",
            "Budapest",
            "Skopje",
            "Rotterdam",
            "Valence",
            "Vancouver",
            "Amsterdam",
            "Vienne",
            "Sydney",
            "New York",
            "Londres",
            "Bangkok",
            "Hong Kong",
            "Dubaï",
            "Rome",
            "Istanbul");

    public static List<String> seek(String seekedValue) {
        if (seekedValue.equals("*")){
            return towns;
        }
        if (seekedValue.length() < 2){
            throw new NotFoundException();
        }
        return towns.stream().filter(t -> t.toLowerCase().contains(seekedValue.toLowerCase())).collect(Collectors.toList());
    }
}
