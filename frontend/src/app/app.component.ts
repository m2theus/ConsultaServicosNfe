import { DisponibilidadeService } from './service/disponibilidade.service';
import { Component, ViewEncapsulation, OnInit } from '@angular/core';
import { DatePipe } from '@angular/common';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss'],
  encapsulation: ViewEncapsulation.None
})
export class AppComponent implements OnInit {

  /* Variáveis utilizadas para recuperar dados*/
  nfes: NfeDisponibilidade[];
  nfesContingencia: NfeDisponibilidade[];
  title = 'disponibilidade-servicos-nfe';
  cols: any[];

  /* Variáveis utilizadas para controle de estado das telas*/
  loading: boolean;
  showEstado: boolean;
  showData: boolean;
  showNfe: boolean = false;
  showNfeContingencia: boolean = false;

  /* Variáveis utilizadas para controle dos filtros*/
  filteredBrands: any[];
  estadosFiltrados: any[];
  filtro;
  estado;
  data;
  defaultDate = new Date();
  selectedValueNfe = 'todos';
  dataChartNfe: any;
  dataChartNfeContigencia: any;

  /* Variáveis utilizadas para guardar os tipos de filtros da tela*/
  tipoFiltros: any[] = [
    { label: 'Data', value: 'Data' },
    { label: 'Estado', value: 'Estado' },
    { label: 'Nenhum', value: 'Nenhum' },
  ];

  tiposEstados: any[] = [
    { nome: "Todos", sigla: "Todos" },
    { nome: "Acre", sigla: "AC" },
    { nome: "Alagoas", sigla: "AL" },
    { nome: "Amapá", sigla: "AP" },
    { nome: "Amazonas", sigla: "AM" },
    { nome: "Bahia", sigla: "BA" },
    { nome: "Ceará", sigla: "CE" },
    { nome: "Distrito Federal", sigla: "DF" },
    { nome: "Espírito Santo", sigla: "ES" },
    { nome: "Goiás", sigla: "GO" },
    { nome: "Maranhão", sigla: "MA" },
    { nome: "Mato Grosso", sigla: "MT" },
    { nome: "Mato Grosso do Sul", sigla: "MS" },
    { nome: "Minas Gerais", sigla: "MG" },
    { nome: "Pará", sigla: "PA" },
    { nome: "Paraíba", sigla: "PB" },
    { nome: "Paraná", sigla: "PR" },
    { nome: "Pernambuco", sigla: "PE" },
    { nome: "Piauí", sigla: "PI" },
    { nome: "Rio de Janeiro", sigla: "RJ" },
    { nome: "Rio Grande do Norte", sigla: "RN" },
    { nome: "Rio Grande do Sul", sigla: "RS" },
    { nome: "Rondônia", sigla: "RO" },
    { nome: "Roraima", sigla: "RR" },
    { nome: "Santa Catarina", sigla: "SC" },
    { nome: "São Paulo", sigla: "SP" },
    { nome: "Sergipe", sigla: "SE" },
    { nome: "Tocantins", sigla: "TO" }

  ]

  constructor(private disponibilidadeService: DisponibilidadeService, private datePipe: DatePipe) {

  }

  ngOnInit() {
    this.cols = [
      { field: 'dsEstado', header: 'Estado', filterMatchMode: 'custom-equals' },
      { field: 'dsAutorizador', header: 'Autorizador', filterMatchMode: 'custom-equals' },
      { field: 'dsStatusRecepcaoEvento', header: 'Recepção Evento4', filterMatchMode: 'custom-equals' },
      { field: 'dsStatusConsultaCadastro', header: 'Consulta Cadastro4', filterMatchMode: 'custom-equals' },
      { field: 'dsTempoMedio', header: 'Tempo médio', filterMatchMode: 'custom-equals' },
      { field: 'dsStatusServico', header: 'Status Serviço4', filterMatchMode: 'custom-equals' },
      { field: 'dsStatusConsultaProtocolo', header: 'Consulta Protocolo4', filterMatchMode: 'custom-equals' },
      { field: 'dsStatusAutorizacao', header: 'Autorização4', filterMatchMode: 'custom-equals' },
      { field: 'dsStatusRetornoAutorizacao', header: 'Retorno Autorização4', filterMatchMode: 'custom-equals' },
      { field: 'dsStatusInutilizacao', header: 'Inutilização4', filterMatchMode: 'custom-equals' },
      { field: 'createdAtTime', header: 'Data/Hora verificação' },
    ];
    this.getAllNfe();
    this.getAllNfeContingencia();
    this.getTotalIndisponibilidadesNfe();
    this.getTotalIndisponibilidadesNfeContingencia();
  }

  /* Método responsável por retornar os 3 estados com maiores indisponibilidades de serviços na nfe*/
  getTotalIndisponibilidadesNfe() {
    this.disponibilidadeService.getTotalIndisponibilidadesNfe().then(data => {
      data = data.sort(function (a, b) {
        return b.total - a.total;
      });
      this.dataChartNfe = {
        labels: [data[0].dsEstado, data[1].dsEstado, data[2].dsEstado],
        datasets: [
          {
            label: 'Indisponibilidades',
            backgroundColor: '#42A5F5',
            borderColor: '#1E88E5',
            data: [data[0].total, data[1].total, data[2].total, 0]
          },
        ]
      }
    });
  }

  /* Método responsável por retornar os 3 estados com maiores indisponibilidades de serviços na nfe em contigência*/
  getTotalIndisponibilidadesNfeContingencia() {
    this.disponibilidadeService.getTotalIndisponibilidadesNfeContingencia().then(data => {
      data = data.sort(function (a, b) {
        return b.total - a.total;
      });
      this.dataChartNfeContigencia = {
        labels: [data[0].dsEstado, data[1].dsEstado, data[2].dsEstado],
        datasets: [
          {
            label: 'Indisponibilidades',
            backgroundColor: '#42A5F5',
            borderColor: '#1E88E5',
            data: [data[0].total, data[1].total, data[2].total, 0]
          },
        ]
      }
    });
  }

  /* Método responsável por retornar todos os dados de serviços das nfe*/
  getAllNfe() {
    this.disponibilidadeService.getStatusAllEstados().then(data => {
      this.nfes = data;
      this.showNfe = true;
      this.loading = false;
    });
  }

  /* Método responsável por retornar todos os dados de serviços das nfe em contigência*/
  getAllNfeContingencia() {
    this.disponibilidadeService.getStatusAllEstadosContingencia().then(data => {
      this.nfesContingencia = data;
      this.showNfeContingencia = true;
      this.loading = false;
    });
  }

  /* Filter para dropdown de tipos de filtro*/
  filterWithContains(event) {
    this.filteredBrands = [];
    for (let i = 0; i < this.tipoFiltros.length; i++) {
      let brand = { ...this.tipoFiltros[i] };
      if (brand.label.toLowerCase().indexOf(event.query.toLowerCase()) == 0) {
        this.filteredBrands.push(brand);
      }

    }
  }

  /* Filter para dropdown de estados*/
  filterWithContainsEstado(event) {
    this.estadosFiltrados = [];
    for (let i = 0; i < this.tiposEstados.length; i++) {
      let estado = { ...this.tiposEstados[i] };
      if (estado.nome.toLowerCase().indexOf(event.query.toLowerCase()) == 0) {
        this.estadosFiltrados.push(estado);
      }

    }
  }

  /* Valida o tipo de filtro selecionado*/
  validTipoFiltro(value) {
    if (value && value.label == 'Data') {
      this.showData = true;
      this.showEstado = false;
      this.data = null;
      this.estado = null;
    } else if (value && value.label == 'Estado') {
      this.showEstado = true;
      this.showData = false;
      this.data = null;
      this.estado = null;
    } else if (value && value.label == 'Nenhum') {
      this.disableFilters();
      this.getAllNfe();
      this.getAllNfeContingencia();
    } else {
      this.disableFilters();
    }
  }

  /* Desabilita os filtros da tela*/
  disableFilters() {
    this.data = null;
    this.estado = null;
    this.showData = false;
    this.showEstado = false;
  }

  /* Busca os serviços de acordo o estado*/
  getByEstado(value) {
    if (value) {
      if (value.nome == "Todos") {
        this.getAllNfe();
        this.getAllNfeContingencia();
      } else {
        this.disponibilidadeService.getStatusByEstado(value.sigla).then(data => {
          console.log(data);
          this.nfes = data[0] ? data : [];
          this.loading = false;
        });
        this.disponibilidadeService.getStatusByEstadoContingencia(value.sigla).then(data => {
          console.log(data);
          this.nfesContingencia = data[0] ? data : [];
          this.loading = false;
        });
      }
    }
  }


  /* Busca os serviços de acordo a data*/
  getByData(value) {
    if (value) {
      this.disponibilidadeService.getStatusByData(this.datePipe.transform(value, 'yyyy/MM/dd')).then(data => {
        this.nfes = data;
        this.loading = false;
      });
      this.disponibilidadeService.getStatusByDataContingencia(this.datePipe.transform(value, 'yyyy/MM/dd')).then(data => {
        this.nfesContingencia = data;
        this.loading = false;
      });
    } else {
      this.getAllNfe();
      this.getAllNfeContingencia();
    }
  }
}

export interface NfeDisponibilidade {
  id;
  dsEstado;
  dsAutorizador;
  dsStatusAutorizacao;
  dsStatusRetornoAutorizacao;
  dsStatusInutilizacao;
  dsStatusConsultaProtocolo;
  dsStatusServico;
  dsTempoMedio;
  dsStatusConsultaCadastro;
  dsStatusRecepcaoEvento;
  createdAtTime;
}  
