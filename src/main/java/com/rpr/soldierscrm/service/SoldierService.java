package com.rpr.soldierscrm.service;

import com.rpr.soldierscrm.entity.Soldier;
import com.rpr.soldierscrm.exception.RecordNotFoundException;
import com.rpr.soldierscrm.repository.SoldierRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SoldierService {

    private final SoldierRepository soldierRepository;

    public SoldierService(SoldierRepository soldierRepository) {
        this.soldierRepository = soldierRepository;
    }

    public Soldier getSoldierById(Long id) {
        return soldierRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Soldier with id " + id + " not found !"));
    }

    public List<Soldier> getAllSoldiers() {
        return soldierRepository.findAll();
    }

    public Soldier createOrUpdateSoldier(Soldier soldier) {
        if (soldier.getId()  == null) {
                soldier = soldierRepository.save(soldier);
                return soldier;
            } else {
                Optional<Soldier> existingSoldier = soldierRepository.findById(soldier.getId());
                if (existingSoldier.isPresent()) {
                    Soldier newSoldier = existingSoldier.get();
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
                    newSoldier = soldierRepository.save(newSoldier);
                    return newSoldier;
                } else {
                    soldier = soldierRepository.save(soldier);
                    return soldier;
                }
        }
    }

    public void deleteSoldierById(Long id) throws RecordNotFoundException {
        Optional<Soldier> soldier = soldierRepository.findById(id);
        if (soldier.isPresent()) {
            soldierRepository.deleteById(id);
        } else {
            throw new RecordNotFoundException("No soldier record exist for given id");
        }
    }

    public List<Soldier> getSoldiersFilteredBy(String filteredBy, String keyword) {
        if (filteredBy.equalsIgnoreCase("militaryMedicalCommission")) {
            return soldierRepository.findByMilitaryMedicalCommissionContaining(keyword);
        } else if (filteredBy.equalsIgnoreCase("originBrigadeArrival")) {
            return soldierRepository.findByOriginBrigadeArrivalContaining(keyword);
        } else {
            soldierRepository.findAll();
        }
        return null;
    }
}
