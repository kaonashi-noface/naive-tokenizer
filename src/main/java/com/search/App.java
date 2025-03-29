package com.search;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.LinkedList;
import java.util.Set;
import java.util.HashSet;
import java.util.stream.Collectors;

import com.search.core.NaiveTokenizer;

public class App {
    public static void main(String[] args) {
        String rootPath = System.getProperty("user.dir");
        String fPath = rootPath + "/data/input-1_alice.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(fPath))) {
            NaiveTokenizer tokenizer = new NaiveTokenizer();
            String line;
            while ((line = br.readLine()) != null) {
                if(line.isBlank()) {
                    continue;
                }
                List<Character> strAsList = line.chars()
                    .mapToObj(i -> (char)i).collect(Collectors.toList());
                List<Character> chunk = new LinkedList<>(strAsList);
                chunk.add('\n');
                tokenizer.feed(chunk);
            }

            String token;
            Set<String> wordSet = new HashSet<>();
            while (!(token = tokenizer.getNextToken()).isEmpty()) {
                wordSet.add(token);
            }

            System.out.printf("Found %d words in File.", wordSet.size());
            System.out.println("All Words in File:");
            System.out.println(wordSet.toString());
        } catch (FileNotFoundException e) {
            System.err.println("Failed to open file at: "+ fPath);
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("Failed to read line from file: "+ fPath);
            e.printStackTrace();
        }
    }
}
