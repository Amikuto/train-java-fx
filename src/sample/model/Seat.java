package sample.model;

import javafx.beans.property.*;
import lombok.Data;

@Data
public class Seat {

    private final LongProperty id;

    private final StringProperty type;
    private final IntegerProperty cost;
    private final LongProperty carId;

    public Seat(Long id, String type, Integer cost, Long carId) {
        this.id = new SimpleLongProperty(id);
        this.type = new SimpleStringProperty(type);
        this.cost = new SimpleIntegerProperty(cost);
        this.carId = new SimpleLongProperty(carId);
    }

    public Seat() {
        this(null, null, null, null);
    }

    public long getId() {
        return id.get();
    }

    public LongProperty idProperty() {
        return id;
    }

    public void setId(long id) {
        this.id.set(id);
    }

    public String getType() {
        return type.get();
    }

    public StringProperty typeProperty() {
        return type;
    }

    public void setType(String type) {
        this.type.set(type);
    }

    public int getCost() {
        return cost.get();
    }

    public IntegerProperty costProperty() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost.set(cost);
    }

    public long getCarId() {
        return carId.get();
    }

    public LongProperty carIdProperty() {
        return carId;
    }

    public void setCarId(long carId) {
        this.carId.set(carId);
    }
}
