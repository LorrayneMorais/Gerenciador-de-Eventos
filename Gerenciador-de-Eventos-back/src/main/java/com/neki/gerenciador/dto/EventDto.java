package com.neki.gerenciador.dto;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class EventDto {

    private Long adminId; 
    private String nomeEvento;
    private LocalDate dataEvento;
    private String descricao;
    private String imagem;
    private String location;

    public EventDto(Long adminId, String nomeEvento, LocalDate dataEvento, String descricao, String imagem, String location) {
        this.adminId = adminId;
        this.nomeEvento = nomeEvento;
        this.dataEvento = dataEvento;
        this.descricao = descricao;
        this.imagem = imagem;
        this.location = location;
    }

    public Long getAdminId() {
        return adminId;
    }

    public void setAdminId(Long adminId) {
        this.adminId = adminId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public String getNomeEvento() {
        return nomeEvento;
    }

    public void setNomeEvento(String nomeEvento) {
        this.nomeEvento = nomeEvento;
    }

    public LocalDate getDataEvento() {
        return dataEvento;
    }

    public void setDataEvento(LocalDate dataEvento) {
        this.dataEvento = dataEvento;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @JsonIgnore
    public String getDataEventoFormatada() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return dataEvento.format(formatter);
    }
}
