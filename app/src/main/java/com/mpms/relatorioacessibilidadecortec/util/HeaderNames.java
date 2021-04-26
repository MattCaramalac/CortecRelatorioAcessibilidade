package com.mpms.relatorioacessibilidadecortec.util;

public class HeaderNames {

    private String name;

//    public void HeaderNames() {
//    }
//

    public static final String[] headerNames = {
            "Acesso externo",
            "Bebedouro",
            "Biblioteca",
            "Calçada",
            "Coordenação",
            "Diretoria",
            "Escada",
            "Estacionamento",
            "Rampa",
            "Refeitório",
            "Sala de Aula",
            "Sala de Informática",
            "Sala de Recursos",
            "Sala dos Professores",
            "Secretaria"
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
