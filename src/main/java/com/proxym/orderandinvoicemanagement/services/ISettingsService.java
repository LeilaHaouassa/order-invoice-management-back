package com.proxym.orderandinvoicemanagement.services;


import com.proxym.orderandinvoicemanagement.model.config.Settings;

public interface ISettingsService {

    Boolean getSettingsBool();

    Boolean changeSettings(Boolean settings);
}
