package com.mpms.relatorioacessibilidadecortec.util;

public class HeaderNames {

    private String name;

    public static final String[] headerNames = {
            "Acesso externo",
            "Sanitário",
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
            "Sala de Tecnologia",
            "Sala de Recursos",
            "Sala dos Professores",
            "Secretaria",
            "Outros Ambientes",
            "Equip. Administrativos",
            "Playground"
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

    public static Integer indexPosition(String name) {
        for (int j = 0; j < headerNames.length; j++) {
            boolean isEqual = name.equals(headerNames[j]);
            if (isEqual)
                return j;
        }
        return null;
    }
}
