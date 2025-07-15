package com.jus.autocomplete.integration;

import com.jus.autocomplete.model.Suggestion;
import com.jus.autocomplete.service.DataLoadService;
import com.jus.autocomplete.service.TrieService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
@ActiveProfiles("test")
class TrieServiceIntegrationTest {

    @Autowired
    private TrieService trieService;

    @MockBean
    private DataLoadService dataLoadService;

    @Test
    void shouldInitializeAndSearchIntegration() {
        List<Suggestion> mockData = Arrays.asList(
                new Suggestion("advogado", "Profissional do direito"),
                new Suggestion("advocacia", "Exercício da profissão de advogado"),
                new Suggestion("direito", "Conjunto de normas jurídicas"),
                new Suggestion("justiça", "Virtude que consiste na vontade de dar a cada um o que é seu")
        );

        when(dataLoadService.loadSuggestions()).thenReturn(mockData);

        trieService.initializeTrie();

        List<Suggestion> results = trieService.searchSuggestions("advo", 10);
        assertEquals(2, results.size());

        results = trieService.searchSuggestions("dire", 10);
        assertEquals(1, results.size());
        assertEquals("direito", results.get(0).getTerm());

        results = trieService.searchSuggestions("just", 10);
        assertEquals(1, results.size());
        assertEquals("justiça", results.get(0).getTerm());
    }

    @Test
    void shouldHandleAccentedTermsIntegration() {
        List<Suggestion> mockData = Arrays.asList(
                new Suggestion("Ação Cível", "Também conhecida como ação civil."),
                new Suggestion("Sentença", "Ato processual do magistrado que extingue o processo, com ou sem julgamento de mérito."),
                new Suggestion("Cláusula pétrea", "Dispositivo constitucional que forma o núcleo intangível da Constituição Federal.")
        );

        when(dataLoadService.loadSuggestions()).thenReturn(mockData);

        trieService.initializeTrie();

        List<Suggestion> results = trieService.searchSuggestions("acao", 10);
        assertEquals(1, results.size());
        assertEquals("Ação Cível", results.get(0).getTerm());

        results = trieService.searchSuggestions("sent", 10);
        assertEquals(1, results.size());
        assertEquals("Sentença", results.get(0).getTerm());

        results = trieService.searchSuggestions("clau", 10);
        assertEquals(1, results.size());
        assertEquals("Cláusula pétrea", results.get(0).getTerm());
    }
}