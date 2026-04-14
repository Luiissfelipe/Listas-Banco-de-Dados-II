package com.lista.aula1.service;

import com.lista.aula1.dto.BookDto;
import com.lista.aula1.dto.CreateBookDto;
import com.lista.aula1.model.Author;
import com.lista.aula1.model.Book;
import com.lista.aula1.model.Genre;
import com.lista.aula1.model.Language;
import com.lista.aula1.repository.AuthorRepository;
import com.lista.aula1.repository.BookRepository;
import com.lista.aula1.repository.GenreRepository;
import com.lista.aula1.repository.LanguageRepository;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final GenreRepository genreRepository;
    private final LanguageRepository languageRepository;

    public BookService(BookRepository bookRepository,
                       AuthorRepository authorRepository,
                       GenreRepository genreRepository,
                       LanguageRepository languageRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.genreRepository = genreRepository;
        this.languageRepository = languageRepository;
    }

    @Transactional
    public void saveFromFile() {
        Path path = Paths.get("best-selling-books.csv");

        try (Reader reader = Files.newBufferedReader(path)) {
            CsvToBean<CreateBookDto> csvToBean = new CsvToBeanBuilder<CreateBookDto>(reader)
                    .withType(CreateBookDto.class)
                    .withSkipLines(1)
                    .withSeparator(',')
                    .build();

            List<CreateBookDto> dtoList = csvToBean.parse();

            for (CreateBookDto dto : dtoList) {
                String authorName = dto.getAuthor().trim();
                Author author = authorRepository.findByNameIgnoreCase(authorName)
                        .orElseGet(() -> authorRepository.save(new Author(authorName)));

                String languageName = dto.getOriginalLanguage().trim();
                Language language = languageRepository.findByLanguageIgnoreCase(languageName)
                        .orElseGet(() -> languageRepository.save(new Language(languageName)));

                String genreName;
                if (dto.getGenre().trim().isBlank()) {
                    genreName = "Not specified";
                } else {
                    genreName = dto.getGenre().trim();
                }
                Genre genre = genreRepository.findByGenreIgnoreCase(genreName)
                        .orElseGet(() -> genreRepository.save(new Genre(genreName)));

                Book book = new Book(dto);
                book.setAuthor(author);
                book.setLanguage(language);
                book.setGenre(genre);

                bookRepository.save(book);
            }

            System.out.println(dtoList.size() + " livros foram salvos com sucesso!");

        } catch (Exception e) {
            System.out.println("Erro ao processar o CSV: " + e.getMessage());
        }
    }

    public void listTop10Books() {

        List<Object[]> books = bookRepository.findTop10Books();

        System.out.println("""
                
                ==============================================================
                              TOP 10 LIVROS MAIS VENDIDOS
                ==============================================================
                """);

        books.forEach(b -> System.out.println(
                "Titulo: " + b[0] + " - Autor: " + b[1] + " - " + b[2] + " milhões de vendas"));
    }

    public void listSumSalesByLanguage() {
        List<Object[]> language = bookRepository.sumSalesByLanguage();

        System.out.println("""
                
                ==============================================================
                                 VENDAS POR IDIOMA ORIGINAL
                ==============================================================
                """);

        language.forEach(l -> System.out.println(
                "Idioma original: " + l[0] + " - " + l[1] + " milhões de vendas"));
    }

    public void listAuthors() {
        List<String> authors = bookRepository.findAuthors();

        System.out.println("""
                
                ==============================================================
                                         AUTORES
                ==============================================================
                """);

        authors.forEach(System.out::println);
    }

    public void listBooksByAuthor(String author) {
        List<BookDto> books = bookRepository.findByAuthorNameContainingIgnoreCase(author.toLowerCase())
                .stream()
                .map(BookDto::new)
                .toList();

        System.out.printf("""
                
                ==============================================================
                              LIVROS DO AUTOR %s
                ==============================================================
                
                """, author.toUpperCase());

        books.forEach(b -> System.out.println(
                "Titulo: " + b.title() + " - Genero: " + b.genre() + " - " + b.sales() + " milhões de vendas"));
    }

    public void listGenres() {
        List<String> genres = bookRepository.findGenres();

        System.out.println("""
                
                ==============================================================
                                         GENEROS
                ==============================================================
                """);

        genres.forEach(System.out::println);
    }

    public void listBooksByGenre(String genre) {
        List<BookDto> books = bookRepository.findByGenreGenreContainingIgnoreCase(genre.toLowerCase())
                .stream()
                .map(BookDto::new)
                .toList();

        System.out.printf("""
                
                ==============================================================
                              LIVROS DO GENERO %s
                ==============================================================
                
                """, genre.toUpperCase());

        books.forEach(b -> System.out.println(
                "Titulo: " + b.title() + " - Autor: " + b.author() + " - " + b.sales() + " milhões de vendas"));
    }

    public void listGenreWithSalesAbove(Long minValue) {
        List<Object[]> genres = bookRepository.findGenreWithSalesAbove(minValue);

        System.out.printf("""
                
                ==============================================================
                            TOTAL DE VENDAS POR GENERO ACIMA DE %d
                ==============================================================
                
                """, minValue);

        genres.forEach(g -> System.out.println(
                g[0] + " - " + g[1] + " milhões de vendas")
        );
    }
}