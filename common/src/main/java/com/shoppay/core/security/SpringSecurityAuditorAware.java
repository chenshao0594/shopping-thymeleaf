package com.shoppay.core.security;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import com.shoppay.common.constants.AppConstants;

/**
 * Implementation of AuditorAware based on Spring Security.
 */
@Component
public class SpringSecurityAuditorAware implements AuditorAware<String> {

    @Override
    public String getCurrentAuditor() {
        String userName = SecurityUtils.getCurrentUserLogin();
        return userName != null ? userName : AppConstants.SYSTEM_ACCOUNT;
    }
}
