package sample.model;

import javafx.beans.property.*;
import javafx.collections.ObservableList;

import java.util.List;

public class User {

    private final LongProperty id;

    private final StringProperty fullName;
    private final StringProperty login;
    private final StringProperty email;
    private final ListProperty<Ticket> tickets;

    public User(Long id, String fullName, String login, String email, List<Ticket> tickets) {
        this.id = new SimpleLongProperty(id);
        this.fullName = new SimpleStringProperty(fullName);
        this.login = new SimpleStringProperty(login);
        this.email = new SimpleStringProperty(email);
        this.tickets = new SimpleListProperty<>((ObservableList<Ticket>) tickets);
    }
    public User() {
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

    public String getFullName() {
        return fullName.get();
    }

    public StringProperty fullNameProperty() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName.set(fullName);
    }

    public String getLogin() {
        return login.get();
    }

    public StringProperty loginProperty() {
        return login;
    }

    public void setLogin(String login) {
        this.login.set(login);
    }

    public String getEmail() {
        return email.get();
    }

    public StringProperty emailProperty() {
        return email;
    }

    public void setEmail(String email) {
        this.email.set(email);
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

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", fullName=" + fullName +
                ", login=" + login +
                ", email=" + email +
                ", tickets=" + tickets +
                '}';
    }
}
