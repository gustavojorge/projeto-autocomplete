package com.jus.autocomplete.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

class TrieTest {

    private Trie trie;

    @BeforeEach
    void setUp() {
        trie = new Trie();
    }

    @Test
    void shouldInsertAndSearchSingleWord() {
        String word = "advogado";
        String definition = "Profissional do direito";

        trie.insert(word, definition);
        List<Suggestion> results = trie.searchByPrefix("adv", 10);

        assertEquals(1, results.size());
        assertEquals(word, results.get(0).getTerm());
        assertEquals(definition, results.get(0).getDefinition());
    }

    @Test
    void shouldInsertMultipleWordsAndSearchWithPrefix() {
        trie.insert("Ação Cível", "Também conhecida como ação civil.");
        trie.insert("Ação cautelar", "Ação de natureza instrumental que visa prevenir qualquer lesão de direito, bem como garantir a eficácia futura do processo principal com o qual está relacionada.");
        trie.insert("Ação penal", "É a ação para examinar a ocorrência de crime ou contravenção.");

        List<Suggestion> results = trie.searchByPrefix("aca", 10);
        assertEquals(3, results.size());
    }

    @Test
    void shouldRespectLimit() {
        trie.insert("Ação Cível", "Também conhecida como ação civil.");
        trie.insert("Ação cautelar", "Ação de natureza instrumental que visa prevenir qualquer lesão de direito, bem como garantir a eficácia futura do processo principal com o qual está relacionada.");
        trie.insert("Ação penal", "É a ação para examinar a ocorrência de crime ou contravenção.");

        List<Suggestion> results = trie.searchByPrefix("aca", 2);

        assertEquals(2, results.size());
    }

    @Test
    void shouldReturnEmptyListForNonExistentPrefix() {
        trie.insert("Ação Cível", "Também conhecida como ação civil.");

        List<Suggestion> results = trie.searchByPrefix("aaa", 10);
        assertTrue(results.isEmpty());
    }

    @Test
    void shouldHandleAccentedCharacters() {
        trie.insert("Ação Cível", "Também conhecida como ação civil.");
        trie.insert("Ação cautelar", "Ação de natureza instrumental que visa prevenir qualquer lesão de direito, bem como garantir a eficácia futura do processo principal com o qual está relacionada.");
        trie.insert("Ação penal", "É a ação para examinar a ocorrência de crime ou contravenção.");

        List<Suggestion> results = trie.searchByPrefix("acao", 10);

        assertEquals(3, results.size());
    }

    @Test
    void shouldSearchExactMatch() {
        trie.insert("direito", "Conjunto de normas jurídicas");

        List<Suggestion> results = trie.searchByPrefix("direito", 10);
        assertEquals(1, results.size());
        assertEquals("direito", results.get(0).getTerm());
    }

    @Test
    void shouldHandleEmptyPrefix() {
        trie.insert("direito", "Conjunto de normas jurídicas");

        List<Suggestion> results = trie.searchByPrefix("", 10);
        assertEquals(0, results.size());
    }

    @Test
    void shouldHandleEmptyPrefixWithSpaces() {
        trie.insert("direito", "Conjunto de normas jurídicas");

        List<Suggestion> results = trie.searchByPrefix("   ", 10);
        assertEquals(0, results.size());
    }
}
