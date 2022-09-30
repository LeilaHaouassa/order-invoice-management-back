package com.proxym.orderandinvoicemanagement.services;

import com.proxym.orderandinvoicemanagement.model.config.Settings;


public interface ISettingsService {

    Settings getSettings();

    Settings changeSettings(Settings settings);
}
