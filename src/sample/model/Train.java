package sample.model;

import javafx.beans.property.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class Train {

    private final LongProperty id;

    private final StringProperty depSt;
    private final StringProperty arrSt;

    private final StringProperty departingCity;
    private final StringProperty arrivalCity;

    private final ObjectProperty<LocalTime> timeDep;
    private final ObjectProperty<LocalTime> timeArr;

    private final ObjectProperty<LocalDate> dateDep;
    private final ObjectProperty<LocalDate> dateArr;


//    private final StringProperty depSt;
//    private final StringProperty arrSt;
//
//    private final ListProperty<Car> cars;
//    private final ListProperty<Ticket> tickets;

//    public Train(String id, LocalTime timeDep, LocalTime timeArr, LocalDate dateDep, LocalDate dateArr, String depSt, String arrSt, ObservableList<Car> cars, ObservableList<Ticket> tickets) {
//        this.id = new SimpleStringProperty(id);
//        this.timeDep = new SimpleObjectProperty<>(timeDep);
//        this.timeArr = new SimpleObjectProperty<>(timeArr);
//        this.dateDep = new SimpleObjectProperty<>(dateDep);
//        this.dateArr = new SimpleObjectProperty<>(dateArr);
////        this.depSt = new SimpleStringProperty(depSt);
////        this.arrSt =  new SimpleStringProperty(arrSt);
////        this.cars = new SimpleListProperty<>(cars);
////        this.tickets = new SimpleListProperty<>(tickets);
//    }

//    public Train(String id, LocalTime timeDep, LocalTime timeArr, LocalDate dateDep, LocalDate dateArr, StringProperty depSt, StringProperty arrSt) {
//        this.id = new SimpleStringProperty(id);
//        this.timeDep = new SimpleObjectProperty<>(timeDep);
//        this.timeArr = new SimpleObjectProperty<>(timeArr);
//        this.dateDep = new SimpleObjectProperty<>(dateDep);
//        this.dateArr = new SimpleObjectProperty<>(dateArr);
//    }

    public Train(Long id, String departingCity, String arrivalCity, String depSt, String arrSt, LocalTime timeDep, LocalTime timeArr, LocalDate dateDep, LocalDate dateArr) {
        this.id = new SimpleLongProperty(id);
        this.timeDep = new SimpleObjectProperty<>(timeDep);
        this.timeArr = new SimpleObjectProperty<>(timeArr);
        this.depSt = new SimpleStringProperty(depSt);
        this.arrSt = new SimpleStringProperty(arrSt);
        this.dateDep = new SimpleObjectProperty<>(dateDep);
        this.dateArr = new SimpleObjectProperty<>(dateArr);
        this.departingCity = new SimpleStringProperty(departingCity);
        this.arrivalCity = new SimpleStringProperty(arrivalCity);
    }

    public Train(){
        this(0L, null, null, null, null, null, null, null, null);
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

    public String getDepSt() {
        return depSt.get();
    }

    public StringProperty depStProperty() {
        return depSt;
    }

    public void setDepSt(String depSt) {
        this.depSt.set(depSt);
    }

    public String getArrSt() {
        return arrSt.get();
    }

    public StringProperty arrStProperty() {
        return arrSt;
    }

    public void setArrSt(String arrSt) {
        this.arrSt.set(arrSt);
    }

    public String getDepartingCity() {
        return departingCity.get();
    }

    public StringProperty departingCityProperty() {
        return departingCity;
    }

    public void setDepartingCity(String departingCity) {
        this.departingCity.set(departingCity);
    }

    public String getArrivalCity() {
        return arrivalCity.get();
    }

    public StringProperty arrivalCityProperty() {
        return arrivalCity;
    }

    public void setArrivalCity(String arrivalCity) {
        this.arrivalCity.set(arrivalCity);
    }

    public LocalTime getTimeDep() {
        return timeDep.get();
    }

    public ObjectProperty<LocalTime> timeDepProperty() {
        return timeDep;
    }

    public void setTimeDep(LocalTime timeDep) {
        this.timeDep.set(timeDep);
    }

    public LocalTime getTimeArr() {
        return timeArr.get();
    }

    public ObjectProperty<LocalTime> timeArrProperty() {
        return timeArr;
    }

    public void setTimeArr(LocalTime timeArr) {
        this.timeArr.set(timeArr);
    }

    public LocalDate getDateDep() {
        return dateDep.get();
    }

    public ObjectProperty<LocalDate> dateDepProperty() {
        return dateDep;
    }

    public void setDateDep(LocalDate dateDep) {
        this.dateDep.set(dateDep);
    }

    public LocalDate getDateArr() {
        return dateArr.get();
    }

    public ObjectProperty<LocalDate> dateArrProperty() {
        return dateArr;
    }

    public void setDateArr(LocalDate dateArr) {
        this.dateArr.set(dateArr);
    }

    @Override
    public String toString() {
        return "Поезд " +
                "\nГород отправления:" + departingCity.getValue() +
                "\nСтанция отправления:" + depSt.getValue() +
                "\nДата отправления:" + dateDep.getValue() +
                "\nВремя отправления:" + timeDep.getValue() +
                "\nСтанция прибытия:" + arrSt.getValue() +
                "\nГород прибытия:" + arrivalCity.getValue() +
                "\nВремя прибытия:" + timeArr.getValue() +
                "\nДата прибытия:" + dateArr.getValue();
    }

    //    public Long getId() {
//        return id.get();
//    }
//
//    public LongProperty idProperty() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id.set(id);
//    }
//
//    public String getDepSt() {
//        return depSt.get();
//    }
//
//    public StringProperty depStProperty() {
//        return depSt;
//    }
//
//    public void setDepSt(String depSt) {
//        this.depSt.set(depSt);
//    }
//
//    public String getArrSt() {
//        return arrSt.get();
//    }
//
//    public StringProperty arrStProperty() {
//        return arrSt;
//    }
//
//    public void setArrSt(String arrSt) {
//        this.arrSt.set(arrSt);
//    }
//
//    public String getDepCity() {
//        return departingCity.get();
//    }
//
//    public StringProperty depCityProperty() {
//        return departingCity;
//    }
//
//    public void setDepCity(String depCity) {
//        this.departingCity.set(depCity);
//    }
//
//    public String getArrCity() {
//        return arrivalCity.get();
//    }
//
//    public StringProperty arrCityProperty() {
//        return arrivalCity;
//    }
//
//    public void setArrCity(String arrCity) {
//        this.arrivalCity.set(arrCity);
//    }
//
//    public LocalTime getTimeDep() {
//        return timeDep.get();
//    }
//
//    public ObjectProperty<LocalTime> timeDepProperty() {
//        return timeDep;
//    }
//
//    public void setTimeDep(LocalTime timeDep) {
//        this.timeDep.set(timeDep);
//    }
//
//    public LocalTime getTimeArr() {
//        return timeArr.get();
//    }
//
//    public ObjectProperty<LocalTime> timeArrProperty() {
//        return timeArr;
//    }
//
//    public void setTimeArr(LocalTime timeArr) {
//        this.timeArr.set(timeArr);
//    }
//
//    public LocalDate getDateDep() {
//        return dateDep.get();
//    }
//
//    public ObjectProperty<LocalDate> dateDepProperty() {
//        return dateDep;
//    }
//
//    public void setDateDep(LocalDate dateDep) {
//        this.dateDep.set(dateDep);
//    }
//
//    public LocalDate getDateArr() {
//        return dateArr.get();
//    }
//
//    public ObjectProperty<LocalDate> dateArrProperty() {
//        return dateArr;
//    }
//
//    public void setDateArr(LocalDate dateArr) {
//        this.dateArr.set(dateArr);
//    }
}
