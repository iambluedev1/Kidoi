package com.dut.kidoi.models;

import java.util.Date;

public class Transaction {

    private String idDebit;
    private String idCredit;
    private Date date;
    private String message;
    private float montant;
    private boolean fait;

    public Transaction(String idCredit, String idDebit, Date date, String message, float montant, boolean fait) {

        this.date = date;
        this.fait = fait;
        this.idCredit = idCredit;
        this.idDebit = idDebit;
        this.message = message;
        this.montant = montant;

    }

    public String getIdDebit() {
        return idDebit;
    }

    public String getIdCredit() {
        return idCredit;
    }

    public Date getDate() {
        return date;
    }

    public String getMessage() {
        return message;
    }

    public float getMontant() {
        return montant;
    }

    public boolean isFait() {
        return fait;
    }
}
