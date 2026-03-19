package com.lista.aula1.dto;

import com.opencsv.bean.CsvBindByPosition;
import lombok.Data;

@Data
public class CreateBookDto {

        @CsvBindByPosition(position = 0)
        private String title;

        @CsvBindByPosition(position = 1)
        private String author;

        @CsvBindByPosition(position = 2)
        private String originalLanguage;

        @CsvBindByPosition(position = 3)
        private Long firstPublished;

        @CsvBindByPosition(position = 4)
        private Double sales;

        @CsvBindByPosition(position = 5)
        private String genre;
}