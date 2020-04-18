import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable()
export class DisponibilidadeService {

    constructor(private http: HttpClient) { }

    getStatusAllEstados() {
        return this.http.get('http://localhost:8080/consultanfe/all')
            .toPromise()
            .then(res => <any[]>res)
            .then(data => { 
                console.log(data);
                return data; });
    }

    getStatusAllEstadosContingencia() {
        return this.http.get('http://localhost:8080/consultanfe/contigencia/all')
            .toPromise()
            .then(res => <any[]>res)
            .then(data => {
                console.log(data);
                return data;
            });
    }
    

    getStatusByEstado(dsEstado) {
        return this.http.get('http://localhost:8080/consultanfe/getByEstado?' + 'dsEstado=' + dsEstado)
            .toPromise()
            .then(res => <any>res)
            .then(data => {
                console.log(data);
                return [data];
            });
    }

    getStatusByEstadoContingencia(dsEstado) {
        return this.http.get('http://localhost:8080/consultanfe/contigencia/getByEstado?' + 'dsEstado=' + dsEstado)
            .toPromise()
            .then(res => <any>res)
            .then(data => {
                console.log(data);
                return [data];
            });
    }

    getStatusByData(data) {
        var params = { dtInicial: data, dtFinal: data };
        var config = { params: params };
        return this.http.post('http://localhost:8080/consultanfe/getByData', [], config)
            .toPromise()
            .then(res => <any>res)
            .then(data => {
                console.log(data);
                return data;
            });
    }

    getStatusByDataContingencia(data) {
        var params = { dtInicial: data, dtFinal: data };
        var config = { params: params };
        return this.http.post('http://localhost:8080/consultanfe/contigencia/getByData', [], config)
            .toPromise()
            .then(res => <any>res)
            .then(data => {
                console.log(data);
                return data;
            });
    }

    
}