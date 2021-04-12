package sample.model;

import javafx.beans.property.*;
import lombok.Data;

//@Data
public class Seat {

    private final LongProperty id;

    private final IntegerProperty number;
    private final StringProperty seatType;
    private final IntegerProperty cost;
    private final LongProperty carId;

    public Seat(Long id, Integer number, String type, Integer cost, Long carId) {
        this.id = new SimpleLongProperty(id);
        this.number = new SimpleIntegerProperty(number);
        this.seatType = new SimpleStringProperty(type);
        this.cost = new SimpleIntegerProperty(cost);
        this.carId = new SimpleLongProperty(carId);
    }

    public Seat() {
        this(null, null, null, null, null);
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

    public int getNumber() {
        return number.get();
    }

    public IntegerProperty numberProperty() {
        return number;
    }

    public void setNumber(int number) {
        this.number.set(number);
    }

    public String getSeatType() {
        return seatType.get();
    }

    public StringProperty seatTypeProperty() {
        return seatType;
    }

    public void setSeatType(String seatType) {
        this.seatType.set(seatType);
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

    @Override
    public String toString() {
        return "Место" +
                " | под номером: " + number.getValue() +
                " | расположение: " + seatType.getValue() +
                " | стоимость: " + cost.getValue() +
                " | id вагона:" + carId.getValue();
    }
}
