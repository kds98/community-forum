package com.ll.domain.quotes;

import lombok.Getter;
import lombok.Setter;

public class Quotes {
    @Getter
    private int id;
    @Getter@Setter
    private String content;
    @Getter@Setter
    private String author;

    public Quotes(int id, String content, String author) {
        this.id = id;
        this.content = content;
        this.author = author;
    }
}
