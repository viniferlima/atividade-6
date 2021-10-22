create schema hangar;

create user 'user'@'localhost' identified by 'pass123';

grant select, insert, delete, update on hangar.* to user@'localhost';

use hangar;

create table avi_aviao (
    avi_id bigint unsigned not null auto_increment,
    avi_modelo varchar(100) not null,
    avi_prefixo varchar(6) not null,
    avi_cor varchar(10) not null,
    primary key (avi_id),
    unique key uni_aviao_prefixo (avi_prefixo)
);

create table pec_peca (
    pec_id bigint unsigned not null auto_increment,
    pec_descricao varchar(100) not null,
    pec_codigo varchar(20) not null,
    pec_tipo varchar(20) not null,
    primary key (pec_id),
    unique key uni_peca_codigo (pec_codigo)
);

create table ape_aviao_peca (
    avi_id bigint unsigned not null,
    pec_id bigint unsigned not null,
    primary key (avi_id, pec_id),
    foreign key ape_avi_fk (avi_id)
        references avi_aviao (avi_id)
        on delete restrict on update cascade,
    foreign key ape_pec_fk (pec_id)
        references pec_peca (pec_id)
        on delete restrict on update cascade
);

create table man_manutencao (
    man_id bigint unsigned not null auto_increment,
    man_data datetime not null,
    man_procedimento varchar(200) not null,
    avi_id bigint unsigned not null,
    primary key (man_id),
    foreign key man_avi_fk (avi_id)
        references avi_aviao (avi_id)
        on delete restrict on update cascade
);

create table usr_usuario (
    usr_id bigint unsigned not null auto_increment,
    usr_nome varchar(20) not null,
    usr_email varchar(100) not null,
    usr_senha varchar(100) not null,
    primary key (usr_id),
    unique key uni_usuario_nome (usr_nome),
    unique key uni_usuario_email (usr_email)
);

create table aut_autorizacao (
    aut_id bigint unsigned not null auto_increment,
    aut_nome varchar(20) not null,
    primary key (aut_id),
    unique key uni_aut_nome (aut_nome)
);

create table uau_usuario_autorizacao (
    usr_id bigint unsigned not null,
    aut_id bigint unsigned not null,
    primary key (usr_id, aut_id),
    foreign key uau_usr_fk (usr_id)
        references usr_usuario (usr_id)
        on delete restrict on update cascade,
    foreign key uau_aut_fk (aut_id)
        references aut_autorizacao (aut_id)
        on delete restrict on update cascade
);