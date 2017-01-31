package com.skylabase.agromarketplace.model;

import com.skylabase.agromarketplace.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.beans.PropertyEditorSupport;

@Component
public class UserPropertyEditor extends PropertyEditorSupport {

    @Autowired
    private UserService userService;

    @Override
    public String getAsText() {
        final User user = (User) getValue();
        if (user == null) {
            return "";
        }
        else {
            return user.getId().toString();
        }
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        if (StringUtils.hasText(text)) {
            setValue(userService.findById(Long.valueOf(text)));
        }
        else {
            setValue(null);
        }
    }
}
