package model;

public class U_student extends UserType{
    public U_student(){ }
    public float getPrice(float price){
        return price * (float) 0.3;
    };
    public String getType(){
        return UserTypes.student;
    }
}
