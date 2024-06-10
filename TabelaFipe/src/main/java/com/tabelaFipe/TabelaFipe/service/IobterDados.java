package com.tabelaFipe.TabelaFipe.service;


import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

public interface IobterDados {

    <T> List<T> obterLista(String json, Class<T> classe);

    <T> T obterDados(String json, Class<T> classe) throws JsonProcessingException;
}
