package com.rpr.soldierscrm.controller;

import com.rpr.soldierscrm.entity.Soldier;
import com.rpr.soldierscrm.exception.SoldierNotFoundException;
import com.rpr.soldierscrm.service.SoldierService;
import com.rpr.soldierscrm.service.SoldierServiceLocal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Optional;

@Controller
@RequestMapping("/")
public class SoldierController {

    private final SoldierServiceLocal soldierService;

    public SoldierController(SoldierServiceLocal soldierService) {
        this.soldierService = soldierService;
    }

    @RequestMapping
    public String getAllSoldiers(Model model) {
        model.addAttribute("soldiers", soldierService.getAllSoldiers());
        return "list-soldiers";
    }

    @RequestMapping(path = {"/edit", "/edit/{id}"})
    public String editSoldierById(Model model, @PathVariable("id") Optional<Long> id) throws SoldierNotFoundException {
        if (id.isPresent()) {
            Soldier entity = soldierService.getSoldierById(id.get());
            model.addAttribute("soldier", entity);
        } else {
            model.addAttribute("soldier", new Soldier());
        }
        return "add-edit-soldier";
    }

    @RequestMapping(path = "/delete/{id}")
    public String deleteEmployeeById(Model model, @PathVariable("id") Long id) throws SoldierNotFoundException {
        soldierService.deleteSoldierById(id);
        return "redirect:/";
    }

    @RequestMapping(path = "/createSoldier", method = RequestMethod.POST)
    public String createOrUpdateSoldier(Soldier soldier) {
        soldierService.createOrUpdateSoldier(soldier);
        return "redirect:/";
    }
}
