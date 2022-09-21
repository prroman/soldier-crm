package com.rpr.soldierscrm.service;

import com.rpr.soldierscrm.entity.Attachment;
import com.rpr.soldierscrm.entity.Soldier;
import com.rpr.soldierscrm.exception.SoldierNotFoundException;
import com.rpr.soldierscrm.repository.AttachmentRepository;
import com.rpr.soldierscrm.repository.SoldierRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class SoldierService {

    private final SoldierRepository soldierRepository;
    private final AttachmentRepository attachmentRepository;

    public SoldierService(SoldierRepository soldierRepository, AttachmentRepository attachmentRepository) {
        this.soldierRepository = soldierRepository;
        this.attachmentRepository = attachmentRepository;
    }

    public Soldier getSoldierById(Long id) {
        return soldierRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Soldier with id " + id + " not found !"));
    }

    public List<Soldier> getAllSoldiers() {
        return soldierRepository.findAll();
    }

    public Soldier createOrUpdateSoldier(Soldier soldier, MultipartFile file) {
        if (soldier.getId() == null) {
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
                if (file != null) {
                    try {
                        Attachment attachment = new Attachment();
                        attachment.setName(file.getOriginalFilename());
                        attachment.setType(file.getContentType());
                        attachment.setData(file.getBytes());
                        attachmentRepository.save(attachment);
                        newSoldier.addAttachment(attachment);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                newSoldier = soldierRepository.save(newSoldier);
                return newSoldier;
            } else {
                soldier = soldierRepository.save(soldier);
                return soldier;
            }
        }
    }

    public void deleteSoldierById(Long id) throws SoldierNotFoundException {
        Optional<Soldier> soldier = soldierRepository.findById(id);
        if (soldier.isPresent()) {
            soldierRepository.deleteById(id);
        } else {
            throw new SoldierNotFoundException("No soldier record exist for given id");
        }
    }
}
