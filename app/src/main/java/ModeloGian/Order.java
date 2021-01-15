package ModeloGian;

import java.time.Instant;
import java.util.List;

public class Order {

    private int id;
    private Instant placed;
    private CommonUser placedBy;

    private List<Product> items; //TODO POSIBLEMENTE ESTO SEA UN HASH, SINO ES MAS DIFICIL SACAR LAS CANTIDADES DE CADA PRODUCTO
    private List<Product> toHome;

    //TODO get price, get time formato hora:min (ej: 16:32 hs), get Productos en strings para la descripcion


    public Order(int id, List<Product> items){
        this.id = id;
        this.items=items;
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
