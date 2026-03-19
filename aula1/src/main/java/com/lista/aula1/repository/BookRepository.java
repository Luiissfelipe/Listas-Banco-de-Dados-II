package com.lista.aula1.repository;

import com.lista.aula1.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    // Busca os 10 livros mais vendidos do banco de dados, trazendo junto o nome do autor
    @Query("SELECT b.title, a.name, b.sales FROM Book b " +
            "JOIN Author a ON b.author.id = a.id " +
            "ORDER BY b.sales DESC LIMIT 10")
    List<Object[]> findTop10Books();

    // Calcula o total de vendas agrupado por cada idioma original
    @Query("SELECT l.language, SUM(b.sales) as totalSales " +
            "FROM Book b " +
            "JOIN b.language l " +
            "GROUP BY l.language " +
            "ORDER BY totalSales DESC")
    List<Object[]> sumSalesByLanguage();

    // Retorna uma lista contendo apenas os nomes de todos os autores cadastrados em ordem alfabética
    @Query("SELECT a.name FROM Author a ORDER BY a.name")
    List<String> findAuthors();

    // Busca todos os livros escritos por um autor que tenha o trecho de texto digitado no nome,
    // ignorando se é maiúsculo ou minúsculo
    List<Book> findByAuthorNameContainingIgnoreCase(String author);

    // Retorna uma lista contendo apenas os nomes dos generos cadastrados em ordem alfabética
    @Query("SELECT g.genre FROM Genre g ORDER BY g.genre")
    List<String> findGenres();

    // Retorna a lista de livros que pertencem a um gênero cujo nome contenha o termo pesquisado
    List<Book> findByGenreGenreContainingIgnoreCase(String genre);

    // Agrupa os livros por gênero, soma as vendas e filtra o resultado,
    // entregando apenas os gêneros que, somados, ultrapassam o valor que passar na variável
    @Query("SELECT g.genre, SUM(b.sales) AS totalSales FROM Book b " +
            "JOIN b.genre g " +
            "GROUP BY g.genre " +
            "HAVING SUM(b.sales) > :minValue " +
            "ORDER BY totalSales DESC")
    List<Object[]> findGenreWithSalesAbove(Long minValue);

}
