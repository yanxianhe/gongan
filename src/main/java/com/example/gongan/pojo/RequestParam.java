package com.example.gongan.pojo;

import java.io.Serializable;

public class RequestParam implements Serializable {
    private static final long serialVersionUID = -2075623553452792694L;
    private String isTransaction;
    private Dataset Dataset;

    public RequestParam() {
    }

    public RequestParam(String isTransaction, com.example.gongan.pojo.Dataset dataset) {
        this.isTransaction = isTransaction;
        Dataset = dataset;
    }

    public String getIsTransaction() {
        return isTransaction;
    }

    public void setIsTransaction(String isTransaction) {
        this.isTransaction = isTransaction;
    }

    public com.example.gongan.pojo.Dataset getDataset() {
        return Dataset;
    }

    public void setDataset(com.example.gongan.pojo.Dataset dataset) {
        Dataset = dataset;
    }
}
