package com.rpr.soldierscrm.repository;

import com.rpr.soldierscrm.entity.MilitaryMedicalCommission;
import com.rpr.soldierscrm.entity.Soldier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface SoldierRepository extends JpaRepository<Soldier, Long> {

    @Query("select u from Soldier u where (:fullName is null or u.fullName like %:fullName%) " +
            "and (:vacation is null or u.vacation like %:vacation%) " +
            "and (:hospital is null or u.hospital like %:hospital%) " +
            "and (:dateOfBirth is null or u.dateOfBirth like :dateOfBirth) " +
            "and (:phoneNumber is null or u.phoneNumber like %:phoneNumber%) " +
            "and (:battalion is null or u.battalion like %:battalion%) " +
            "and (:fullTimePosition is null or u.fullTimePosition like %:fullTimePosition%) " +
            "and (:militaryRankName is null or u.militaryRankName like %:militaryRankName%) " +
            "and (:militaryMedicalCommission is null or u.militaryMedicalCommission like :militaryMedicalCommission) " +
            "and (:personalIdNumber is null or u.personalIdNumber like %:personalIdNumber%) " +
            "and (:dateOfArrival is null or u.dateOfArrival = :dateOfArrival)")
    Page<Soldier> searchByAllParamsWithPagination(@Param("fullName") String fullName, @Param("vacation") String vacation,
                                                  @Param("hospital") String hospital, @Param("dateOfBirth") Integer dateOfBirth,
                                                  @Param("phoneNumber") String phoneNumber, @Param("battalion") String battalion,
                                                  @Param("fullTimePosition") String fullTimePosition, @Param("militaryRankName") String militaryRankName,
                                                  @Param("personalIdNumber") String personalIdNumber, @Param("dateOfArrival") Date dateOfArrival,
                                                  @Param("militaryMedicalCommission") MilitaryMedicalCommission militaryMedicalCommission,
                                                  Pageable pageable);

    @Query("select distinct s.militaryMedicalCommission from Soldier s")
    List<MilitaryMedicalCommission> findDistinctByMilitaryMedicalCommission();

}
