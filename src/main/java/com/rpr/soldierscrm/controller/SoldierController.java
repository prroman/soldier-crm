package com.rpr.soldierscrm.controller;

import com.rpr.soldierscrm.dto.SearchDto;
import com.rpr.soldierscrm.entity.MilitaryMedicalCommission;
import com.rpr.soldierscrm.entity.Soldier;
import com.rpr.soldierscrm.exception.SoldierNotFoundException;
import com.rpr.soldierscrm.service.ExcelService;
import com.rpr.soldierscrm.service.SoldierService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MimeTypeUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.OutputStream;
import java.text.ParseException;
import java.util.Optional;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class SoldierController {

    private final SoldierService soldierService;
    private final ExcelService excelService;
    private static final String RESULTS_FRAGMENT = "fragments/tableFragment :: resultsList";


    @RequestMapping("/")
    public String newPageable(@RequestParam(name = "page", defaultValue = "1", required = false) Integer pageNum, Model model) {
        Page<Soldier> page = soldierService.getSoldiersPageable(pageNum);
        model.addAttribute("currentPage", pageNum);
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("soldiers", page.getContent());
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

    @RequestMapping(value = "/api/search", method = RequestMethod.GET)
    public String searchByAllParamsWithPagination(@RequestParam(name = "fullName", required = false) String fullName,
                                                  @RequestParam(name = "vacation", required = false) String vacation,
                                                  @RequestParam(name = "hospital", required = false) String hospital,
                                                  @RequestParam(name = "dateOfBirth", required = false) Integer dateOfBirth,
                                                  @RequestParam(name = "phoneNumber", required = false) String phoneNumber,
                                                  @RequestParam(name = "battalion", required = false) String battalion,
                                                  @RequestParam(name = "fullTimePosition", required = false) String fullTimePosition,
                                                  @RequestParam(name = "militaryRankName", required = false) String militaryRankName,
                                                  @RequestParam(name = "militaryMedicalCommission", required = false) MilitaryMedicalCommission militaryMedicalCommission,
                                                  @RequestParam(name = "personalIdNumber", required = false) String personalIdNumber,
                                                  @RequestParam(name = "dateOfArrival", required = false) String dateOfArrival,
                                                  @RequestParam(name = "page", required = false, defaultValue = "1") Integer pageNum, Model model) throws ParseException {
        SearchDto searchRequest = new SearchDto(fullName, vacation, hospital, dateOfBirth, phoneNumber,
                battalion, fullTimePosition, militaryRankName, militaryMedicalCommission, personalIdNumber, dateOfArrival);

        Boolean allParamsAreNull = soldierService.isNull(fullName, vacation, hospital, dateOfBirth, phoneNumber,
                battalion, fullTimePosition, militaryRankName, personalIdNumber, dateOfArrival, militaryMedicalCommission);

        Page<Soldier> soldiersPage = allParamsAreNull
                ? soldierService.getSoldiersPageable(pageNum)
                : soldierService.searchByAllParamsWithPagination(searchRequest, pageNum);

        model.addAttribute("soldiers", soldiersPage.getContent());
        model.addAttribute("totalItems", soldiersPage.getTotalElements());
        model.addAttribute("currentPage", pageNum);
        model.addAttribute("totalPages", soldiersPage.getTotalPages());
        model.addAttribute("urlParams", soldiersPage.getContent());
        return RESULTS_FRAGMENT;
    }
}
