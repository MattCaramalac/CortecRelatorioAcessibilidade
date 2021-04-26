package com.mpms.relatorioacessibilidadecortec.util;

public class HeaderNames {

    private String name;

    public static final HeaderNames[] headerNames = {
            new HeaderNames("Acesso externo"),
            new HeaderNames("Bebedouro"),
            new HeaderNames("Biblioteca"),
            new HeaderNames("Calçada"),
            new HeaderNames("Coordenação"),
            new HeaderNames("Diretoria"),
            new HeaderNames("Escada"),
            new HeaderNames("Estacionamento"),
            new HeaderNames("Rampa"),
            new HeaderNames("Refeitório"),
            new HeaderNames("Sala de Aula"),
            new HeaderNames("Sala de Informática"),
            new HeaderNames("Sala de Recursos"),
            new HeaderNames("Sala dos Professores"),
            new HeaderNames("Secretaria")
    };

    private HeaderNames (String location){
        this.name = location;
    }

    public String getName(){
        return name;
    }

    @Override
    public String toString(){
        return this.name;
    }
}
