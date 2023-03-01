package telran.test;

import org.junit.jupiter.api.Test;

import telran.util.Words;
import telran.util.WordsImpl;

import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

import org.junit.jupiter.api.BeforeEach;

public class WordsTest {
    private Words words;

    @BeforeEach
    public void setUp() {
        words = new WordsImpl();
    }

    @Test
    public void testAddWord() {
        // Add a word to the Words object
        boolean result = words.addWord("hello");
        assertTrue(result);

        // Check that the word was added
        List<String> wordsWithPrefix = words.getWordsByPrefix("h");
        assertEquals(Arrays.asList("hello"), wordsWithPrefix);

        // Add the same word again
        result = words.addWord("hello");
        assertFalse(result);

        // Check that the word wasn't added again
        wordsWithPrefix = words.getWordsByPrefix("h");
        assertEquals(Arrays.asList("hello"), wordsWithPrefix);
    }

    @Test
    public void testGetWordsByPrefix() {
        // Add some words with different prefixes
        words.addWord("hello");
        words.addWord("help");
        words.addWord("happy");
        words.addWord("hat");
        words.addWord("house");

        // Test some prefix searches
        List<String> wordsWithPrefix = words.getWordsByPrefix("h");
        assertEquals(Arrays.asList("hello", "help", "happy", "hat", "house"), wordsWithPrefix);

        wordsWithPrefix = words.getWordsByPrefix("he");
        assertEquals(Arrays.asList("hello", "help"), wordsWithPrefix);

        wordsWithPrefix = words.getWordsByPrefix("ho");
        assertEquals(Collections.singletonList("house"), wordsWithPrefix);

        wordsWithPrefix = words.getWordsByPrefix("xyz");
        assertEquals(Collections.emptyList(), wordsWithPrefix);
    }
}
