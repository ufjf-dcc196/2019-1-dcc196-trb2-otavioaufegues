package com.example.trabalho2;

public class Tarefa {
    private String titulo_tarefa;
    private String descricao_tarefa;
    private Dificuldade dificuldade;
    private String dt_limite;
    private String updated_at;
    private String status_tarefa;

    public Tarefa(String titulo, Dificuldade dificuldade, String updated_at, String status) {
        this.titulo_tarefa = titulo;
        this.dificuldade = dificuldade;
        this.updated_at = updated_at;
        this.status_tarefa = status;
    }

    public String getTitulo() {
        return titulo_tarefa;
    }

    public void setTitulo(String titulo) {
        this.titulo_tarefa = titulo;
    }

    public String getDescricao() {
        return descricao_tarefa;
    }

    public void setDescricao(String descricao) {
        this.descricao_tarefa = descricao;
    }

    public Dificuldade getDificuldade() {
        return dificuldade;
    }

    public void setDificuldade(Dificuldade dificuldade) {
        this.dificuldade = dificuldade;
    }

    public String getDt_limite() {
        return dt_limite;
    }

    public void setDt_limite(String dt_limite) {
        this.dt_limite = dt_limite;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getStatus() {
        return status_tarefa;
    }

    public void setStatus(String status) {
        this.status_tarefa = status;
    }
}
