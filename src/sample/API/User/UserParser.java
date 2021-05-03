package sample.API.User;

import org.json.JSONObject;
import sample.model.User;

import java.io.IOException;

public class UserParser {

    private String json;
    private final UserGet userGet = new UserGet();

    public User getUserByLogin(String login) throws IOException {
        json = userGet.userGetByLogin(login);
        JSONObject obj = new JSONObject(json);

        Long id = Long.parseLong(obj.get("id").toString());
        String fullName = obj.get("fullName").toString();
        String email = obj.get("email").toString();
        String login_json = obj.get("login").toString();

        return new User(id, fullName, login_json, email, null);
    }
}
