package com.jus.autocomplete.service;

import com.jus.autocomplete.model.Suggestion;
import org.junit.jupiter.api.BeforeEach;
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
class TrieServiceTest {

    @Mock
    private DataLoadService dataLoadService;

    @InjectMocks
    private TrieService trieService;

    @BeforeEach
    void setUp() {
        List<Suggestion> mockSuggestions = Arrays.asList(
                new Suggestion("advogado", "Profissional do direito"),
                new Suggestion("advocacia", "Exercício da profissão de advogado"),
                new Suggestion("direito", "Conjunto de normas jurídicas"),
                new Suggestion("diretor", "Conjunto de normas jurídicas"),
                new Suggestion("ação", "Conjunto de normas jurídicas")
        );

        when(dataLoadService.loadSuggestions()).thenReturn(mockSuggestions);
        trieService.initializeTrie();
    }

    @Test
    void shouldInitializeTrieWithLoadedData() {
        verify(dataLoadService).loadSuggestions();
    }

    @Test
    void shouldSearchSuggestionsWithValidPrefix() {
        String prefix = "advo";
        int limit = 10;

        List<Suggestion> results = trieService.searchSuggestions(prefix, limit);
        assertNotNull(results);
        assertEquals(2, results.size());
        assertTrue(results.stream().anyMatch(s -> s.getTerm().equals("advogado")));
        assertTrue(results.stream().anyMatch(s -> s.getTerm().equals("advocacia")));
    }

    @Test
    void shouldReturnEmptyListForShortPrefix() {
        String prefix = "adv";
        int limit = 10;

        List<Suggestion> results = trieService.searchSuggestions(prefix, limit);
        assertNotNull(results);
        assertTrue(results.isEmpty());
    }

    @Test
    void shouldReturnEmptyListForEmptyPrefix() {
        String prefix = "";
        int limit = 10;

        List<Suggestion> results = trieService.searchSuggestions(prefix, limit);
        assertNotNull(results);
        assertTrue(results.isEmpty());
    }

    @Test
    void shouldRespectLimitParameter() {
        String prefix = "dire";
        int limit = 1;

        List<Suggestion> results = trieService.searchSuggestions(prefix, limit);
        assertNotNull(results);
        assertTrue(results.size() <= limit);
    }

    @Test
    void shouldNormalizePrefixBeforeSearch() {
        String prefix = "DIRE";
        int limit = 10;

        List<Suggestion> results = trieService.searchSuggestions(prefix, limit);
        assertNotNull(results);
        assertEquals(2, results.size());
        assertEquals("diretor", results.get(0).getTerm());
        assertEquals("direito", results.get(1).getTerm());
    }

    @Test
    void shouldNormalizePrefixBeforeSearch2() {
        String prefix = "AÇÃO";
        int limit = 10;

        List<Suggestion> results = trieService.searchSuggestions(prefix, limit);
        assertNotNull(results);
        assertEquals(1, results.size());
        assertEquals("ação", results.get(0).getTerm());
    }
}