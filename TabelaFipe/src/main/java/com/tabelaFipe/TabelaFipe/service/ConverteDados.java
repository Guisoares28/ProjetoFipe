package com.tabelaFipe.TabelaFipe.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import models.Marcas;

import java.util.List;

public class ConverteDados implements IobterDados {


    @Override
    public <T> List<T> obterLista(String json, Class<T> classe) {
        ObjectMapper mapper = new ObjectMapper();
        CollectionType listMarcas = mapper.getTypeFactory()
                .constructCollectionType(List.class,classe);
        try{
            return mapper.readValue(json, listMarcas);
        }catch (JsonProcessingException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public <T> T obterDados(String json, Class<T> classe) throws JsonProcessingException {
        ObjectMapper mapper =  new ObjectMapper();
        var objeto = mapper.readValue(json,classe);
        return objeto;
    }


}
