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