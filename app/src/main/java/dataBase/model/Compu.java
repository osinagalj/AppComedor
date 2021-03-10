package dataBase.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Compu implements Serializable {

    public String getKaka() {
        return kaka;
    }

    public void setKaka(String kaka) {
        this.kaka = kaka;
    }

    public ArrayList<Objeto1> getObjecto() {
        return objecto;
    }

    public void setObjecto(ArrayList<Objeto1> objecto) {
        this.objecto = objecto;
    }

    private String kaka = "a";
    ArrayList<Objeto1> objecto = new ArrayList<>();

    public Compu(){

    }
}
