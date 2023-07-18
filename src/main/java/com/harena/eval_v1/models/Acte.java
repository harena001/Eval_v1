package com.harena.eval_v1.models;

public class Acte {

    public int id;
    public String nomActe;
    public int budgetAnnuel;
    public String code;
    public int realisation;



    public int getRealisation() {
        return realisation;
    }

    public void setRealisation(int realisation) {
        this.realisation = realisation;
    }

    public int reel;

    public int getReel() {
        return reel;
    }

    public void setReel(int reel) {
        this.reel = reel;
    }

    public int getBudgetAnnuel() {
        return budgetAnnuel;
    }

    public void setBudgetAnnuel(int budgetAnnuel) {
        this.budgetAnnuel = budgetAnnuel;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomActe() {
        return nomActe;
    }

    public void setNomActe(String nomActe) {
        this.nomActe = nomActe;
    }
}
