package br.com.agilles.protocolo.documento;

/**
 * Created by jille on 23/05/2017.
 */
public enum TipoDocumento {
    OFICIO ("Ofício"),
    CI("Comunicação Interna"),
    RC("Relatório de Comunicação"),
    PROCESSO("Processo"),
    OUTROS("Outros");

    private String descricao;

    TipoDocumento(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
