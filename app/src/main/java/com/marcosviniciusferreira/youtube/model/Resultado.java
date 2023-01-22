package com.marcosviniciusferreira.youtube.model;

import java.util.List;

public class Resultado {

    //Vamos criar os atributos públicos então não precisaremos
    //dos métodos GETs e SETs, pois vamos usar os atributos diretamente
    //na MainAcitivy
    //Mas também podemos usar os getters e setters que o resultado seria o mesmo...

    public String regionCode;
    public PageInfo pageInfo;
    public List<Item> items;

}
