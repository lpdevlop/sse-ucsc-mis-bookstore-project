package com.ucsc.bookstoreproject.utils;

import com.ucsc.bookstoreproject.database.model.UserModel;
import lombok.experimental.UtilityClass;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@UtilityClass
public class SecurityUtil {

    public static UserModel getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getPrincipal() instanceof UserModel userModel) {
            return userModel;
        }
        return null;
    }
}
