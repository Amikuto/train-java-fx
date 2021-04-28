package sample.model;

import javafx.beans.property.*;

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

    public Long getId() {
        return id.get();
    }

    public LongProperty idProperty() {
        return id;
    }

    public void setId(Long id) {
        this.id.set(id);
    }

    public Integer getNumber() {
        return number.get();
    }

    public IntegerProperty numberProperty() {
        return number;
    }

    public void setNumber(Integer number) {
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

    public Integer getCost() {
        return cost.get();
    }

    public IntegerProperty costProperty() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost.set(cost);
    }

    public Long getCarId() {
        return carId.get();
    }

    public LongProperty carIdProperty() {
        return carId;
    }

    public void setCarId(Long carId) {
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
