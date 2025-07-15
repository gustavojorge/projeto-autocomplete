package com.jus.autocomplete.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SuggestionTest {

    @Test
    void shouldCreateSuggestionWithDefaultConstructor() {
        Suggestion suggestion = new Suggestion();

        assertNotNull(suggestion);
        assertNull(suggestion.getTerm());
        assertNull(suggestion.getDefinition());
    }

    @Test
    void shouldCreateSuggestionWithParameterizedConstructor() {
        String term = "Habeas Corpus";
        String definition = "Remédio constitucional que protege o direito de locomoção";

        Suggestion suggestion = new Suggestion(term, definition);

        assertEquals(term, suggestion.getTerm());
        assertEquals(definition, suggestion.getDefinition());
    }

    @Test
    void shouldSetAndGetTerm() {
        Suggestion suggestion = new Suggestion();
        String term = "Mandado de Segurança";

        suggestion.setTerm(term);
        assertEquals(term, suggestion.getTerm());
    }

    @Test
    void shouldSetAndGetDefinition() {
        Suggestion suggestion = new Suggestion();
        String definition = "Ação constitucional para proteger direito líquido e certo";

        suggestion.setDefinition(definition);
        assertEquals(definition, suggestion.getDefinition());
    }

}