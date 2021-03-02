package model;

import java.util.Date;

public class ExtenUser extends CommonUser {

    public String getAtributoExtra() {
        return atributoExtra;
    }

    public void setAtributoExtra(String atributoExtra) {
        this.atributoExtra = atributoExtra;
    }

    private String atributoExtra;
    public ExtenUser(){}
    public ExtenUser(int identityCardNumber, String password, float balance, String names, String lastName, Date birthDate, int condition, Category category , String s) {
        super(identityCardNumber,password,balance,names,lastName,birthDate,condition,category);
        this.atributoExtra = s;
    }


}
