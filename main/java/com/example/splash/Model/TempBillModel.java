package com.example.splash.Model;

public class TempBillModel {
    String pid,pname,catid,catname;
    Double qty,price,amt;

    public TempBillModel() {
    }

    public TempBillModel(String pid, String pname, String catid, String catname, Double qty, Double price, Double amt) {
        this.pid = pid;
        this.pname = pname;
        this.catid = catid;
        this.catname = catname;
        this.qty = qty;
        this.price = price;
        this.amt = amt;
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

    public String getCatname() {
        return catname;
    }

    public Double getQty() {
        return qty;
    }

    public Double getPrice() {
        return price;
    }

    public Double getAmt() {
        return amt;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public void setCatid(String catid) {
        this.catid = catid;
    }

    public void setCatname(String catname) {
        this.catname = catname;
    }

    public void setQty(Double qty) {
        this.qty = qty;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setAmt(Double amt) {
        this.amt = amt;
    }
}
