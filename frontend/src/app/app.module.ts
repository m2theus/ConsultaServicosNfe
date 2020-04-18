import { CalendarModule } from 'primeng/calendar';
import { TableModule } from 'primeng/table';
import { ButtonModule } from 'primeng/button';
import { PanelModule } from 'primeng/panel';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AutoCompleteModule } from 'primeng/autocomplete';
import { FormsModule } from '@angular/forms';
import { DisponibilidadeService } from './service/disponibilidade.service';
import { HttpClientModule } from '@angular/common/http';
import { CardModule } from 'primeng/card';
import { DatePipe } from '@angular/common';
import { NfeComponent } from './nfe/nfe.component';
import { RadioButtonModule } from 'primeng/radiobutton';
import { ChartModule } from 'primeng/chart';

@NgModule({
  declarations: [
    AppComponent,
    NfeComponent,
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    PanelModule,
    ButtonModule,
    TableModule,
    AutoCompleteModule,
    HttpClientModule,
    CalendarModule,
    CardModule,
    FormsModule,
    RadioButtonModule,
    ChartModule
  ],
  providers: [DisponibilidadeService, DatePipe],
  bootstrap: [AppComponent]
})
export class AppModule { }
