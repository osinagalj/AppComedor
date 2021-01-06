package model;

public class U_universityEmployee extends UserType{


    private int antiquity;

    public U_universityEmployee(int antiquity){
        this.antiquity = antiquity;
    }

    public float getPrice(float price){
        if(this.antiquity >= 2){
            return  price * (float) 0.8;
        }
        return  price;
    }
    public String getType(){
        return UserTypes.universityEmployee;
    }
    public int getAntiquity() {
        return antiquity;
    }

    public void setAntiquity(int antiquity) {
        this.antiquity = antiquity;
    }
}
