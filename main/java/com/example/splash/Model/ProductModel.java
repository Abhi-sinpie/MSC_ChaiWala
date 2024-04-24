package com.example.splash.Model;

public class ProductModel {
    String pid,pname,catid;
    int productqty,pprice;

    public ProductModel() {
    }

    public ProductModel(String pid, String pname, String catid, int productqty, int pprice) {
        this.pid = pid;
        this.pname = pname;
        this.catid = catid;
        this.productqty = productqty;
        this.pprice = pprice;
    }

    public String getPid() {
        return pid;
    }

    public String getPname() {
        return pname;
    }

    public String getCatid() {
        return catid;
    }

    public int getProductqty() {
        return productqty;
    }

    public int getPprice() {
        return pprice;
    }
}
