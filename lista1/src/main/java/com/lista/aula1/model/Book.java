package com.lista.aula1.model;

import com.lista.aula1.dto.CreateBookDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "books")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @ManyToOne
    @JoinColumn(name = "id_author", nullable = false)
    private Author author;

    @ManyToOne
    @JoinColumn(name = "id_language", nullable = false)
    private Language language;

    @Column(name = "first_published", nullable = false)
    private Long firstPublished;

    @Column(name = "sales_in_millions", nullable = false)
    private Double sales;

    @ManyToOne
    @JoinColumn(name = "id_genre", nullable = false)
    private Genre genre;

    public Book(CreateBookDto dto) {
        this.title = dto.getTitle();
        this.firstPublished = dto.getFirstPublished();
        this.sales = dto.getSales();
    }

}
