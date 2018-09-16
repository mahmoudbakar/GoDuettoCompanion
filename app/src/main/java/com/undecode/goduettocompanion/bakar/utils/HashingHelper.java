package com.undecode.goduettocompanion.bakar.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;

public class HashingHelper {

    public static String getHashedValue(String value) {
        String sha1 = "";
        try {
            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
            crypt.reset();
            crypt.update(value.getBytes("UTF-8"));
            sha1 = byteToHex(crypt.digest());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return sha1;
    }

    private static String byteToHex(byte[] digest) {
        Formatter formatter = new Formatter();
        for (byte b : digest)
        {
            formatter.format("%02X", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }
}
