create or replace view v_detailsActe as
    select g.id as idgroupeacte, idpatient as idPatient, g.statue as statue,d.idacte as idacte, a.nomacte as nomActe, d.prix as prix, d.date as date
        from detailsgroupeacte as d inner join groupeacte g on g.id = d.idgroupeacte
            inner join actes a on a.id = d.idacte;

select sum(prix) from v_detailsActe where statue=false;

select nomacte,EXTRACT(Month from date)as mois,sum(prix) from v_detailsActe group by EXTRACT(Month from date), nomacte;
