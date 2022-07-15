package cc.jktu.api.util;

import org.mindrot.jbcrypt.BCrypt;

public final class BcryptUtil {

    private BcryptUtil() {
    }

    public static String hashPassword(String plain) {
        return BCrypt.hashpw(plain, BCrypt.gensalt());
    }

    public static Boolean checkPassword(String plain, String hashed) {
        return BCrypt.checkpw(plain, hashed);
    }

}
