package com.rpr.soldierscrm.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "soldiers")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Soldier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String fullName; // Прізвище Імя По батькові
    @Column
    private String vacation; // Відпустка
    @Column
    private String hospital; // Госпіталь
    @Column
    private Integer dateOfBirth; // Рік народження
    @Column
    private String phoneNumber; // Номер телефона
    @Column
    private String battalion; // Корпус БАТ
    @Column
    private String fullTimePosition; // Посада за штатним розкладом
    @Column
    private String militaryRankName;  // Військове звання фактично
    @Column
    @Enumerated(EnumType.STRING)
    private MilitaryMedicalCommission militaryMedicalCommission;  // влк
    @Column
    private String personalIdNumber;  // Особистий номер
    @Column
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateOfArrival;  // Прибув дата
    @Column
    private String enrollmentOrderNumber;  // Номер Наказу на зарахування
    @Column
    private String originBrigadeArrival;  // з якої бригади прибув
    @Column
    private String internalOrder;  // Внутрінній наказ
}
