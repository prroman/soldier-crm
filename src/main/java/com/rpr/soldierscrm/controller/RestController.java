package com.rpr.soldierscrm.controller;

import com.rpr.soldierscrm.dto.SearchDto;
import com.rpr.soldierscrm.entity.Soldier;
import com.rpr.soldierscrm.service.SoldierService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@org.springframework.web.bind.annotation.RestController
@RequiredArgsConstructor
public class RestController {

    private final SoldierService soldierService;
    @PostMapping("/api/searchByAllParamsWithPagination")
    Page<Soldier> searchByAllParamsWithPagination(@RequestBody SearchDto searchDto) {
        return soldierService.searchByAllParamsWithPagination(searchDto);
    }

}
