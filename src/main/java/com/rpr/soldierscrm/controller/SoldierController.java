package com.rpr.soldierscrm.controller;

import com.rpr.soldierscrm.entity.Soldier;
import com.rpr.soldierscrm.exception.RecordNotFoundException;
import com.rpr.soldierscrm.service.SoldierService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Optional;

@Controller
@RequestMapping("/")
public class SoldierController {

    private final SoldierService soldierService;
    private Object[] militaryMedicalCommissionOptions = {"REQUIRE", "AWAITING", "INPROGRESS", "FINISHED"};

    public SoldierController(SoldierService soldierService) {
        this.soldierService = soldierService;
    }

    @RequestMapping
    public String getAllSoldiers(Model model, String filterBy, String keyword) {
        if (keyword != null && filterBy != null) {
            model.addAttribute("soldiers", soldierService.getSoldiersFilteredBy(filterBy, keyword));
            model.addAttribute("militaryMedicalCommissionOptions", militaryMedicalCommissionOptions);
        } else {
            model.addAttribute("soldiers", soldierService.getAllSoldiers());
            model.addAttribute("militaryMedicalCommissionOptions", militaryMedicalCommissionOptions);
        }
        return "list-soldiers";
    }

    @RequestMapping(path = {"/edit", "/edit/{id}"})
    public String editSoldierById(Model model, @PathVariable("id") Optional<Long> id) throws RecordNotFoundException {
        if (id.isPresent()) {
            Soldier entity = soldierService.getSoldierById(id.get());
            model.addAttribute("soldier", entity);
        } else {
            model.addAttribute("soldier", new Soldier());
        }
        return "add-edit-soldier";
    }

    @RequestMapping(path = "/delete/{id}")
    public String deleteEmployeeById(Model model, @PathVariable("id") Long id) throws RecordNotFoundException {
        soldierService.deleteSoldierById(id);
        return "redirect:/";
    }

    @RequestMapping(path = "/createSoldier", method = RequestMethod.POST)
    public String createOrUpdateSoldier(Soldier soldier) {
        soldierService.createOrUpdateSoldier(soldier);
        return "redirect:/";
    }
}
