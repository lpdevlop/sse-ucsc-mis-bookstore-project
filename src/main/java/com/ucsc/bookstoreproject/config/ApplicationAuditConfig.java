package com.ucsc.bookstoreproject.config;


import com.ucsc.bookstoreproject.database.model.UserModel;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ApplicationAuditConfig implements AuditorAware<UserModel> {


    @Override
    public Optional<UserModel> getCurrentAuditor() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            return Optional.empty();
        }

        Object principal = authentication.getPrincipal();

        if (principal instanceof UserModel) {
            return Optional.of((UserModel) principal);
        }

        return Optional.empty();
    }
}

