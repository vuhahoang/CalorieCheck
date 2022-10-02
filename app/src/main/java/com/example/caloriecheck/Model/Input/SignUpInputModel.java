package com.example.caloriecheck.Model.Input;



public class SignUpInputModel {
    private String name;
    private String username;
    private String email;
    private String password;
    private int nhucau;
    private double r;
    private int can;
    private int chieucao;
    private int k;
    private int tuoi;
    private int canmt;
    private int canbt;

    public SignUpInputModel(String name, String username, String email, String password, int nhucau, double r, int can, int chieucao, int k, int tuoi, int canmt, int canbt) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
        this.nhucau = nhucau;
        this.r = r;
        this.can = can;
        this.chieucao = chieucao;
        this.k = k;
        this.tuoi = tuoi;
        this.canmt = canmt;
        this.canbt = canbt;
    }

    public SignUpInputModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getNhucau() {
        return nhucau;
    }

    public void setNhucau(int nhucau) {
        this.nhucau = nhucau;
    }

    public double getR() {
        return r;
    }

    public void setR(double r) {
        this.r = r;
    }

    public int getCan() {
        return can;
    }

    public void setCan(int can) {
        this.can = can;
    }

    public int getChieucao() {
        return chieucao;
    }

    public void setChieucao(int chieucao) {
        this.chieucao = chieucao;
    }

    public int getK() {
        return k;
    }

    public void setK(int k) {
        this.k = k;
    }

    public int getTuoi() {
        return tuoi;
    }

    public void setTuoi(int tuoi) {
        this.tuoi = tuoi;
    }

    public int getCanmt() {
        return canmt;
    }

    public void setCanmt(int canmt) {
        this.canmt = canmt;
    }

    public int getCanbt() {
        return canbt;
    }

    public void setCanbt(int canbt) {
        this.canbt = canbt;
    }
}
