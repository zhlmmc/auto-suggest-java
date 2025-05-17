package org.example.leansoftx;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class TrieTests {
    private Trie trie;

    @BeforeEach
    void setUp() {
        trie = new Trie();
    }

    @Test
    void testInsertAndAutoSuggest() {
        assertTrue(trie.insert("cat"));
        assertTrue(trie.insert("car"));
        assertTrue(trie.insert("cart"));

        List<String> suggestions = trie.autoSuggest("ca");
        assertNotNull(suggestions);
        assertEquals(3, suggestions.size());
        assertTrue(suggestions.contains("cat"));
        assertTrue(suggestions.contains("car"));
        assertTrue(suggestions.contains("cart"));
    }

    @Test
    void testInsertDuplicate() {
        assertTrue(trie.insert("test"));
        assertFalse(trie.insert("test"));
    }

    @Test
    void testAutoSuggestWithNoMatch() {
        trie.insert("hello");
        List<String> suggestions = trie.autoSuggest("xyz");
        assertNotNull(suggestions);
        assertTrue(suggestions.isEmpty());
    }

    @Test
    void testGetAllWords() {
        trie.insert("a");
        trie.insert("ab");
        trie.insert("abc");

        List<String> allWords = trie.getAllWords();
        assertNotNull(allWords);
        assertEquals(3, allWords.size());
        assertTrue(allWords.contains("a"));
        assertTrue(allWords.contains("ab"));
        assertTrue(allWords.contains("abc"));
    }

    @Test
    void testLevenshteinDistance() {
        assertEquals(3, Trie.levenshteinDistance("kitten", "sitting"));
        assertEquals(1, Trie.levenshteinDistance("cat", "bat"));
        assertEquals(0, Trie.levenshteinDistance("same", "same"));
    }

    @Test
    void testGetSpellingSuggestions() {
        trie.insert("cat");
        trie.insert("bat");
        trie.insert("rat");
        trie.insert("hat");

        List<String> suggestions = trie.getSpellingSuggestions("cat");
        assertNotNull(suggestions);
        assertTrue(suggestions.contains("cat"));
        assertTrue(suggestions.contains("bat"));
        assertTrue(suggestions.contains("rat"));
        assertTrue(suggestions.contains("hat"));
    }

    @Test
    void testEmptyTrie() {
        List<String> allWords = trie.getAllWords();
        assertNotNull(allWords);
        assertTrue(allWords.isEmpty());
    }

    @Test
    void testInsertEmptyString() {
        assertTrue(trie.insert(""));
        List<String> allWords = trie.getAllWords();
        assertEquals(1, allWords.size());
        assertTrue(allWords.contains(""));
    }
}
