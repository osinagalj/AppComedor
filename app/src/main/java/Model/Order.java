package Model;

import java.time.Instant;
import java.util.HashMap;
import java.util.List;

public class Order {
    private static int ordersPlaced = 0;

    private int id;
    private Instant placed;
    private CommonUser placedBy;

    private List<Product> items; //TODO POSIBLEMENTE ESTO SEA UN HASH, SINO ES MAS DIFICIL SACAR LAS CANTIDADES DE CADA PRODUCTO
    private List<Product> toHome;

    private HashMap<Product,Integer> productos;

    //TODO get price, get time formato hora:min (ej: 16:32 hs), get Productos en strings para la descripcion


    public Order(int id, List<Product> items){
        this.id = id;
        this.items=items;

    }

    //TODO creo hay que sobreescribir el operador de product para que quede ordenado segun se fue ingresando
    public void addFood(Product p, int amount){
        productos.put(p,amount);
    }

    public String getDescripcion(){ //TODO
        return "2x Coca-Cola + sanguchito";
    }
    public String getTime(){//TODO
        return "30/01/2001, 13:03 hs";
    }
    public float getPrice() {//TODO
        return 280.f;
    }
    public Order(CommonUser placedBy, List<Product> items, List<Product> toHome) {
        this.placedBy = placedBy;
        this.items = items;
        this.toHome = toHome;
        this.id = ++ordersPlaced;
        this.placed = Instant.now();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Instant getPlaced() {
        return placed;
    }

    public void setPlaced(Instant placed) {
        this.placed = placed;
    }

    public CommonUser getPlacedBy() {
        return placedBy;
    }

    public void setPlacedBy(CommonUser placedBy) {
        this.placedBy = placedBy;
    }

    public List<Product> getItems() {
        return items;
    }

    public void setItems(List<Product> items) {
        this.items = items;
    }

    public List<Product> getToHome() {
        return toHome;
    }

    public void setToHome(List<Product> toHome) {
        this.toHome = toHome;
    }
}
