package br.com.devinhouse.entity;

import java.time.LocalDateTime;

public class Acoes {

    private TipoAcaoEnum tipo;
    private Pessoa colaborador;
    private LocalDateTime dataHora;

    private Documento documento;

    private Integer opcaoRelatorio;

    public Acoes(TipoAcaoEnum tipo, Pessoa colaborador) {
        this.tipo = tipo;
        this.colaborador = colaborador;
        this.dataHora = LocalDateTime.now();
    }

    public Acoes(TipoAcaoEnum tipo, Pessoa colaborador, Documento documento) {
        this.tipo = tipo;
        this.colaborador = colaborador;
        this.dataHora = LocalDateTime.now();
        this.documento = documento;
    }

    public Acoes(TipoAcaoEnum tipo, Pessoa colaborador, Integer opcaoRelatorio) {
        this.tipo = tipo;
        this.colaborador = colaborador;
        this.dataHora = LocalDateTime.now();
        this.opcaoRelatorio = opcaoRelatorio;
    }

    public TipoAcaoEnum getTipo() {
        return tipo;
    }

    public Pessoa getColaborador() {
        return colaborador;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public Documento getDocumento() {
        return documento;
    }

    public Integer getOpcaoRelatorio() {
        return opcaoRelatorio;
    }

}
