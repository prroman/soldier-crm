package com.rpr.soldierscrm.repository;

import com.rpr.soldierscrm.entity.Soldier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SoldierRepository extends JpaRepository<Soldier, Long> {

    @Query(value = "select * from soldiers s where military_medical_commission like %:keyword% ", nativeQuery = true)
    List<Soldier> findByMilitaryMedicalCommissionContaining(@Param("keyword") String keyword);

    @Query(value = "select * from soldiers s where origin_brigade_arrival like %:keyword% ", nativeQuery = true)
    List<Soldier> findByOriginBrigadeArrivalContaining(@Param("keyword") String keyword);
}
