package com.tabelaFipe.TabelaFipe;

import com.tabelaFipe.TabelaFipe.service.ConsumirApi;
import com.tabelaFipe.TabelaFipe.service.ConverteDados;
import models.DadosMarca;
import models.Marcas;
import models.Veiculo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class TabelaFipeApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(TabelaFipeApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Scanner input = new Scanner(System.in);
		ConsumirApi consumoApi = new ConsumirApi();
		ConverteDados converteDados = new ConverteDados();
		System.out.println("Escolha qual modelo deseja: " +
				"Carro" +
				"Moto" +
				"Caminhão");
		System.out.println("\nQual tipo deseja pesquisar: ");
		String tipo = input.nextLine();
		String response = consumoApi.buscarDadosPorMarca(tipo);
		var listaMarcas = converteDados.obterLista(response, Marcas.class);
		listaMarcas.stream()
				.sorted(Comparator.comparing(Marcas::codigo))
				.forEach(System.out::println);
		System.out.println("\nSelecione uma marca pelo codigo: ");
		var marca = input.nextLine();
		String json = consumoApi.buscarModelos(tipo,marca);
		System.out.println(json);
		var veiculo = converteDados.obterDados(json,DadosMarca.class);
		System.out.println("Modelos da Marca");
		veiculo.modelos().stream()
				.sorted(Comparator.comparing(Marcas::codigo))
				.forEach(System.out::println);
		System.out.println("\nSelecione o Modelo do Veiculo: ");
		var modelo = input.nextLine();
		json = consumoApi.buscarTodosOsAnos(tipo,marca,modelo);
		List<Marcas> anos = converteDados.obterLista(json, Marcas.class);
		List<Veiculo> veiculos =  new ArrayList<>();
		for (int i = 0; i < anos.size(); i++) {
			json = consumoApi.buscarPorCadaAno(tipo,marca,modelo,anos.get(i).codigo());
			Veiculo veiculo1 = converteDados.obterDados(json,Veiculo.class);
			veiculos.add(veiculo1);
		}
		System.out.println("Todos os veiculos filtrados");
		veiculos.forEach(System.out::println);

	}
}
