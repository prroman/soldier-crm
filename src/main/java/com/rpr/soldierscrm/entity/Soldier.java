package com.rpr.soldierscrm.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class Soldier {

    private Long id;

    private String fullName; // Прізвище Імя По батькові

    private String vacation; // Відпустка

    private String hospital; // Госпіталь

    private Integer dateOfBirth; // Рік народження

    private String phoneNumber; // Номер телефона

    private String battalion; // Корпус БАТ

    private String fullTimePosition; // Посада за штатним розкладом

    private String militaryRankName;  // Військове звання фактично

    private MilitaryMedicalCommission militaryMedicalCommission;  // влк

    private String personalIdNumber;  // Особистий номер

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateOfArrival;  // Прибув дата

    private String enrollmentOrderNumber;  // Номер Наказу на зарахування

    private String originBrigadeArrival;  // з якої бригади прибув

    private String internalOrder;  // Внутрінній наказ
}
