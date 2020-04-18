import { DatePipe } from '@angular/common';
import { DisponibilidadeService } from './../service/disponibilidade.service';
import { Component, OnInit, Input, ViewEncapsulation } from '@angular/core';

@Component({
  selector: 'app-nfe',
  templateUrl: './nfe.component.html',
  styleUrls: ['./nfe.component.scss'],
  encapsulation: ViewEncapsulation.None
})
export class NfeComponent {
  @Input() valores: NfeDisponibilidade[];
  @Input() colunas: any[];
  @Input() titulo: any[];
  @Input() dataChart;
  title = 'disponibilidade-servicos-nfe';

  ngOnInit() {
    console.log(this.colunas);
    console.log(this.valores);
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

