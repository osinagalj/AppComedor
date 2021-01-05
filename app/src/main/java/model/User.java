package model;



public class User {

    //attributes
    private int id;
    private String password;
    private String name;
    private String lastname;
    private int dni;

    public User(String password,String name,String lastname,int dni){
        this.password = password;
        this.name = name;
        this.lastname = lastname;
        this.dni = dni;
    }

    public static boolean validData(String name, String lastname, String DNI){
        return correctNameAndLastname(name, lastname) && correctDNI(DNI);
    }

    public static boolean correctDNI(String field){
        return (field.matches("[0-9]+") && field.length()>7);
    }

    public static boolean correctNameAndLastname(String name, String lastname) { //solo permite un nombre y un apellido, no se pueden poner espacios extras ni simbolos
        return (name.matches("[a-zA-Z]+") && lastname.matches("[a-zA-Z]+"));//si solo hay letras
    }




    //Getters and Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getLastName() {
        return lastname;
    }

    public void setLastName(String lastName) {
        this.lastname = lastName;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public String getPassWord() {
        return password;
    }

    public void setPassWord(String passWord) {
        this.password = passWord;
    }
}
