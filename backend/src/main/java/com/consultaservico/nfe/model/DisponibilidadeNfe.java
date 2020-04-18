package com.consultaservico.nfe.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import com.consultaservico.nfe.shared.StatusDisponibilidade;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "nfe", schema = "consulta")
@EntityListeners(AuditingEntityListener.class)
@Getter
@NoArgsConstructor
@Setter
public class DisponibilidadeNfe implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(name = "ds_estado")
    private String dsEstado;

    @NotBlank
    @Column(name = "ds_autorizador")
    private String dsAutorizador;

    @Column(name = "ds_status_autorizacao")
    private StatusDisponibilidade dsStatusAutorizacao;

    @Column(name = "ds_status_retorno_autorizacao")
    private StatusDisponibilidade dsStatusRetornoAutorizacao;

    @Column(name = "ds_status_inutilizacao")
    private StatusDisponibilidade dsStatusInutilizacao;

    @Column(name = "ds_status_consulta_protocolo")
    private StatusDisponibilidade dsStatusConsultaProtocolo;

    @Column(name = "ds_status_servico")
    private StatusDisponibilidade dsStatusServico;

    @Column(name = "ds_tempo_medio")
    private String dsTempoMedio;

    @Column(name = "ds_status_consulta_cadastro")
    private StatusDisponibilidade dsStatusConsultaCadastro;

    @Column(name = "ds_status_recepcao_evento")
    private StatusDisponibilidade dsStatusRecepcaoEvento;

    @Column(name = "fg_ativo")
    private boolean fgAtivo;

    @DateTimeFormat(pattern = "yyyy/MM/dd  HH:mm")
    @Column(nullable = false, updatable = false, name = "created_attime")
    private LocalDateTime createdAtTime;

    @DateTimeFormat(pattern = "yyyy/MM/dd")
    @Column(nullable = false, updatable = false, name = "created_at")
    private Date createdAt;

    public LocalDateTime getCreatedAtTime() {
        return createdAtTime;
    }

    public void setCreatedAtTime(LocalDateTime createdAtTime) {
        this.createdAtTime = createdAtTime;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getDsAutorizador() {
        return dsAutorizador;
    }

    public void setDsAutorizador(String dsAutorizador) {
        this.dsAutorizador = dsAutorizador;
    }

    public boolean isFgAtivo() {
        return fgAtivo;
    }

    public void setFgAtivo(boolean fgAtivo) {
        this.fgAtivo = fgAtivo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDsEstado() {
        return dsEstado;
    }

    public void setDsEstado(String dsEstado) {
        this.dsEstado = dsEstado;
    }

    public StatusDisponibilidade getDsStatusAutorizacao() {
        return dsStatusAutorizacao;
    }

    public void setDsStatusAutorizacao(StatusDisponibilidade dsStatusAutorizacao) {
        this.dsStatusAutorizacao = dsStatusAutorizacao;
    }

    public StatusDisponibilidade getDsStatusRetornoAutorizacao() {
        return dsStatusRetornoAutorizacao;
    }

    public void setDsStatusRetornoAutorizacao(StatusDisponibilidade dsStatusRetornoAutorizacao) {
        this.dsStatusRetornoAutorizacao = dsStatusRetornoAutorizacao;
    }

    public StatusDisponibilidade getDsStatusInutilizacao() {
        return dsStatusInutilizacao;
    }

    public void setDsStatusInutilizacao(StatusDisponibilidade dsStatusInutilizacao) {
        this.dsStatusInutilizacao = dsStatusInutilizacao;
    }

    public StatusDisponibilidade getDsStatusConsultaProtocolo() {
        return dsStatusConsultaProtocolo;
    }

    public void setDsStatusConsultaProtocolo(StatusDisponibilidade dsStatusConsultaProtocolo) {
        this.dsStatusConsultaProtocolo = dsStatusConsultaProtocolo;
    }

    public StatusDisponibilidade getDsStatusServico() {
        return dsStatusServico;
    }

    public void setDsStatusServico(StatusDisponibilidade dsStatusServico) {
        this.dsStatusServico = dsStatusServico;
    }

    public String getDsTempoMedio() {
        return dsTempoMedio;
    }

    public void setDsTempoMedio(String dsTempoMedio) {
        this.dsTempoMedio = dsTempoMedio;
    }

    public StatusDisponibilidade getDsStatusConsultaCadastro() {
        return dsStatusConsultaCadastro;
    }

    public void setDsStatusConsultaCadastro(StatusDisponibilidade dsStatusConsultaCadastro) {
        this.dsStatusConsultaCadastro = dsStatusConsultaCadastro;
    }

    public StatusDisponibilidade getDsStatusRecepcaoEvento() {
        return dsStatusRecepcaoEvento;
    }

    public void setDsStatusRecepcaoEvento(StatusDisponibilidade dsStatusRecepcaoEvento) {
        this.dsStatusRecepcaoEvento = dsStatusRecepcaoEvento;
    }

}
