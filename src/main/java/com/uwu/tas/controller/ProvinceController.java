package com.uwu.tas.controller;


import com.uwu.tas.dto.ProvinceDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/admin/province")
public class ProvinceController {

    @PostMapping(value = "/save")


}
