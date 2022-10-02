package com.example.caloriecheck.Model.Input.Builder;

import com.example.caloriecheck.Model.Input.SignUpInputModel;

public class SignUpInputModelBuilder {
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

    public SignUpInputModelBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public SignUpInputModelBuilder setUsername(String username) {
        this.username = username;
        return this;
    }

    public SignUpInputModelBuilder setEmail(String email) {
        this.email = email;
        return this;
    }

    public SignUpInputModelBuilder setPassword(String password) {
        this.password = password;
        return this;
    }

    public SignUpInputModelBuilder setNhucau(int nhucau) {
        this.nhucau = nhucau;
        return this;
    }

    public SignUpInputModelBuilder setR(double r) {
        this.r = r;
        return this;
    }

    public SignUpInputModelBuilder setCan(int can) {
        this.can = can;
        return this;
    }

    public SignUpInputModelBuilder setChieucao(int chieucao) {
        this.chieucao = chieucao;
        return this;
    }

    public SignUpInputModelBuilder setK(int k) {
        this.k = k;
        return this;
    }

    public SignUpInputModelBuilder setTuoi(int tuoi) {
        this.tuoi = tuoi;
        return this;
    }

    public SignUpInputModelBuilder setCanmt(int canmt) {
        this.canmt = canmt;
        return this;
    }

    public SignUpInputModelBuilder setCanbt(int canbt) {
        this.canbt = canbt;
        return this;
    }

    public SignUpInputModel createSignUpInputModel() {
        return new SignUpInputModel(name, username, email, password, nhucau, r, can, chieucao, k, tuoi, canmt, canbt);
    }
}