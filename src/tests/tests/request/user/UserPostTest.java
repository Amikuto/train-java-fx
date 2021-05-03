package tests.request.user;

import sample.API.User.UserPost;
import sample.model.User;

import java.io.IOException;

public class UserPostTest {
    public static void main(String[] args) throws IOException {
        User user = new User(0L, "FullName", "Login", "Email", null);
        UserPost.addNewUser("FullName", "Login", "Email", "123");
    }
}
