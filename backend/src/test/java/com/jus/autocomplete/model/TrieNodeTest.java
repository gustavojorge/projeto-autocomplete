package com.jus.autocomplete.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TrieNodeTest {

    @Test
    void shouldCreateTrieNodeWithCharacter() {
        char character = 'a';

        TrieNode node = new TrieNode(character);

        assertNotNull(node);
        assertNotNull(node.getChildren());
        assertFalse(node.isEndOfWord());
        assertNull(node.getFullWord());
        assertNull(node.getDefinition());
        assertTrue(node.getChildren().isEmpty());
    }

    @Test
    void shouldSetAndGetEndOfWord() {
        TrieNode node = new TrieNode('a');

        node.setEndOfWord(true);
        assertTrue(node.isEndOfWord());
    }

    @Test
    void shouldSetAndGetFullWord() {
        TrieNode node = new TrieNode('a');
        String word = "advogado";

        node.setFullWord(word);
        assertEquals(word, node.getFullWord());
    }

    @Test
    void shouldSetAndGetDefinition() {
        TrieNode node = new TrieNode('a');
        String definition = "Profissional do direito";

        node.setDefinition(definition);
        assertEquals(definition, node.getDefinition());
    }

    @Test
    void shouldAddChildrenToNode() {
        TrieNode parent = new TrieNode('a');
        TrieNode child = new TrieNode('b');

        parent.getChildren().put('b', child);

        assertEquals(1, parent.getChildren().size());
        assertEquals(child, parent.getChildren().get('b'));
    }
}