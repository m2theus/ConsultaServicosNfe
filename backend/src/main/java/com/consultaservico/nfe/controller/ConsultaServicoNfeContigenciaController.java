package com.consultaservico.nfe.controller;

import com.consultaservico.nfe.model.DisponibilidadeNfeContigencia;
import com.consultaservico.nfe.model.TotalIndisponibilidadeNfe;
import com.consultaservico.nfe.repository.ConsultaServicoNfeContigenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Created by Matheus Molinete on 16/04/20.
 */
@RestController
@RequestMapping("/consultanfe/contigencia")
public class ConsultaServicoNfeContigenciaController {

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Date.class,
                new CustomDateEditor(new SimpleDateFormat("yyyy/MM/dd"), true, 10));
    }

    @Autowired
    ConsultaServicoNfeContigenciaRepository consultaServicoNfeContigenciaRepository;

    @GetMapping("/all")
    public List<DisponibilidadeNfeContigencia> getAll() {
        return consultaServicoNfeContigenciaRepository.findAllAtivos();
    }

    @GetMapping("/getByEstado")
    public Optional<DisponibilidadeNfeContigencia> getByEstado(@RequestParam String dsEstado) {
        return consultaServicoNfeContigenciaRepository.getByEstado(dsEstado);
    }

    @PostMapping("/getByData")
    public List<DisponibilidadeNfeContigencia> getByData(@RequestParam Date dtInicial, @RequestParam Date dtFinal) {
        Date dtinit = addHoursToJavaUtilDate(dtInicial, 00);
        Date dtFim = addHoursToJavaUtilDate(dtFinal, 23);
        return consultaServicoNfeContigenciaRepository.getByData(dtinit, dtFim);
    }

    /**
     * Método para ajustar horas de data inicial e final
     */
    public Date addHoursToJavaUtilDate(Date date, int hours) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR_OF_DAY, hours);
        return calendar.getTime();
    }

    @GetMapping("/getTotalIndisponibilidade")
    public List<TotalIndisponibilidadeNfe> getTotalIndisponibilidade() {
        return consultaServicoNfeContigenciaRepository.getTotalIndisponibilidade();
    }

}