package com.lista.aula1.controller;

import com.lista.aula1.service.BookService;
import org.springframework.stereotype.Controller;

import java.io.IOException;

@Controller
public class BookController {
    private final BookService service;

    public BookController(BookService service) {
        this.service = service;
    }

    public void saveFromFile() throws IOException {
        service.saveFromFile();
    }

    public void listTop10Books() {
        service.listTop10Books();
    }

    public void listSumSalesByLanguage() {
        service.listSumSalesByLanguage();
    }

    public void listAuthors() {
        service.listAuthors();
    }

    public void listBooksByAuthor(String author) {
        service.listBooksByAuthor(author);
    }

    public void listGenres() {
        service.listGenres();
    }

    public void listBooksByGenres(String genre) {
        service.listBooksByGenre(genre);
    }

    public void listGenreWithSalesAbove(Long minValue) {
        service.listGenreWithSalesAbove(minValue);
    }
}
