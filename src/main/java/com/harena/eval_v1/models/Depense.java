package com.harena.eval_v1.models;

public class Depense {

    public int id;
    public String nomDepense;
    public int budgetAnnuel;
    public String code;

    public int realisation;
    public int reel;

    public int getRealisation() {
        return realisation;
    }

    public void setRealisation(int realisation) {
        this.realisation = realisation;
    }

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

    public String getNomDepense() {
        return nomDepense;
    }

    public void setNomDepense(String nomDepense) {
        this.nomDepense = nomDepense;
    }
}
