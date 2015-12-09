package com.gofriend.vienngan.vngofriend.utils;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;

import java.util.Locale;

/**
 * Created by tmvien on 12/9/15.
 */
public class LanguageUtils {

    /**
     * Set the language for the application
     */
    public static void setLanguage(Context context, String language) {

        Locale locale = Locale.getDefault();
        if (Locale.JAPAN.getLanguage().equals(language)) {
            locale = Locale.JAPAN;
        } else {
            locale = Locale.US;
        }
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        Resources resources = context.getResources();
        resources.updateConfiguration(config, null);
    }
}
