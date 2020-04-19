import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable()
export class DisponibilidadeService {

    urlNfe = 'http://localhost:8080/consultanfe';
    urlNfeContigencia = 'http://localhost:8080/consultanfe/contigencia';
    constructor(private http: HttpClient) { }

    getTotalIndisponibilidadesNfe() {
        return this.http.get(this.urlNfe + '/getTotalIndisponibilidade')
            .toPromise()
            .then(res => <any[]>res)
            .then(data => {
                return data;
            });
    }

    getTotalIndisponibilidadesNfeContingencia() {
        return this.http.get(this.urlNfeContigencia + '/getTotalIndisponibilidade')
            .toPromise()
            .then(res => <any[]>res)
            .then(data => {
                return data;
            });
    }

    getStatusAllEstados() {
        return this.http.get(this.urlNfe + '/all')
            .toPromise()
            .then(res => <any[]>res)
            .then(data => {
                return data;
            });
    }

    getStatusAllEstadosContingencia() {
        return this.http.get(this.urlNfeContigencia + '/all')
            .toPromise()
            .then(res => <any[]>res)
            .then(data => {
                return data;
            });
    }


    getStatusByEstado(dsEstado) {
        return this.http.get(this.urlNfe + '/getByEstado?' + 'dsEstado=' + dsEstado)
            .toPromise()
            .then(res => <any>res)
            .then(data => {
                return [data];
            });
    }

    getStatusByEstadoContingencia(dsEstado) {
        return this.http.get(this.urlNfeContigencia + '/getByEstado?' + 'dsEstado=' + dsEstado)
            .toPromise()
            .then(res => <any>res)
            .then(data => {
                return [data];
            });
    }

    getStatusByData(data) {
        var params = { dtInicial: data, dtFinal: data };
        var config = { params: params };
        return this.http.post(this.urlNfe + '/getByData', [], config)
            .toPromise()
            .then(res => <any>res)
            .then(data => {
                return data;
            });
    }

    getStatusByDataContingencia(data) {
        var params = { dtInicial: data, dtFinal: data };
        var config = { params: params };
        return this.http.post(this.urlNfeContigencia + '/getByData', [], config)
            .toPromise()
            .then(res => <any>res)
            .then(data => {
                return data;
            });
    }


}