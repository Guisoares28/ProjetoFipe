package principal;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.tabelaFipe.TabelaFipe.service.ConsumirApi;
import com.tabelaFipe.TabelaFipe.service.ConverteDados;
import models.Dados;
import models.Modelos;
import models.Veiculo;

import java.io.IOException;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Principal {
    private Scanner input = new Scanner(System.in);
    private ConsumirApi consumo = new ConsumirApi();
    private ConverteDados conversor = new ConverteDados();
    private String base = "https://parallelum.com.br/fipe/api/v1/";

    public String exibir_menu(){
        String menu = "OPÇÕES" +
                "\nCarro" +
                "\nMoto" +
                "\nCaminhão";

        return menu;
    }

    public void principal() {
        System.out.println(exibir_menu());
        System.out.println("\nEscolha um tipo: ");
        var escolha = input.nextLine();
        String json = null;
        String tipo = null;
        if (escolha.toLowerCase().contains("carr")){
            try {
                tipo = "carros";
                json = consumo.buscarDados(base + tipo + "/marcas");
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        } else if (escolha.toLowerCase().contains("mot")) {
            try {
                tipo = "motos";
                json = consumo.buscarDados(base + tipo + "/marcas");
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }else {
            try {
                tipo = "caminhoes";
                json = consumo.buscarDados(base + tipo + "/marcas");
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        var listaModelos = conversor.obterLista(json, Dados.class);
        listaModelos.stream()
                        .sorted(Comparator.comparing(Dados::nome))
                        .forEach(System.out::println);
        System.out.println("\nEscolha a marca do veiculo pelo codigo: ");
        var codigoMarca = input.nextLine();
        try {
            json = consumo.buscarDados(base + tipo + "/marcas/" + codigoMarca + "/modelos");
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        try {
            var listModels = conversor.obterDados(json, Modelos.class);
            listModels.modelos().stream()
                    .sorted(Comparator.comparing(Dados::nome))
                    .forEach(System.out::println);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        System.out.println("\nPesquisa o modelo por uma parte do nome: ");
        var modelo = input.nextLine();
        try {
            var listModels = conversor.obterDados(json, Modelos.class);
            List<Dados> listaDados = listModels.modelos().stream()
                    .filter(dados -> dados.nome().toLowerCase().contains(modelo.toLowerCase()))
                    .collect(Collectors.toList());
            System.out.println("\nModelos filtrados");
            listaDados.forEach(System.out::println);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        System.out.println("\nSelecione o modelo por codigo: ");
        var codigoModelo = input.nextLine();
        List<Veiculo> veiculos = new ArrayList<>();
        try {
            json = consumo.buscarDados(base + tipo + "/marcas/" + codigoMarca + "/modelos/" + codigoModelo + "/anos");
            List<Dados> anos = conversor.obterLista(json,Dados.class);
            for (int i = 0; i < anos.size(); i++) {
                String endereco = base + tipo + "/marcas/" + codigoMarca + "/modelos/" + codigoModelo + "/anos/" + anos.get(i).codigo();
                json = consumo.buscarDados(endereco);
                Veiculo veiculo = conversor.obterDados(json, Veiculo.class);
                veiculos.add(veiculo);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("\nTodos os veículos filtrados com avaliações por ano: ");
        veiculos.forEach(System.out::println);
    }


}
