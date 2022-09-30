package com.proxym.orderandinvoicemanagement.services.Implementations;

import com.proxym.orderandinvoicemanagement.model.config.Settings;
import com.proxym.orderandinvoicemanagement.repositories.SettingsRepository;
import com.proxym.orderandinvoicemanagement.services.ISettingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class SettingsServiceImpl implements ISettingsService {

    @Autowired
    private SettingsRepository settingsRepository;

    @Override
    public Settings getSettings() {
        return settingsRepository.findById("1ID").orElse(null);
    }

    @Override
    public Settings changeSettings(Settings settings) {
        return settingsRepository.save(settings);
    }
}
