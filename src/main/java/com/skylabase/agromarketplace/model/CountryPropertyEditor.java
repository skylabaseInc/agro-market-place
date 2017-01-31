package com.skylabase.agromarketplace.model;

import com.skylabase.agromarketplace.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.beans.PropertyEditorSupport;

@Component
public class CountryPropertyEditor extends PropertyEditorSupport {

    @Autowired
    private CountryService countryService;

    @Override
    public String getAsText() {
        final Country country = (Country) getValue();
        if (country == null) {
            return "";
        }
        else {
            return country.getId().toString();
        }
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        if (StringUtils.hasText(text)) {
            setValue(countryService.findById(Long.valueOf(text)));
        }
        else {
            setValue(null);
        }
    }
}
