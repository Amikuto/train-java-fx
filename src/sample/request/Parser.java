package sample.request;

import org.json.*;

import java.io.IOException;
import java.util.ArrayList;

public class Parser {

    private final Request request = new Request();

    public Parser() throws IOException {
    }

    public ArrayList<Object> getAll(Integer id) throws IOException {

        ArrayList<Object> data = new ArrayList();
        JSONArray obj = new JSONArray(request.requestGET());

        JSONObject user = obj.getJSONObject(id);

        String name = user.getString("name");
        Integer postalCode = user.getInt("postalCode");
        String street = user.getString("street");
        String surname = user.getString("surname");
        String city = user.getString("city");
        String birthday = user.getString("birthday");

        data.add(name);
        data.add(surname);
        data.add(street);
        data.add(city);
        data.add(postalCode);
        data.add(birthday);

        return(data);
    }

    public String getName(Integer id) throws IOException {
        return (String) getAll(id).get(0);
    }

    public String getSurname(Integer id) throws IOException {
        return (String) getAll(id).get(1);
    }

    public String getStreet(Integer id) throws IOException {
        return (String) getAll(id).get(2);
    }

    public String getCity(Integer id) throws IOException {
        return (String) getAll(id).get(3);
    }

    public String getPostalCode(Integer id) throws IOException {
        return getAll(id).get(4).toString();
    }

    public String getBirthDay(Integer id) throws IOException {
        return (String) getAll(id).get(5);
    }
}
