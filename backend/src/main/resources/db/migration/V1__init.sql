create schema if not exists consulta collate utf8mb4_0900_ai_ci;
create table if not exists nfe
(
	id int auto_increment
		primary key,
	ds_estado varchar(45) not null,
	created_at date null,
	ds_status_autorizacao varchar(20) null,
	ds_status_retorno_autorizacao varchar(20) not null,
	ds_status_inutilizacao varchar(20) not null,
	ds_status_consulta_protocolo varchar(20) not null,
	ds_status_servico varchar(20) not null,
	ds_tempo_medio varchar(20) not null,
	ds_status_consulta_cadastro varchar(20) not null,
	ds_status_recepcao_evento varchar(20) not null,
	fg_ativo tinyint(1) default 1 not null,
	ds_autorizador varchar(20) not null,
	created_attime datetime not null
);
create table if not exists nfe_contigencia
(
	id int auto_increment
		primary key,
	ds_estado varchar(45) not null,
	created_at timestamp null,
	ds_status_autorizacao varchar(20) null,
	ds_status_retorno_autorizacao varchar(20) not null,
	ds_status_inutilizacao varchar(20) not null,
	ds_status_consulta_protocolo varchar(20) not null,
	ds_status_servico varchar(20) not null,
	ds_tempo_medio varchar(20) not null,
	ds_status_consulta_cadastro varchar(20) not null,
	ds_status_recepcao_evento varchar(20) not null,
	fg_ativo tinyint(1) default 1 not null,
	ds_autorizador varchar(20) not null,
	created_attime datetime null
);