package com.rpr.soldierscrm.controller;

import com.rpr.soldierscrm.entity.Attachment;
import com.rpr.soldierscrm.entity.Soldier;
import com.rpr.soldierscrm.service.AttachmentService;
import com.rpr.soldierscrm.service.SoldierService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.HttpHeaders;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

@Controller
public class AttachmentController {

    private final AttachmentService attachmentService;
    private final SoldierService soldierService;

    public AttachmentController(AttachmentService attachmentService, SoldierService soldierService) {
        this.attachmentService = attachmentService;
        this.soldierService = soldierService;
    }

    @GetMapping(path = "/attachments/{soldierId}")
    public String getAttachmentsIndexPage(@PathVariable Long soldierId, Model model) {
        Soldier soldier = soldierService.getSoldierById(soldierId);
        List<Attachment> attachments = soldier.getAttachments();

        model.addAttribute("soldier", soldier);
        model.addAttribute("soldier_files", attachments);
        return "attachments-soldier";
    }

    @PostMapping("/attachments/upload/{soldierId}")
    public String uploadAttachment(@PathVariable Long soldierId, @RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return "redirect:/attachments/" + soldierId;
        }
        soldierService.createOrUpdateSoldier(soldierService.getSoldierById(soldierId), file);
        return "redirect:/attachments/" + soldierId;
    }

    @GetMapping("/attachments/download/{attachmentId}")
    public void downloadFile(@PathVariable Long attachmentId, HttpServletResponse resp) throws IOException {
        Attachment attachment = attachmentService.getAttachmentById(attachmentId);
        byte[] byteArray =  attachment.getData(); // read the byteArray
        resp.setContentType(MimeTypeUtils.APPLICATION_OCTET_STREAM.getType());
        resp.setHeader("Content-Disposition", "attachment; filename=" + attachment.getName());
        resp.setContentLength(byteArray.length);
        OutputStream os = resp.getOutputStream();
        try {
            os.write(byteArray, 0, byteArray.length);
        } finally {
            os.close();
        }
    }

    @PostMapping(path = "/attachments/delete/{attachmentId}/{soldierId}")
    public String delete(@PathVariable("attachmentId") Long attachmentId, @PathVariable("soldierId") Long soldierId) {
        attachmentService.deleteAttachmentById(attachmentId);
        return "redirect:/attachments/" + soldierId;
    }

    private HttpHeaders headers(String name) {
        HttpHeaders header = new HttpHeaders();
        header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + name);
        header.add("Cache-Control", "no-cache, no-store," + " must-revalidate");
        header.add("Pragma", "no-cache");
        header.add("Expires", "0");
        return header;

    }
}
