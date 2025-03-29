package com.search.core;

import java.util.LinkedList;
import java.util.List;

public class NaiveTokenizer {
    List<List<Character>> buffer;
    private int cPtr;

    public NaiveTokenizer() {
        this.buffer = new LinkedList<>();
        this.cPtr = 0;
    }

    public void feed(List<Character> chunk) {
        this.buffer.add(chunk);
    }

    public String getNextToken() {
        int startIdx = this.moveToStart(this.cPtr);
        if(startIdx == -1) {
            return "";
        }

        int cPtr = startIdx;
        StringBuilder token = new StringBuilder();
        while(this.buffer.size() > 0) {
            List<Character> chunk = this.buffer.get(0);
            while (cPtr < chunk.size()) {
                char c = chunk.get(cPtr);
                if (this.isInvalidChar(c)) {
                    this.cPtr = cPtr;
                    return token.toString();
                }
                
                if(this.isLetter(c)) {
                    token.append(Character.toLowerCase(c));
                }
                cPtr++;
            }
            this.buffer.remove(0);
            cPtr = 0;
        }
        return token.toString();
    }

    public int moveToStart(int cPtr) {
        int startIdx = cPtr;
        while (this.buffer.size() > 0) {
            List<Character> chunk = this.buffer.get(0);
            while (startIdx < chunk.size()) {
                char c = chunk.get(startIdx);
                if (this.isLetter(c)) {
                    return startIdx;
                }
                startIdx++;
            }
            this.buffer.remove(0);
            startIdx = 0;
        }
        return -1;
    }

    private boolean isInvalidChar(char c) {
        return !this.isLetter(c) && c != '\'';
    }

    private boolean isLetter(char c) {
        return Character.isLetter(c);
    }
}
