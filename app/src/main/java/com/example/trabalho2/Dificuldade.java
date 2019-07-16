package com.example.trabalho2;

public enum Dificuldade { MUITOFACIL(0), FACIL(1), MEDIO(2), DIFICIL(3), MUITODIFICIL(4);

    public int valDificuldade;
    Dificuldade(int i) {
        valDificuldade = i;
    }

    public int getValor(){
        return valDificuldade;
    }

    public static Dificuldade getDificuldade(String s){
        if (s.equals("MUITOFACIL")) {
            return Dificuldade.MUITOFACIL;
        } else if (s.equals("FACIL")) {
            return Dificuldade.FACIL;
        } else if (s.equals("MEDIO")) {
            return Dificuldade.MEDIO;
        }else if (s.equals("DIFICIL")) {
            return Dificuldade.DIFICIL;
        }else if (s.equals("MUITODIFICIL")) {
            return Dificuldade.MUITODIFICIL;
        } else {
            return null;
        }
    }

    public static String getDificuldadeName(String s){
        if (s.equals("MUITOFACIL")) {
            return Dificuldade.MUITOFACIL.name();
        } else if (s.equals("FACIL")) {
            return Dificuldade.FACIL.name();
        } else if (s.equals("MEDIO")) {
            return Dificuldade.MEDIO.name();
        }else if (s.equals("DIFICIL")) {
            return Dificuldade.DIFICIL.name();
        }else if (s.equals("MUITODIFICIL")) {
            return Dificuldade.MUITODIFICIL.name();
        } else {
            return null;
        }
    }
}
