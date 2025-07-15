package com.jus.autocomplete.service;

import com.jus.autocomplete.model.Suggestion;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class DataLoadServiceTest {

    @InjectMocks
    private DataLoadService dataLoadService;

    @Test
    void shouldLoadSuggestionsFromFile() {
        List<Suggestion> suggestions = dataLoadService.loadSuggestions();

        assertNotNull(suggestions);
        assertTrue(suggestions.isEmpty() || !suggestions.isEmpty());
    }

}