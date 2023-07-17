create or replace view v_detailsActe as
    select g.idpatient as idPatient, g.statue as statue, a.nomacte as nomActe, d.prix as prix, d.date as date
        from detailsgroupeacte as d inner join groupeacte g on g.id = d.idgroupeacte
            inner join actes a on a.id = d.idacte;