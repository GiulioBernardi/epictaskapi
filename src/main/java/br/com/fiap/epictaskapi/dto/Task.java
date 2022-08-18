package br.com.fiap.epictaskapi.dto;


import javax.persistence.Entity;
import java.util.Objects;

public record Task (

        String titulo,
        String descricao,
        int pontuacao,
        int status
) {
//    public Task() {
//    }
//
//    public Task(String titulo, String descricao) {
//        this.titulo = titulo;
//        this.descricao = descricao;
//    }
//
//    public Task(String titulo, String descricao, int pontuacao, int status) {
//        this.titulo = titulo;
//        this.descricao = descricao;
//        this.pontuacao = pontuacao;
//        this.status = status;
//    }
//
//    public String getTitulo() {
//        return titulo;
//    }
//
//    public void setTitulo(String titulo) {
//        this.titulo = titulo;
//    }
//
//    public String getDescricao() {
//        return descricao;
//    }
//
//    public void setDescricao(String descricao) {
//        this.descricao = descricao;
//    }
//
//    public int getPontuacao() {
//        return pontuacao;
//    }
//
//    public void setPontuacao(int pontuacao) {
//        this.pontuacao = pontuacao;
//    }
//
//    public int getStatus() {
//        return status;
//    }
//
//    public void setStatus(int status) {
//        this.status = status;
//    }
//
//    @Override
//    public String toString() {
//        return "Task{" +
//                "titulo='" + titulo + '\'' +
//                ", descricao='" + descricao + '\'' +
//                ", pontuacao=" + pontuacao +
//                ", status=" + status +
//                '}';
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Task task = (Task) o;
//        return pontuacao == task.pontuacao && status == task.status && Objects.equals(titulo, task.titulo) && Objects.equals(descricao, task.descricao);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(titulo, descricao, pontuacao, status);
//    }
}
