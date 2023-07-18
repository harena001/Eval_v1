create database eval3_v1;

create table Employes (
    id serial,
    nom varchar(50),
    username varchar(50),
    motdepasse varchar(50),
    primary key (id)
);

create table Medicaments (
    id serial,
    nomMedoc varchar(50),
    prixMedoc integer,
    primary key (id)
);

create table employees (
    id serial,
    email varchar(30),
    motdepasse varchar(30),
    primary key (id)
);

create table Patients (
    id serial,
    nomPatient varchar(100),
    dateNaissance date,
    genre char,
    primary key (id)
);

create table Actes (
    id serial,
    nomActe varchar(50),
    primary key (id)
);

create table Depenses (
    id serial,
    nomDepense varchar(50),
    primary key (id)
);

create table GroupeActe (
    id serial,
    idPatient integer,
    statue boolean,
    primary key (id),
    foreign key (idPatient) references patients(id)
);

create table DetailsGroupeActe (
    idGroupeActe integer,
    idActe integer,
    prix integer,
    date date,
    foreign key (idGroupeActe) references GroupeActe(id),
    foreign key (idActe) references Actes(id)
);

create table DepenseFait (
    id serial,
    idDepense integer,
    prix integer,
    date date,
    primary key (id),
    foreign key (idDepense) references Depenses(id)
);