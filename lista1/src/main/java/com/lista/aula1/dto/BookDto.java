package com.lista.aula1.dto;

import com.lista.aula1.model.Book;

public record BookDto(
        Long id,
        String title,
        String author,
        String language,
        Long firstPublished,
        Double sales,
        String genre
) {
    public BookDto(Book book) {
        this(
                book.getId(),
                book.getTitle(),
                book.getAuthor().getName(),
                book.getLanguage().getLanguage(),
                book.getFirstPublished(),
                book.getSales(),
                book.getGenre().getGenre()
        );
    }
}
