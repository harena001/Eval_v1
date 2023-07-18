package com.harena.eval_v1.models;

public class DashRecette {

    public int idActe;
    public String nomActe;
    public int reel;
    public int budget;
    public int realisation;

    public String getNomActe() {
        return nomActe;
    }

    public void setNomActe(String nomActe) {
        this.nomActe = nomActe;
    }

    public int getReel() {
        return reel;
    }

    public void setReel(int reel) {
        this.reel = reel;
    }

    public int getBudget() {
        return budget;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }

    public int getRealisation() {
        return realisation;
    }

    public void setRealisation(int realisation) {
        this.realisation = realisation;
    }

    public void setIdActe(int anInt) {
    }

    public int getIdActe() {
        return idActe;
    }
}
