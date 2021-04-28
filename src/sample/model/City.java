package sample.model;

import javafx.beans.property.*;
import javafx.collections.ObservableList;

import java.util.List;

public class City {

    private final LongProperty id;

    private final StringProperty name;
    private final ListProperty<Station> stations;

    public City(Long id, String name, List<Station> stations) {
        this.id = new SimpleLongProperty(id);
        this.name = new SimpleStringProperty(name);
        this.stations = new SimpleListProperty<>((ObservableList<Station>) stations);
    }

    public City() {
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

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public ObservableList<Station> getStations() {
        return stations.get();
    }

    public ListProperty<Station> stationsProperty() {
        return stations;
    }

    public void setStations(ObservableList<Station> stations) {
        this.stations.set(stations);
    }
}
