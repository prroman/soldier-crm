package com.rpr.soldierscrm.controller;

import com.rpr.soldierscrm.entity.Soldier;
import com.rpr.soldierscrm.exception.SoldierNotFoundException;
import com.rpr.soldierscrm.service.ExcelService;
import com.rpr.soldierscrm.service.SoldierService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MimeTypeUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class SoldierController {

    private final SoldierService soldierService;
    private final ExcelService excelService;

    public SoldierController(SoldierService soldierService, ExcelService excelService) {
        this.soldierService = soldierService;
        this.excelService = excelService;
    }

    @RequestMapping
    public String getAllSoldiers(Model model) {
        model.addAttribute("soldiers", soldierService.getAllSoldiers());
        return "list-soldiers";
    }

    @RequestMapping("/pageable")
    public String getAllSoldiersPageable(Model model) {
        int currentPage = 1;
        Page<Soldier> page = soldierService.getSoldiersPageable();
        List<Soldier> soldierList = page.getContent();

        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("soldiers", soldierList);
        return "list-soldiers";
    }

    @RequestMapping(path = {"/create", "/edit/{id}"})
    public String editSoldierById(Model model, @PathVariable("id") Optional<Long> id) {
        if (id.isPresent()) {
            Soldier entity = soldierService.getSoldierById(id.get());
            model.addAttribute("soldier", entity);
        } else {
            model.addAttribute("soldier", new Soldier());
        }
        return "add-edit-soldier";
    }

    @PostMapping(path = "/delete/{id}")
    public String deleteEmployeeById(@PathVariable("id") Long id) throws SoldierNotFoundException {
        soldierService.deleteSoldierById(id);
        return "redirect:/";
    }

    @RequestMapping(path = "/createSoldier", method = RequestMethod.POST)
    public String createOrUpdateSoldier(@Valid Soldier soldier, BindingResult result) {
        if (result.hasErrors()) {
            return "add-edit-soldier";
        }
        soldierService.createOrUpdateSoldier(soldier, null);
        return "redirect:/";
    }

    @GetMapping("/generateExcelReport")
    public void generateExcelReport(HttpServletResponse resp) throws IOException {
        byte[] bytes = excelService.generateExcel().toByteArray();
        resp.setContentType(MimeTypeUtils.APPLICATION_OCTET_STREAM.getType());
        resp.setHeader("Content-Disposition", "attachment; filename=excelReport.xlsx");
        resp.setContentLength(bytes.length);
        OutputStream os = resp.getOutputStream();
        try {
            os.write(bytes, 0, bytes.length);
        } finally {
            os.close();
        }
    }
}
