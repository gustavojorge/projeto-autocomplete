package com.jus.autocomplete.integration;

import com.jus.autocomplete.model.Suggestion;
import com.jus.autocomplete.service.TrieService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.GraphQlTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.graphql.test.tester.GraphQlTester;
import org.springframework.test.context.ActiveProfiles;
import static org.mockito.Mockito.*;
import java.util.Arrays;
import java.util.List;

@GraphQlTest
@ActiveProfiles("test")
class AutocompleteIntegrationTest {

    @Autowired
    private GraphQlTester graphQlTester;

    @MockBean
    private TrieService trieService;

    @Test
    void shouldReturnSuggestionsViaGraphQL() {
        String prefix = "advo";
        int limit = 10;
        List<Suggestion> mockSuggestions = Arrays.asList(
                new Suggestion("advogado", "Profissional do direito"),
                new Suggestion("advocacia", "Exercício da profissão de advogado")
        );

        when(trieService.searchSuggestions(prefix, limit)).thenReturn(mockSuggestions);

        graphQlTester.document("""
            query {
                getSuggestions(prefix: "advo", limit: 10) {
                    term
                    definition
                }
            }
        """)
                .execute()
                .path("getSuggestions")
                .entityList(Suggestion.class)
                .hasSize(2)
                .path("getSuggestions[0].term").entity(String.class).isEqualTo("advogado")
                .path("getSuggestions[0].definition").entity(String.class).isEqualTo("Profissional do direito")
                .path("getSuggestions[1].term").entity(String.class).isEqualTo("advocacia")
                .path("getSuggestions[1].definition").entity(String.class).isEqualTo("Exercício da profissão de advogado");

        verify(trieService).searchSuggestions(prefix, limit);
    }

    @Test
    void shouldReturnEmptyArrayForNoResults() {
        String prefix = "xwx";
        int limit = 10;

        when(trieService.searchSuggestions(prefix, limit)).thenReturn(Arrays.asList());

        graphQlTester.document("""
            query {
                getSuggestions(prefix: "xwx", limit: 10) {
                    term
                    definition
                }
            }
        """)
                .execute()
                .path("getSuggestions")
                .entityList(Suggestion.class)
                .hasSize(0);

        verify(trieService).searchSuggestions(prefix, limit);
    }
}