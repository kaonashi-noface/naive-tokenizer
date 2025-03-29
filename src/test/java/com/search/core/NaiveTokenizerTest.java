package com.search.core;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.stream.Collectors;

public class NaiveTokenizerTest {

    static List<Character> stringToList(String str) {
        return str.chars()
            .mapToObj(c -> (char) c)
            .collect(Collectors.toList());
    }
    
    @Test
    public void shouldGetSingleTokenWhenSingleChunk() {
        // Arrange
        String expectedToken = "hello";
        NaiveTokenizer tokenizer = new NaiveTokenizer();
        List<Character> chunk = stringToList(expectedToken);
        tokenizer.feed(chunk);

        // Act
        String actualToken = tokenizer.getNextToken();
        
        // Assert
        assertEquals(expectedToken, actualToken);
    }

    @Test
    public void shouldGetManyTokensWhenSingleChunk() {
        // Arrange
        String expectedFirstToken = "hello";
        String expectedSecondToken = "world";
        NaiveTokenizer tokenizer = new NaiveTokenizer();
        List<Character> chunk = stringToList(expectedFirstToken + " " + expectedSecondToken);
        tokenizer.feed(chunk);

        // Act
        String actualFirstToken = tokenizer.getNextToken();
        String actualSecondToken = tokenizer.getNextToken();

        // Assert
        assertEquals(expectedFirstToken, actualFirstToken);
        assertEquals(expectedSecondToken, actualSecondToken);
    }

    @Test
    public void shouldGetManyTokensWhenManyChunks() {
        // Arrange
        String[] parts = { "      hello  \n", "    my  name   \n", "   is   inigo    montoya  \n" };
        NaiveTokenizer tokenizer = new NaiveTokenizer();
        for (String part : parts) {
            List<Character> chunk = stringToList(part);
            tokenizer.feed(chunk);
        }

        // Act and Assert
        assertEquals("hello", tokenizer.getNextToken());
        assertEquals("my", tokenizer.getNextToken());
        assertEquals("name", tokenizer.getNextToken());
        assertEquals("is", tokenizer.getNextToken());
        assertEquals("inigo", tokenizer.getNextToken());
        assertEquals("montoya", tokenizer.getNextToken());
    }

    @Test
    public void shouldGetManyTokensWhenChunkHasDifferentCases() {
        // Arrange
        NaiveTokenizer tokenizer = new NaiveTokenizer();
        List<Character> chunk = stringToList("tHiS sHoUlD PaSS");
        tokenizer.feed(chunk);

        // Act and Assert
        assertEquals("this", tokenizer.getNextToken());
        assertEquals("should", tokenizer.getNextToken());
        assertEquals("pass", tokenizer.getNextToken());
    }

    @Test
    public void shouldGetManyTokensWhenChunkHasSymbols() {
        // Arrange
        String[] parts = { "`But it's no use now,' \n", "thought poor Alice\n" };
        NaiveTokenizer tokenizer = new NaiveTokenizer();
        for (String part : parts) {
            List<Character> chunk = stringToList(part);
            tokenizer.feed(chunk);
        }

        // Act and Assert
        assertEquals("but", tokenizer.getNextToken());
        assertEquals("its", tokenizer.getNextToken());
        assertEquals("no", tokenizer.getNextToken());
        assertEquals("use", tokenizer.getNextToken());
        assertEquals("now", tokenizer.getNextToken());
        assertEquals("thought", tokenizer.getNextToken());
        assertEquals("poor", tokenizer.getNextToken());
        assertEquals("alice", tokenizer.getNextToken());
    }
}
