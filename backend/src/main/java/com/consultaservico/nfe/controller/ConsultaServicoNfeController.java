package com.consultaservico.nfe.controller;

import com.consultaservico.nfe.model.DisponibilidadeNfe;
import com.consultaservico.nfe.model.TotalIndisponibilidadeNfe;
import com.consultaservico.nfe.repository.ConsultaServicoNfeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/consultanfe")
public class ConsultaServicoNfeController {


    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Date.class,
                new CustomDateEditor(new SimpleDateFormat("yyyy/MM/dd"), true, 10));
    }

    @Autowired
    ConsultaServicoNfeRepository consultaServicoNfeRepository;

    @GetMapping("/all")
    public List<DisponibilidadeNfe> getAll() {
        return consultaServicoNfeRepository.findAllAtivos();
    }

    @GetMapping("/getByEstado")
    public Optional<DisponibilidadeNfe> getByEstado(@RequestParam String dsEstado) {
        return consultaServicoNfeRepository.getByEstado(dsEstado);
    }

    @PostMapping("/getByData")
    public List<DisponibilidadeNfe> getByData(@RequestParam Date dtInicial, @RequestParam Date dtFinal) {
        Date dtinit = addHoursToJavaUtilDate(dtInicial,00);
        Date dtFim = addHoursToJavaUtilDate(dtFinal,23);
        List<DisponibilidadeNfe> teste = consultaServicoNfeRepository.getByData(dtinit, dtFim);
        return teste;
    }

    public Date addHoursToJavaUtilDate(Date date, int hours) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR_OF_DAY, hours);
        return calendar.getTime();
    }

    @GetMapping("/getTotalIndisponibilidade")
    public List<TotalIndisponibilidadeNfe> getTotalIndisponibilidade() {
        return consultaServicoNfeRepository.getTotalIndisponibilidade();
    }

}

