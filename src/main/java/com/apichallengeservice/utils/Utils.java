package com.apichallengeservice.utils;

import lombok.AllArgsConstructor;

import java.time.LocalDate;
@AllArgsConstructor
public class Utils {
    public static boolean dateComparator(LocalDate startDate, LocalDate endDate ){
        return endDate.isAfter(startDate);
    }
}
