import { Component, Input, ViewEncapsulation } from '@angular/core';

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