package com.consultaservico.nfe.shared;

public enum StatusDisponibilidade {
    DISPONIVEL("imagens/bola_verde_P.png", "DISPONIVEL"),
    INSTAVEL("imagens/bola_amarela_P.png", "INSTAVEL"),
    INDISPONIVEL("imagens/bola_vermelha_P.png", "INDISPONIVEL"),
    SEM_STATUS("","");

    public final String label;
    public final String value;

    private StatusDisponibilidade(String label, String value) {
        this.label = label;
        this.value = value;
    }

    public static StatusDisponibilidade valueOfLabel(String label) {
        for (StatusDisponibilidade e : values()) {
            if (e.label.equals(label)) {
                return e;
            }
        }
        return SEM_STATUS;
    }
}
