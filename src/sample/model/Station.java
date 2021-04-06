package sample.model;

import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Station {

    private final StringProperty id;
    private final StringProperty cityName;
    private final StringProperty stationName;

    public Station(String id, String cityName, String stationName) {
        this.id = new SimpleStringProperty(id);
        this.cityName = new SimpleStringProperty(cityName);
        this.stationName = new SimpleStringProperty(stationName);
    }

    public Station() {
        this(null, null, null);
    }

    public String getId() {
        return id.get();
    }

    public StringProperty idProperty() {
        return id;
    }

    public void setId(String id) {
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
        return "Station{" +
                "id=" + id +
                ", cityName=" + cityName +
                ", stationName=" + stationName +
                '}';
    }
}
