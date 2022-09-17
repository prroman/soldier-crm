package com.rpr.soldierscrm.service;

import com.rpr.soldierscrm.entity.Attachment;
import com.rpr.soldierscrm.repository.AttachmentRepository;
import com.rpr.soldierscrm.repository.SoldierRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AttachmentService {

    private final SoldierRepository soldierRepository;
    private final AttachmentRepository attachmentRepository;

    public AttachmentService(SoldierRepository soldierRepository, AttachmentRepository attachmentRepository) {
        this.soldierRepository = soldierRepository;
        this.attachmentRepository = attachmentRepository;
    }

    public Attachment getAttachmentById(Long id) {
        return attachmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Attachment with id " + id + " not found !"));
    }

    public List<Attachment> getAllAttachments() {
        return attachmentRepository.findAll();
    }

    public void deleteAttachmentById(Long id) {
        Optional<Attachment> attachment = attachmentRepository.findById(id);
        if (attachment.isPresent()) {
            attachmentRepository.deleteById(id);
        } else {
            throw new RuntimeException("No soldier record exist for given id");
        }
    }
}
