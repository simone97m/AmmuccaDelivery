package com.example.ammuccadelivery.dataModels;

public class Prodotto {
    private String nome;
    private int prezzo;
    private float quantita=0;

    public Prodotto(String nome,int prezzo){
        this.nome = nome;
        this.prezzo = prezzo;
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(int prezzo) {
        this.prezzo = prezzo;
    }

    public float getQuantita() {
        return quantita;
    }

    public void setQuantita(int quantita) {
        this.quantita = quantita;
    }

    public void increaseQuantita(){
        this.quantita++;
    }

    public void decreaseQuantita(){
        if (quantita==0) return;
        this.quantita--;
    }
    public float getSubtotal(){
        return quantita * prezzo;
    }
}
