package model;

public class U_professor extends UserType{


    private int subjectNumber;

    public U_professor(int subjectNumber){
        this.subjectNumber = subjectNumber;
    }

    public float getPrice(float price){
        if(this.subjectNumber <= 3){
            return  price * (float) 0.7;
        }else{
            if(this.subjectNumber <= 6){
                return  price * (float) 0.5;
            }else{
                return  price * (float) 0.3;
            }
        }
    }
    public String getType(){
        return UserTypes.professor;
    }
    public int getSubjectNumber() {
        return subjectNumber;
    }

    public void setSubjectNumber(int subjectNumber) {
        this.subjectNumber = subjectNumber;
    }
}
