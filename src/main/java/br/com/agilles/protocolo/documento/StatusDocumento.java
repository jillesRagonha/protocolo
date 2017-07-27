package br.com.agilles.protocolo.documento;

/**
 * Created by jille on 03/07/2017.
 */
public enum StatusDocumento {
    RECEBIDO("Recebido"),
    PENDENTE("Pendente"),
    DESPACHADO("Despachado"),
    ARQUIVADO("Arquivado");

    private String descricao;

    public String getDescricao() {
        return descricao;
    }

    StatusDocumento(String descricao) {
        this.descricao = descricao;
    }


}
