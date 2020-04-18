package com.consultaservico.nfe.shared;

import java.util.ArrayList;
import java.util.Arrays;

public enum EstadoAutorizador {
    SVAN(new ArrayList<String>(Arrays.asList("MA"))),
    SVRS(new ArrayList<String>(Arrays.asList("RN", "PB", "SC", "AC", "AL", "AP", "DF", "ES", "PB", "PI", "RJ", "RO", "RR", "SC", "SE", "TO"))),
    SVCAN(new ArrayList<String>(Arrays.asList("AC", "AL", "AP", "DF", "ES", "MG", "PA", "PB", "PI", "RJ", "RN", "RO", "RR", "RS", "SC", "SE", "SP", "TO"))),
    SVCRS(new ArrayList<String>(Arrays.asList("AM", "BA", "CE", "GO", "MA", "MS", "MT", "PE", "PR")));
    public final ArrayList<String> value;

    private EstadoAutorizador(ArrayList<String> value) {
        this.value = value;
    }

}
