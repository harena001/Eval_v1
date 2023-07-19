create or replace view v_detailsActe as
    select g.id as idgroupeacte, idpatient as idPatient, g.statue as statue,d.idacte as idacte, a.nomacte as nomActe, d.prix as prix, d.date as date
        from detailsgroupeacte as d inner join groupeacte g on g.id = d.idgroupeacte
            inner join actes a on a.id = d.idacte;

create or replace view v_dashRecette as
    select idacte,nomacte,EXTRACT(Month from date)as mois,EXTRACT(year from date)as annee,sum(prix) from v_detailsActe
                                                                    where statue=true group by mois,annee,nomacte,idacte;

create or replace view v_depenseFait as
    select df.iddepense as id, df.date as date, df.prix, d.nomdepense  from depenses as d inner join depensefait df
        on d.id = df.iddepense;

create or replace view v_dashDepense as
    select id as iddepense,nomdepense,EXTRACT(Month from date)as mois,EXTRACT(year from date) as annee,sum(prix)
        from v_depenseFait group by mois,annee,nomdepense,iddepense;

select sum(prix) from v_detailsActe where statue=false;

select nomacte,EXTRACT(Month from date)as mois,EXTRACT(year from date)as annee,sum(prix) from v_detailsActe group by mois,annee,nomacte;

select annee from v_dashRecette union select annee from v_dashDepense;