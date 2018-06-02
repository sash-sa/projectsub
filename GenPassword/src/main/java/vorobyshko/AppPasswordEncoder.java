package vorobyshko;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class AppPasswordEncoder {

    public String encode(CharSequence charSequence) {
        return getMD5(sha1(charSequence.toString()));
    }

    public boolean matches(CharSequence charSequence, String s) {
        return encode(charSequence).equals(s);
    }

    String getMD5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest((input + "services.vckp.ru.hidden.salt").getBytes());
            BigInteger number = new BigInteger(1, messageDigest);
            String hashtext = number.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    String sha1(String input) {
        StringBuffer sb = new StringBuffer();
        try {
            MessageDigest mDigest = null;
            mDigest = MessageDigest.getInstance("SHA1");
            byte[] result = mDigest.digest(input.getBytes());
            for (int i = 0; i < result.length; i++) {
                sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            System.out.println(e.getMessage());
        }
        return sb.toString();
    }
}
