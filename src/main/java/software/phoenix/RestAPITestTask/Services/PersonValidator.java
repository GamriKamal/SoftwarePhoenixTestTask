package software.phoenix.RestAPITestTask.Services;

import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
public class PersonValidator {

    private static final String USERNAME_REGEX = "^[a-zA-Z0-9_-]{3,16}$";
    private static final String PASSWORD_REGEX = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,20}$";
    private static final String AVATAR_URL_REGEX = "^https?://.*\\.(jpeg|jpg|gif|png)$";

    public static boolean validateUsername(String username) {
        return Pattern.matches(USERNAME_REGEX, username);
    }

    public static boolean validatePassword(String password) {
        return Pattern.matches(PASSWORD_REGEX, password);
    }

    public static boolean validateAvatarUrl(String avatarUrl) {
        return Pattern.matches(AVATAR_URL_REGEX, avatarUrl);
    }
}
