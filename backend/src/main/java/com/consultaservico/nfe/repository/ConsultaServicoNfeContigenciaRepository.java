package com.consultaservico.nfe.repository;

import com.consultaservico.nfe.model.DisponibilidadeNfeContigencia;
import com.consultaservico.nfe.model.TotalIndisponibilidadeNfe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Created by Matheus Molinete on 16/04/20.
 */
@Repository
public interface ConsultaServicoNfeContigenciaRepository extends JpaRepository<DisponibilidadeNfeContigencia, Long> {

    @Query("select u from DisponibilidadeNfeContigencia as u where u.fgAtivo = true")
    List<DisponibilidadeNfeContigencia> findAllAtivos();

    @Query("select u from DisponibilidadeNfeContigencia as u where u.dsEstado like %:dsEstado and u.fgAtivo = true")
    Optional<DisponibilidadeNfeContigencia> getByEstado(@Param("dsEstado") String dsEstado);

    @Query("select u from DisponibilidadeNfeContigencia as u where  :dtInicial <= u.createdAt and :dtFinal >= u.createdAt group by u.dsEstado")
    List<DisponibilidadeNfeContigencia> getByData(@Param("dtInicial") Date dtInicial, @Param("dtFinal") Date dtFinal);

    @Query("SELECT nfe.dsEstado as dsEstado ,COUNT(CASE WHEN nfe.dsStatusAutorizacao in(1,2) or nfe.dsStatusRetornoAutorizacao in(1,2) or nfe.dsStatusInutilizacao in(1,2) or nfe.dsStatusConsultaProtocolo in(1,2) or nfe.dsStatusServico in(1,2) or nfe.dsStatusConsultaCadastro in (1,2) or nfe.dsStatusRecepcaoEvento in (1,2)  THEN nfe.id END) as total FROM DisponibilidadeNfeContigencia nfe GROUP BY nfe.dsEstado")
    List<TotalIndisponibilidadeNfe> getTotalIndisponibilidade();

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("update DisponibilidadeNfeContigencia as nfe set nfe.fgAtivo = 0")
    void inactiveServicos();
}
