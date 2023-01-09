package com.rpr.soldierscrm.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SearchDto {

    String fullName;
    String vacation;
    String hospital;
    Integer dateOfBirth;
    String phoneNumber;
    String battalion;
    String fullTimePosition;
    String militaryRankName;
    String personalIdNumber;
    String dateOfArrival;

    public Date formatDateOfArrival() throws ParseException {
        if (dateOfArrival == null) return null;
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date date = df.parse(dateOfArrival);
        return date;
    }
}
