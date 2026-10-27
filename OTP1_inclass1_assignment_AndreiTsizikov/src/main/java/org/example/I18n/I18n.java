package org.example.I18n;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Locale;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

/**
 * Loads the UTF-8 encoded MessagesBundle_xx_XX.properties files.
 * Missing keys / unsupported locales fall back to English instead of crashing.
 */
public final class I18n {

    private static final String BUNDLE_BASE = "MessagesBundle";

    public static final Locale DEFAULT_LOCALE = Locale.of("en", "US");

    /** Order = order shown in the language selector. */
    public static final List<Locale> SUPPORTED = List.of(
            DEFAULT_LOCALE,
            Locale.of("fi", "FI"),
            Locale.of("sv", "SE"),
            Locale.of("ja", "JP")
    );

    private static final ResourceBundle FALLBACK = load(DEFAULT_LOCALE);

    private static Locale current = DEFAULT_LOCALE;
    private static ResourceBundle bundle = FALLBACK;

    private I18n() {}

    public static void setLocale(Locale locale) {
        Locale supported = findSupported(locale);
        current = supported;
        bundle = supported.equals(DEFAULT_LOCALE) ? FALLBACK : load(supported);
    }

    public static Locale getLocale() {
        return current;
    }

    public static String get(String key) {
        if (bundle.containsKey(key)) {
            return bundle.getString(key);
        }
        if (FALLBACK.containsKey(key)) {
            return FALLBACK.getString(key);
        }
        return key;
    }

    /** Localized display name of a unit stored in the DB (Celsius, Fahrenheit, Kelvin). */
    public static String unitName(String dbName) {
        String key = "unit." + dbName;
        String value = get(key);
        return value.equals(key) ? dbName : value;
    }

    private static Locale findSupported(Locale locale) {
        if (locale != null) {
            for (Locale l : SUPPORTED) {
                if (l.getLanguage().equals(locale.getLanguage())) {
                    return l;
                }
            }
        }
        return DEFAULT_LOCALE;
    }

    private static ResourceBundle load(Locale locale) {
        String path = "/" + BUNDLE_BASE + "_" + locale + ".properties";
        try (InputStream in = I18n.class.getResourceAsStream(path)) {
            if (in == null) {
                throw new IllegalStateException("Missing resource: " + path);
            }
            // Explicit UTF-8 decoding (PropertyResourceBundle(InputStream) would use ISO-8859-1 on Java 8)
            return new PropertyResourceBundle(new InputStreamReader(in, StandardCharsets.UTF_8));
        } catch (IOException e) {
            throw new IllegalStateException("Could not load " + path, e);
        }
    }
}
