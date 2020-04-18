package com.consultaservico.nfe.repository;

import com.consultaservico.nfe.model.DisponibilidadeNfe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;


@Repository
@Transactional
public interface ConsultaServicoNfeRepository extends JpaRepository<DisponibilidadeNfe, Long> {

    @Query("select u from DisponibilidadeNfe as u where u.fgAtivo = true")
    List<DisponibilidadeNfe> findAllAtivos();

    @Query("select u from DisponibilidadeNfe as u where u.dsEstado like %:dsEstado and u.fgAtivo = true")
    Optional<DisponibilidadeNfe> getByEstado(@Param("dsEstado") String dsEstado);

    @Query("select u from DisponibilidadeNfe as u where  :dtInicial <= u.createdAt and :dtFinal >= u.createdAt group by u.dsEstado")
    List<DisponibilidadeNfe> getByData(@Param("dtInicial") Date dtInicial, @Param("dtFinal") Date dtFinal);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("update DisponibilidadeNfe as nfe set nfe.fgAtivo = false")
    void inactiveServicos();

//    SELECT
//            ds_estado,
//    COUNT(CASE WHEN ds_status_autorizacao in(1,2) or
//    ds_status_retorno_autorizacao in(1,2) or
//    ds_status_inutilizacao in(1,2) or
//    ds_status_consulta_protocolo in(1,2) or
//    ds_status_servico in(1,2) or
//    ds_status_consulta_cadastro in (1,2) or ds_status_recepcao_evento in (1,2)  THEN id END) as total
//    FROM
//            nfe
//    GROUP BY
//    ds_estado
//    ;

}
