package utils;

import java.time.LocalDate;

import model.Category;

public class Utils {

    static public LocalDate getDate(String date) {
        //convertir el string 0001-01-01 de la base de datos a un tipo LocalDate
        return LocalDate.now();
    }

    static public Category getCategory(String name){
        Category category;

        switch (name){
            case "ALUMNO":
                category = Category.ALUMNO;
                break;
            case "NO_DOCENTE":
                category = Category.NO_DOCENTE;
                break;
            case "DOCENTE":
                category = Category.DOCENTE;
                break;
            default:
                category = Category.EXTERNO; //Por defecto es externo ya que a este tipo no se le aplica ninguna descuento
        }

        return category;
    }

}
