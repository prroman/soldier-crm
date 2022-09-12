package com.rpr.soldierscrm.service;

import com.rpr.soldierscrm.entity.Soldier;
import com.rpr.soldierscrm.exception.SoldierNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SoldierServiceLocal {

    public Long idCounter = 0L;
    public List<Soldier> soldiers = new ArrayList<>();

    public SoldierServiceLocal() {
    }

    public Soldier getSoldierById(Long id) {
        return soldiers.stream().filter(soldier -> soldier.getId().equals(id)).findFirst()
                .orElseThrow(() -> new RuntimeException("Soldier with id " + id + " not found !"));
    }

    public List<Soldier> getAllSoldiers() {
        return soldiers;
    }

    public Soldier createOrUpdateSoldier(Soldier soldier) {
        if (soldier.getId()  == null) {
            idCounter++;
            soldier.setId(idCounter);
            soldiers.add(soldier);
            return soldier;
        } else {
            Soldier finalSoldier = soldier;
            Optional<Soldier> existingSoldier = soldiers.stream().filter(entity -> entity.getId().equals(finalSoldier.getId())).findFirst();
            if (existingSoldier.isPresent()) {
                idCounter++;
                Soldier newSoldier = existingSoldier.get();
                newSoldier.setId(idCounter);
                newSoldier.setFullName(soldier.getFullName());
                newSoldier.setVacation(soldier.getVacation());
                newSoldier.setHospital(soldier.getHospital());
                newSoldier.setDateOfBirth(soldier.getDateOfBirth());
                newSoldier.setPhoneNumber(soldier.getPhoneNumber());
                newSoldier.setBattalion(soldier.getBattalion());
                newSoldier.setFullTimePosition(soldier.getFullTimePosition());
                newSoldier.setMilitaryRankName(soldier.getMilitaryRankName());
                newSoldier.setMilitaryMedicalCommission(soldier.getMilitaryMedicalCommission());
                newSoldier.setPersonalIdNumber(soldier.getPersonalIdNumber());
                newSoldier.setDateOfArrival(soldier.getDateOfArrival());
                newSoldier.setEnrollmentOrderNumber(soldier.getEnrollmentOrderNumber());
                newSoldier.setOriginBrigadeArrival(soldier.getOriginBrigadeArrival());
                newSoldier.setInternalOrder(soldier.getInternalOrder());
                soldiers.add(newSoldier);
                return newSoldier;
            } else {
                soldiers.add(soldier);
                return soldier;
            }
        }
    }

    public void deleteSoldierById(Long id) throws SoldierNotFoundException {
        Optional<Soldier> first = soldiers.stream().filter(soldier -> soldier.getId().equals(id)).findFirst();
        if (first.isPresent()) {
            soldiers.remove(first);
        } else {
            throw new SoldierNotFoundException("Soldier with id " + id + "not found");
        }
    }
}
