package com.jus.autocomplete.util;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TextNormalizerTest {

    @Test
    void shouldNormalizeTextWithAccents() {
        String input = "Ação Jurídica";
        String result = TextNormalizer.normalize(input);
        assertEquals("acao juridica", result);
    }

    @Test
    void shouldNormalizeTextWithUppercase() {
        String input = "DIREITO CONSTITUCIONAL";
        String result = TextNormalizer.normalize(input);
        assertEquals("direito constitucional", result);
    }

    @Test
    void shouldNormalizeTextWithSpecialCharacters() {
        String input = "Ação-Jurídica";
        String result = TextNormalizer.normalize(input);
        assertEquals("acao-juridica", result);
    }

    @Test
    void shouldHandleEmptyString() {
        String input = "";
        String result = TextNormalizer.normalize(input);
        assertEquals("", result);
    }

    @Test
    void shouldTrimWhitespace() {
        String input = "  Direito Civil  ";
        String result = TextNormalizer.normalize(input);
        assertEquals("direito civil", result);
    }
}
