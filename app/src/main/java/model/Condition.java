package com.company.model;

import java.util.Collections;
import java.util.Set;

public class Condition {
    private String name;

    private Set<String> cantConsume;

    public Condition(String name, Set<String> cantConsume) {
        this.name = name;
        this.cantConsume = cantConsume;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<String> getCantConsume() {
        return Collections.unmodifiableSet(cantConsume);
    }

    public boolean addIngredient (String ingredient){
        return cantConsume.add(ingredient);
    }

    public boolean removeIngredient (String ingredient){
        return cantConsume.remove(ingredient);
    }

    public boolean canConsume(Product product){
        for (String ingredient : product.getIngredients())
            for (String cantIngredient : cantConsume)
                if (ingredient.equals(cantIngredient))
                    return false;
        return true;
    }
}
