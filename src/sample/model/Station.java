package sample.model;

import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Station {

    private final LongProperty id;
    private final StringProperty cityName;
    private final StringProperty stationName;

    public Station(Long id, String cityName, String stationName) {
        this.id = new SimpleLongProperty(id);
        this.cityName = new SimpleStringProperty(cityName);
        this.stationName = new SimpleStringProperty(stationName);
    }

    public Station(Long stationId, String stationName) {
        this.id = new SimpleLongProperty(stationId);
        this.cityName = new SimpleStringProperty("");
        this.stationName = new SimpleStringProperty(stationName);
    }

    public Station() {
        this(null, null, null);
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

    public String getCityName() {
        return cityName.get();
    }

    public StringProperty cityNameProperty() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName.set(cityName);
    }

    public String getStationName() {
        return stationName.get();
    }

    public StringProperty stationNameProperty() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName.set(stationName);
    }

    @Override
    public String toString() {
        return "Станция " + stationName.getValue() + " под id = " +id.getValue();
    }
}
