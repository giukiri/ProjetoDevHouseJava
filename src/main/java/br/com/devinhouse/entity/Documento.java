package br.com.devinhouse.entity;

import br.com.devinhouse.util.GeradorIdentificador;

import java.time.LocalDateTime;

public class Documento {
    private Integer identificador;
    private Pessoa colaboradorResponsavel;
    private Pessoa coloboradorCriouDocumento;
    private String link;
    private EstadoEnum estado;
    private LocalDateTime dataCriacao;

    public Documento(Pessoa coloboradorCriouDocumento, String link) {
      criarDocumento(coloboradorCriouDocumento,link);
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public Integer getIdentificador() {
        return identificador;
    }

    public Pessoa getColaboradorResponsavel() {
        return colaboradorResponsavel;
    }

    public void setColaboradorResponsavel(Pessoa colaboradorResponsavel) {
        this.colaboradorResponsavel = colaboradorResponsavel;
    }

    public Pessoa getColoboradorCriouDocumento() {
        return coloboradorCriouDocumento;
    }

    public void setColoboradorCriouDocumento(Pessoa coloboradorCriouDocumento) {
        this.coloboradorCriouDocumento = coloboradorCriouDocumento;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public EstadoEnum getEstado() {
        return estado;
    }

    public void setEstado(EstadoEnum estado) {
        this.estado = estado;
    }

    private void criarDocumento(Pessoa colaboradorCriouDocumento, String link){
        this.identificador = GeradorIdentificador.getProximoIdentificador();
        this.link = link;
        this.coloboradorCriouDocumento = colaboradorCriouDocumento;
        this.colaboradorResponsavel = colaboradorCriouDocumento;
        this.dataCriacao = LocalDateTime.now();
        this.estado = EstadoEnum.ATIVO;
    }
}
