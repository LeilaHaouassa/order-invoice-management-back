package com.proxym.orderandinvoicemanagement.services.Implementations;

import com.proxym.orderandinvoicemanagement.model.config.Settings;
import com.proxym.orderandinvoicemanagement.repositories.SettingsRepository;
import com.proxym.orderandinvoicemanagement.services.ISettingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;


@Service
public class SettingsServiceImpl implements ISettingsService {

    @Autowired
    private SettingsRepository settingsRepository;

    @Override
    public Boolean getSettingsBool() {
        return Objects.requireNonNull(settingsRepository.findById("1ID").orElse(null)).getResponseToBuyerIsRequiredWhenAcceptingOrder();
    }

    @Override
    public Boolean changeSettings(Boolean settingsBool) {
        Settings settings = settingsRepository.findById("1ID").orElse(null);
        assert settings != null;
        settings.setResponseToBuyerIsRequiredWhenAcceptingOrder(settingsBool);
        settingsRepository.save(settings);
        return settings.getResponseToBuyerIsRequiredWhenAcceptingOrder();
    }
}
