package tests.request.user;

import sample.API.User.UserGet;
import sample.API.User.UserParser;

import java.io.IOException;

public class UserGetRequsetTest {

    public static void main(String[] args) throws IOException {
        UserGet userGet = new UserGet();

        UserParser userParser = new UserParser();
        System.out.println(userParser.getUserByLogin("asd"));
    }
}
