package sample.model;

import javafx.beans.property.*;
import javafx.collections.ObservableList;

import java.time.LocalDate;
import java.time.LocalTime;

public class Train {

    private final LongProperty id;
    private final ObjectProperty<LocalTime> timeDep;
    private final ObjectProperty<LocalTime> timeArr;

    private final ObjectProperty<LocalDate> dateDep;
    private final ObjectProperty<LocalDate> dateArr;

    private final LongProperty depSt;
    private final LongProperty arrSt;

    private final ListProperty<Car> cars;
    private final ListProperty<Ticket> tickets;

    public long getId() {
        return id.get();
    }

    public LongProperty idProperty() {
        return id;
    }

    public void setId(long id) {
        this.id.set(id);
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

    public long getArrSt() {
        return arrSt.get();
    }

    public LongProperty arrStProperty() {
        return arrSt;
    }

    public void setArrSt(long arrSt) {
        this.arrSt.set(arrSt);
    }

    public long getDepSt() {
        return depSt.get();
    }

    public LongProperty depStProperty() {
        return depSt;
    }

    public void setDepSt(long depSt) {
        this.depSt.set(depSt);
    }

    public ObservableList<Car> getCars() {
        return cars.get();
    }

    public ListProperty<Car> carsProperty() {
        return cars;
    }

    public void setCars(ObservableList<Car> cars) {
        this.cars.set(cars);
    }

    public ObservableList<Ticket> getTickets() {
        return tickets.get();
    }

    public ListProperty<Ticket> ticketsProperty() {
        return tickets;
    }

    public void setTickets(ObservableList<Ticket> tickets) {
        this.tickets.set(tickets);
    }

//    public Train() {
//        this(null, null);
//    }
//
//    public Train(Long id, ObjectProperty<LocalTime> time_dep) {
//        this.id = new SimpleLongProperty(id);
//    }

    public Train(LongProperty id, ObjectProperty<LocalTime> timeDep, ObjectProperty<LocalTime> timeArr, ObjectProperty<LocalDate> dateDep, ObjectProperty<LocalDate> dateArr, LongProperty depSt, LongProperty arrSt, ListProperty<Car> cars, ListProperty<Ticket> tickets){
        this.id = id;
        this.timeDep = timeDep;
        this.timeArr = timeArr;

        this.dateDep = dateDep;
        this.dateArr = dateArr;

        this.depSt = depSt;
        this.arrSt = arrSt;

        this.cars = cars;
        this.tickets = tickets;
    }

    @Override
    public String toString() {
        return "Train{" +
                "id=" + id +
                ", timeDep=" + timeDep +
                ", timeArr=" + timeArr +
                ", dateDep=" + dateDep +
                ", dateArr=" + dateArr +
                ", depSt=" + depSt +
                ", arrSt=" + arrSt +
                ", cars=" + cars +
                ", tickets=" + tickets +
                '}';
    }
}
