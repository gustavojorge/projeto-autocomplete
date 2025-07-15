package com.jus.autocomplete.controller;

import com.jus.autocomplete.model.Suggestion;
import com.jus.autocomplete.service.TrieService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import java.util.Arrays;

@ExtendWith(MockitoExtension.class)
class SearchControllerTest {

    @Mock
    private TrieService trieService;

    @InjectMocks
    private SearchController searchController;

    @Test
    void shouldGetSuggestionsFromTrieService() {
        String prefix = "advo";
        int limit = 10;
        List<Suggestion> expectedSuggestions = Arrays.asList(
                new Suggestion("advogado", "Profissional do direito"),
                new Suggestion("advocacia", "Exercício da profissão de advogado")
        );

        when(trieService.searchSuggestions(prefix, limit)).thenReturn(expectedSuggestions);

        List<Suggestion> result = searchController.getSuggestions(prefix, limit);
        assertNotNull(result);
        assertEquals(expectedSuggestions.size(), result.size());
        assertEquals(expectedSuggestions, result);
        verify(trieService).searchSuggestions(prefix, limit);
    }

    @Test
    void shouldReturnEmptyListWhenNoSuggestions() {
        String prefix = "xxx";
        int limit = 10;
        List<Suggestion> emptySuggestions = Arrays.asList();

        when(trieService.searchSuggestions(prefix, limit)).thenReturn(emptySuggestions);

        List<Suggestion> result = searchController.getSuggestions(prefix, limit);
        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(trieService).searchSuggestions(prefix, limit);
    }

    @Test
    void shouldDelegateToTrieServiceWithCorrectParameters() {
        String prefix = "dire";
        int limit = 5;

        searchController.getSuggestions(prefix, limit);
        verify(trieService).searchSuggestions(prefix, limit);
    }
}
