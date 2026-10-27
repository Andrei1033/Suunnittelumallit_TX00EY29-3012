package org.example.view;

import javafx.scene.text.Font;

import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;

/**
 * JavaFX on Linux often cannot find CJK glyphs through fontconfig (especially in .ttc files),
 * which shows up as empty boxes. The Docker build bundles a plain .ttf into the JAR
 * (/fonts/ipag.ttf); this class loads it directly. When the font is not bundled
 * (e.g. running from the IDE) the normal system fonts are used.
 */
public final class FontSupport {

    private static final String JP_FONT_RESOURCE = "/fonts/ipag.ttf";
    private static String japaneseFamily;
    private static boolean loaded;

    private FontSupport() {}

    /** Must be called after the JavaFX toolkit is started (e.g. in Application.start). */
    public static synchronized void load() {
        if (loaded) {
            return;
        }
        loaded = true;
        try (InputStream in = FontSupport.class.getResourceAsStream(JP_FONT_RESOURCE)) {
            if (in != null) {
                Font font = Font.loadFont(in, 12);
                if (font != null) {
                    japaneseFamily = font.getFamily();
                }
            }
        } catch (IOException e) {
            System.err.println("Could not load bundled font: " + e.getMessage());
        }
        System.out.println("Japanese UI font: "
                + (japaneseFamily == null ? "not bundled, using system fonts" : japaneseFamily));
    }

    /** Inline CSS for the given locale ("" when no special font is needed). */
    public static String styleFor(Locale locale) {
        if (japaneseFamily != null && locale != null && "ja".equals(locale.getLanguage())) {
            return "-fx-font-family: '" + japaneseFamily + "';";
        }
        return "";
    }
}
