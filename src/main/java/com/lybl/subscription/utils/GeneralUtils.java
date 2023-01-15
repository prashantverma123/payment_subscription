package com.lybl.subscription.utils;

import com.lybl.subscription.model.User;
import com.lybl.subscription.service.UserDetailsImpl;
import org.springframework.security.core.context.SecurityContextHolder;

public class GeneralUtils {

    public static UserDetailsImpl getLoggedInUser() {
        var auth = SecurityContextHolder.getContext().getAuthentication();
        return (UserDetailsImpl) auth.getPrincipal();
    }
}