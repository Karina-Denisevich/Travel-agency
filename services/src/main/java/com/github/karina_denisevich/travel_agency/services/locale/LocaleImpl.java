package com.github.karina_denisevich.travel_agency.services.locale;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class LocaleImpl implements CustomLocale {

    private String language;

    public String getLanguage() {
        if (language == null) {
            return "en";
        }
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
