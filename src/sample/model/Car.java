package sample.model;

import javafx.beans.property.*;
import javafx.collections.ObservableList;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Car {

    private final LongProperty id;

    private final IntegerProperty number;
    private final StringProperty carClass;
    private final StringProperty type;
    private final IntegerProperty trainId;
    private final ListProperty<Seat> seats;

    public Car(Long id, Integer number, String carClass, String type, Integer trainId, List<Seat> seats) {
        this.id = new SimpleLongProperty(id);
        this.number = new SimpleIntegerProperty(number);
        this.carClass = new SimpleStringProperty(carClass);
        this.type = new SimpleStringProperty(type);
        this.trainId = new SimpleIntegerProperty(trainId);
        this.seats = new SimpleListProperty<>((ObservableList<Seat>) seats);
    }

    public Car() {
        this(null, null, null, null, null, null);
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

    public String getCarClass() {
        return carClass.get();
    }

    public StringProperty carClassProperty() {
        return carClass;
    }

    public void setCarClass(String carClass) {
        this.carClass.set(carClass);
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

    public int getTrainId() {
        return trainId.get();
    }

    public IntegerProperty trainIdProperty() {
        return trainId;
    }

    public void setTrainId(int trainId) {
        this.trainId.set(trainId);
    }

    public ObservableList<Seat> getSeats() {
        return seats.get();
    }

    public ListProperty<Seat> seatsProperty() {
        return seats;
    }

    public void setSeats(ObservableList<Seat> seats) {
        this.seats.set(seats);
    }
}
