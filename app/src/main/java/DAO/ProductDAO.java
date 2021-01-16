package DAO;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import ModeloGian.Combo;
import ModeloGian.Product;
import ModeloGian.Food;
import ModeloGian.ProductCategory;

public class ProductDAO {
    private static final String FILES_PATH = System.getProperty("user.home") + File.separator + "comedor" + File.separator;
    //+/-barcode, name, description, imgId, categoryId, +stock/-discount, +price
    private static final String PRODUCTS_PATH = FILES_PATH + "products.csv";
    //barcode, ingredient x
    private static final String INGREDIENTS_PATH = FILES_PATH + "ingredients.csv";
    //barcode, item x
    private static final String ITEMS_PATH = FILES_PATH + "items.csv";

    public static List<Product> avalaibleProducts() {
        Map<Integer, Product> products = new HashMap<Integer, Product>();
        try {
            BufferedReader productsReader = new BufferedReader(new FileReader(PRODUCTS_PATH));
            Map<Integer, List<String>> productsIngredients = getProductsIngredients();
            Map<Integer, List<Integer>> comboItems = getComboItems();
            String row;
            String[] data = null;
            List<String> foodIngredients;
            int barcode;
            while ((row = productsReader.readLine()) != null) {
                data = row.split(",");
                barcode = Integer.parseInt(data[0]);
                if (barcode > 0) { // <0 combo, >0 food
                    products.put(barcode, (new Food(barcode, data[1], data[2], Integer.parseInt(data[3]), getCategory(Integer.parseInt(data[4])), Integer.parseInt(data[5]), Float.parseFloat(data[6]), productsIngredients.get(barcode))));
                } else {
                    barcode = barcode * -1;
                    products.put(barcode, (new Combo(barcode, data[1], data[2], Integer.parseInt(data[3]), getCategory(Integer.parseInt(data[4])), Integer.parseInt(data[5]))));
                }
            }
            if (!products.isEmpty()) {
                for (int comboBarcode : comboItems.keySet()) {
                    for (int item : Objects.requireNonNull(comboItems.get(comboBarcode))) {
                        ((Combo) Objects.requireNonNull(products.get(comboBarcode))).addItem(products.get(item));
                    }
                }
            }
            productsReader.close();
            return new ArrayList<>(products.values());
        } catch (FileNotFoundException e) { //If the file dont exists, create them
            try {
                FileWriter csvWriter = new FileWriter(PRODUCTS_PATH);
                csvWriter.flush();
                csvWriter.close();
                return new ArrayList<>(); //return empty list because dont exist any ingredient
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.exit(1);
        return null; //unreachable
    }

    private static ProductCategory getCategory(int cat) {
        switch (cat) {
            case 0:
                return ProductCategory.DAILY_MENU;
            case 1:
                return ProductCategory.BUFFET;
            case 2:
                return ProductCategory.KIOSKO;
        }
        System.exit(1);
        return null; //unreachable
    }

    private static Map<Integer, List<Integer>> getComboItems() {
        Map<Integer, List<Integer>> comboItems = new HashMap<>();
        try {
            BufferedReader itemsReader = new BufferedReader(new FileReader(ITEMS_PATH));
            String row;
            String[] data = null;
            int lastBarcode;
            List<Integer> items = new ArrayList<>();
            row = itemsReader.readLine();
            if (row != null) {
                data = row.split(",");
                lastBarcode = Integer.parseInt(data[0]);
                items.add(Integer.parseInt(data[1]));
                while ((row = itemsReader.readLine()) != null) {
                    data = row.split(",");
                    if (Integer.parseInt(data[0]) != lastBarcode) {
                        comboItems.put(lastBarcode, items);
                        items = new ArrayList<>();
                    }
                    items.add(Integer.parseInt(data[1]));
                }
                if (!items.isEmpty()) {
                    comboItems.put(lastBarcode, items);
                }
            }
            itemsReader.close();
            return comboItems;
        } catch (FileNotFoundException e) { //If the file dont exists, create them
            try {
                FileWriter csvWriter = new FileWriter(ITEMS_PATH);
                csvWriter.flush();
                csvWriter.close();
                return new HashMap<>(); //return empty map because dont exist any combo
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.exit(1);
        return null; //unreachable
    }

    private static Map<Integer, List<String>> getProductsIngredients() {
        Map<Integer, List<String>> productsIngredients = new HashMap<>();
        try {
            BufferedReader ingredientsReader = new BufferedReader(new FileReader(INGREDIENTS_PATH));
            String row;
            String[] data = null;
            int lastBarcode;
            List<String> ingredients = new ArrayList<>();
            row = ingredientsReader.readLine();
            if (row != null) {
                data = row.split(",");
                lastBarcode = Integer.parseInt(data[0]);
                ingredients.add(data[1]);
                while ((row = ingredientsReader.readLine()) != null) {
                    data = row.split(",");
                    if (Integer.parseInt(data[0]) != lastBarcode) {
                        productsIngredients.put(lastBarcode, ingredients);
                        ingredients = new ArrayList<>();
                    }
                }
                if (!ingredients.isEmpty()) {
                    productsIngredients.put(lastBarcode, ingredients);
                }
            }
            ingredientsReader.close();
            return productsIngredients;
        } catch (FileNotFoundException e) { //If the file dont exists, create them
            try {
                FileWriter csvWriter = new FileWriter(INGREDIENTS_PATH);
                csvWriter.flush();
                csvWriter.close();
                return new HashMap<>(); //return empty map because dont exist any ingredient
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.exit(1);
        return null; //unreachable
    }

    public static void saveAvailableProducts(List<Product> products) {
        List<List<String>> rows = new ArrayList<>();
        List<String> productInfo;
        Food castedFood;
        Combo castedCombo;
        for (Product product : products) {
            productInfo = new ArrayList<>();
            productInfo.add(product.getName());
            productInfo.add(product.getDescription());
            productInfo.add(String.valueOf(product.getImgId()));
            productInfo.add(String.valueOf(getIdCategory(product.getCategory())));
            if (product instanceof Food){
                castedFood = ((Food) product);
                productInfo.add(0,String.valueOf(product.getId()));
                productInfo.add(String.valueOf(castedFood.getStock()));
                productInfo.add(String.valueOf(castedFood.getPrice()));
                saveIngredients(castedFood);
            }
            else { //combo in the domain of app
                castedCombo = ((Combo) product);
                productInfo.add(0,String.valueOf((-1)*product.getId())); //make id negative
                productInfo.add(String.valueOf(castedCombo.getDiscount()));
                saveItems(castedCombo);
            }
            rows.add(productInfo);
            try {
                 FileWriter productsWriter = new FileWriter(PRODUCTS_PATH);
                for (List<String> rowData : rows) {
                    productsWriter.append(String.join(",", rowData));
                    productsWriter.append("\n");
                }
                productsWriter.flush();
                productsWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    private static int getIdCategory(ProductCategory productCategory) {
        switch (productCategory) {
            case DAILY_MENU:
                return 0;
            case BUFFET:
                return 1;
            case KIOSKO:
                return 2;
        }
        System.exit(1);
        return -1; //unreachable
    }

    private static void saveItems(Combo castedCombo) {
        List<Product> comboItems = castedCombo.getComboItems();
        try {
            FileWriter productsWriter = new FileWriter(ITEMS_PATH);
            for (Product item: comboItems) {
                productsWriter.append(String.valueOf(castedCombo.getId()));
                productsWriter.append(",");
                productsWriter.append(String.valueOf(item.getId()));
                productsWriter.append("\n");
            }
            productsWriter.flush();
            productsWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void saveIngredients(Food food){
        List<String> foodIngredients = food.getIngredients();
        try {
            FileWriter productsWriter = new FileWriter(INGREDIENTS_PATH);
            for (String ingredient: foodIngredients) {
                productsWriter.append(String.valueOf(food.getId()));
                productsWriter.append(",");
                productsWriter.append(ingredient);
                productsWriter.append("\n");
            }
            productsWriter.flush();
            productsWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int maxDailyMenus() {
        return 2;
    }
}
