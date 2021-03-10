package com.Singlestone.ContactApiENTRY.Model;

import java.io.Serializable;

public class Phone implements Serializable {

    private String number;
    private String numberType;

    public String getNumber() {
        return number;
    }

    public void setNumber (String number) {
        this.number = number;
    }

    public String getNumberType() {
        return numberType;
    }

    public void setNumberType(String numberType) {
        this.number = numberType;
    }

}
