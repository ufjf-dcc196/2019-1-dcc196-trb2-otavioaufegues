package com.example.trabalho2;

public enum Status { AFAZER(0), EMEXECUCAO(1), BLOQUEADA(2), CONCLUIDA(3);

    public int valStatus;
    Status(int i) {
        valStatus = i;
    }
    public int getValor(){
        return valStatus;
    }

    public static Status getStatus(String s){
        if (s.equals("AFAZER")) {
            return Status.AFAZER;
        } else if (s.equals("EMEXECUCAO")) {
            return Status.EMEXECUCAO;
        } else if (s.equals("BLOQUEADA")) {
            return Status.BLOQUEADA;
        }else if (s.equals("CONCLUIDA")) {
            return Status.CONCLUIDA;
        } else {
            return null;
        }
    }
}
