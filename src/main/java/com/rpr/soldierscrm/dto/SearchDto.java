package com.rpr.soldierscrm.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

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
    Date dateOfArrival;

}
