package com.rpr.soldierscrm.repository;

import com.rpr.soldierscrm.entity.Soldier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface SoldierRepository extends PagingAndSortingRepository<Soldier, Long>, JpaRepository<Soldier, Long> {


}
