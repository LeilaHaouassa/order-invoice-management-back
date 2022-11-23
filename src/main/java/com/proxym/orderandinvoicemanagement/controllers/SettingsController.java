package com.proxym.orderandinvoicemanagement.controllers;

import com.proxym.orderandinvoicemanagement.dto.commun.PartyDTO;
import com.proxym.orderandinvoicemanagement.model.config.Settings;
import com.proxym.orderandinvoicemanagement.services.ISettingsService;
import com.proxym.orderandinvoicemanagement.services.Implementations.SettingsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@CrossOrigin
@RequestMapping("api/v1/settings")
//this is for a page that is going to enable access and edit of settings flags
public class SettingsController {

    @Autowired
    private ISettingsService settingsService;

    @GetMapping
    public Boolean getSettings(){
        return settingsService.getSettingsBool();
    }

    @PostMapping
    public Boolean changeSettings(@RequestBody Boolean settings){
        return settingsService.changeSettings(settings);
    }
}
