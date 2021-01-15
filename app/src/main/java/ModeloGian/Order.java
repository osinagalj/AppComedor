package ModeloGian;

import java.time.Instant;
import java.util.List;

public class Order {
    private static int ordersPlaced = 0;

    private int id;
    private Instant placed;
    private CommonUser placedBy;

    private List<Product> items;
    private List<Product> toHome;

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
