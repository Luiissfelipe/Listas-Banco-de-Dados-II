package com.lista.aula1;

import com.lista.aula1.controller.BookController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class Aula1Application implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(Aula1Application.class, args);
	}

	@Autowired
	private BookController controller;

	@Override
	public void run(String... args) throws Exception {

		Scanner input = new Scanner(System.in);

		System.out.println("""
                
                ==============================================================
                                      LIVROS MAIS VENDIDOS
                ==============================================================
                """);

		int opcao;
		do {
			System.out.println("""
					Digite uma opção:
					
					1 - Salvar dados no banco
					2 - Top 10 livros mais vendidos
					3 - Vendas por idioma original
					4 - Livros por autor
					5 - Livros por genero
					6 - Genero com número mínimo de vendas
					""");

			opcao = input.nextInt();

			switch (opcao) {
				case 1:
					controller.saveFromFile();
					break;
				case 2:
					controller.listTop10Books();
					break;
				case 3:
					controller.listSumSalesByLanguage();
					break;
				case 4:
					controller.listAuthors();
					System.out.println("\nDigite o nome do autor:");
					String autor = input.next();
					controller.listBooksByAuthor(autor);
					break;
				case 5:
					controller.listGenres();
					System.out.println("\nDigite o genero:");
					String genre = input.next();
					controller.listBooksByGenres(genre);
					break;
				case 6:
					System.out.println("\nDigite o valor mínimo de vendas (em milhões) para filtrar os gêneros:");
					Long value = input.nextLong();
					controller.listGenreWithSalesAbove(value);
					break;
				case 0:
					break;
				default:
					System.out.println("Digite um valor válido!");
			}

		} while (opcao != 0);
	}
}
