package model;

public class U_external extends UserType{
    public U_external(){ }

    public float getPrice(float price){
        return price;
    };
    public String getType(){
        return UserTypes.external;
    }
}
