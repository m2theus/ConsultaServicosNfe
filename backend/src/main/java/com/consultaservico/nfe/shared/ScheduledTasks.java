
package com.consultaservico.nfe.shared;

import com.consultaservico.nfe.model.DisponibilidadeNfe;
import com.consultaservico.nfe.model.DisponibilidadeNfeContigencia;
import com.consultaservico.nfe.repository.ConsultaServicoNfeContigenciaRepository;
import com.consultaservico.nfe.repository.ConsultaServicoNfeRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Matheus Molinete on 16/04/20.
 */
@Component
@EnableScheduling
@Service
public class ScheduledTasks {
    @Autowired
    private ConsultaServicoNfeRepository consultaServicoNfeRepository;

    @Autowired
    private ConsultaServicoNfeContigenciaRepository consultaServicoNfeContigenciaRepository;

    /**
     * Task criada para buscar dados de disponibilidade de serviços a cada 5 minutos, por meio da biblioteca jsoup
     */
    @Scheduled(fixedRate = 300000)
    public void scheduleTaskWithFixedRate() {
        //    @Override
        try {
            consultaServicoNfeRepository.inactiveServicos();
            consultaServicoNfeContigenciaRepository.inactiveServicos();
            Document doc = Jsoup.connect("http://www.nfe.fazenda.gov.br/portal/disponibilidade.aspx").get();
            for (Element table : doc.getElementsByClass("tabelaListagemDados")) {

                for (Element row : table.select("tr")) {

                    List<String> listDados = new ArrayList<>();
                    for (Node child : row.childNodes()) {

                        if (child.nodeName().equals("td")) {

                            Node node = child.childNodes().get(0);
                            if (node.nodeName().equals("img")) {

                                Element element = ((Element) ((Element) child).childNodes().get(0));
                                listDados.add(element.attributes().get("src"));
                            } else {

                                if (child.childNodes().get(0).getClass().equals(TextNode.class)) {

                                    TextNode textNode = ((TextNode) ((Element) child).childNodes().get(0));
                                    listDados.add(textNode.getWholeText());
                                } else if (child.childNodes().get(0).getClass().equals(Element.class)) {

                                    Element elementNode = ((Element) ((Element) child).childNodes().get(0));
                                    if (elementNode.tag().getName().equals("span")) {

                                        listDados.add("");
                                    }
                                }

                            }
                        }
                    }
                    saveNfe(listDados);
                }
            }
        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    void saveNfe(List<String> listDados) {
        convertStringFromDisponibilidadeNfe(listDados);
    }

    /**
     * Método responsável por chamar as conversões de acordo com o autorizador de nfe
     */
    void convertStringFromDisponibilidadeNfe(List<String> listDados) {

        if (listDados.size() > 0) {

            if (listDados.get(0).equals("SVAN")) {

                convertFromDisponibilidadeNfeAutorizador(listDados, EstadoAutorizador.SVAN.value);
            } else if (listDados.get(0).equals("SVRS")) {

                convertFromDisponibilidadeNfeAutorizador(listDados, EstadoAutorizador.SVRS.value);
            } else if (listDados.get(0).equals("SVC-AN")) {

                convertFromDisponibilidadeNfeAutorizadorContigencia(listDados, EstadoAutorizador.SVCAN.value);
            } else if (listDados.get(0).equals("SVC-RS")) {

                convertFromDisponibilidadeNfeAutorizadorContigencia(listDados, EstadoAutorizador.SVCRS.value);
            } else if (!listDados.get(0).equals("SVC-RS") && !listDados.get(0).equals("SVC-AN")) {

                DisponibilidadeNfe disponibilidadeNfe = new DisponibilidadeNfe();
                disponibilidadeNfe.setDsAutorizador(listDados.get(0));
                disponibilidadeNfe.setDsEstado(listDados.get(0));
                disponibilidadeNfe.setCreatedAt(new Date());
                disponibilidadeNfe.setCreatedAtTime(LocalDateTime.now(ZoneId.of("America/Sao_Paulo")));
                disponibilidadeNfe.setDsStatusAutorizacao(StatusDisponibilidade.valueOfLabel(listDados.get(1)));
                disponibilidadeNfe.setDsStatusRetornoAutorizacao(StatusDisponibilidade.valueOfLabel(listDados.get(2)));
                disponibilidadeNfe.setDsStatusInutilizacao(StatusDisponibilidade.valueOfLabel(listDados.get(3)));
                disponibilidadeNfe.setDsStatusConsultaProtocolo(StatusDisponibilidade.valueOfLabel(listDados.get(4)));
                disponibilidadeNfe.setDsStatusServico(StatusDisponibilidade.valueOfLabel(listDados.get(5)));
                disponibilidadeNfe.setDsTempoMedio(listDados.get(6));
                disponibilidadeNfe.setDsStatusConsultaCadastro(StatusDisponibilidade.valueOfLabel(listDados.get(7)));
                disponibilidadeNfe.setDsStatusRecepcaoEvento(StatusDisponibilidade.valueOfLabel(listDados.get(8)));
                disponibilidadeNfe.setFgAtivo(true);
                consultaServicoNfeRepository.save(disponibilidadeNfe);
            }
        }
    }

    /**
     * Método responsável por chamar as conversões de acordo com o autorizador de nfe em contigência
     */
    void convertFromDisponibilidadeNfeAutorizadorContigencia(List<String> listDados, ArrayList<String> listEstadoAutorizador) {

        if (listDados.size() > 0) {

            for (String estado : listEstadoAutorizador) {

                DisponibilidadeNfeContigencia disponibilidadeNfeContigencia = new DisponibilidadeNfeContigencia();
                disponibilidadeNfeContigencia.setDsAutorizador(listDados.get(0));
                disponibilidadeNfeContigencia.setDsEstado(estado);
                disponibilidadeNfeContigencia.setCreatedAt(new Date());
                disponibilidadeNfeContigencia.setCreatedAtTime(LocalDateTime.now(ZoneId.of("America/Sao_Paulo")));
                disponibilidadeNfeContigencia.setDsStatusAutorizacao(StatusDisponibilidade.valueOfLabel(listDados.get(1)));
                disponibilidadeNfeContigencia.setDsStatusRetornoAutorizacao(StatusDisponibilidade.valueOfLabel(listDados.get(2)));
                disponibilidadeNfeContigencia.setDsStatusInutilizacao(StatusDisponibilidade.valueOfLabel(listDados.get(3)));
                disponibilidadeNfeContigencia.setDsStatusConsultaProtocolo(StatusDisponibilidade.valueOfLabel(listDados.get(4)));
                disponibilidadeNfeContigencia.setDsStatusServico(StatusDisponibilidade.valueOfLabel(listDados.get(5)));
                disponibilidadeNfeContigencia.setDsTempoMedio(listDados.get(6));
                disponibilidadeNfeContigencia.setDsStatusConsultaCadastro(StatusDisponibilidade.valueOfLabel(listDados.get(7)));
                disponibilidadeNfeContigencia.setDsStatusRecepcaoEvento(StatusDisponibilidade.valueOfLabel(listDados.get(8)));
                disponibilidadeNfeContigencia.setFgAtivo(true);
                consultaServicoNfeContigenciaRepository.save(disponibilidadeNfeContigencia);
            }
        }
    }

    /**
     * Método responsável por realizar conversões dos dados recuperados do site da sefaz
     */
    void convertFromDisponibilidadeNfeAutorizador(List<String> listDados, ArrayList<String> listEstadoAutorizador) {

        if (listDados.size() > 0) {

            for (String estado : listEstadoAutorizador) {

                DisponibilidadeNfe disponibilidadeNfe = new DisponibilidadeNfe();
                disponibilidadeNfe.setDsAutorizador(listDados.get(0));
                disponibilidadeNfe.setDsEstado(estado);
                disponibilidadeNfe.setCreatedAt(new Date());
                disponibilidadeNfe.setCreatedAtTime(LocalDateTime.now(ZoneId.of("America/Sao_Paulo")));
                disponibilidadeNfe.setDsStatusAutorizacao(StatusDisponibilidade.valueOfLabel(listDados.get(1)));
                disponibilidadeNfe.setDsStatusRetornoAutorizacao(StatusDisponibilidade.valueOfLabel(listDados.get(2)));
                disponibilidadeNfe.setDsStatusInutilizacao(StatusDisponibilidade.valueOfLabel(listDados.get(3)));
                disponibilidadeNfe.setDsStatusConsultaProtocolo(StatusDisponibilidade.valueOfLabel(listDados.get(4)));
                disponibilidadeNfe.setDsStatusServico(StatusDisponibilidade.valueOfLabel(listDados.get(5)));
                disponibilidadeNfe.setDsTempoMedio(listDados.get(6));
                disponibilidadeNfe.setDsStatusConsultaCadastro(StatusDisponibilidade.valueOfLabel(listDados.get(7)));
                disponibilidadeNfe.setDsStatusRecepcaoEvento(StatusDisponibilidade.valueOfLabel(listDados.get(8)));
                disponibilidadeNfe.setFgAtivo(true);
                consultaServicoNfeRepository.save(disponibilidadeNfe);
            }
        }
    }
}