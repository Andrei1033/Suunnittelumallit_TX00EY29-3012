package org.example.I18n;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Locale;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

public final class I18n {

    private static final String BUNDLE_BASE = "MessagesBundle";
    private static Locale current = Locale.US;
    private static ResourceBundle bundle = load(current);

    private I18n() {}

    public static void setLocale(Locale locale) {
        current = locale;
        bundle = load(locale);
    }

    public static Locale getLocale() {
        return current;
    }

    public static String get(String key) {
        return bundle.getString(key);
    }

    private static ResourceBundle load(Locale locale) {
        String path = "/" + BUNDLE_BASE + "_" + locale + ".properties";
        try (InputStream in = I18n.class.getResourceAsStream(path)) {
            if (in == null) {
                throw new IllegalStateException("Missing resource: " + path);
            }
            // UTF-8 decoding is the key requirement here
            return new PropertyResourceBundle(new InputStreamReader(in, StandardCharsets.UTF_8));
        } catch (IOException e) {
            throw new IllegalStateException("Could not load " + path, e);
        }
    }
}
