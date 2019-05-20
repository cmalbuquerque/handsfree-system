/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package generalstuff;

/**
 *
 * @author pirex
 */
public class Hash {

    private Hash() {
        //not called

    }

    /**
     *
     * @param txt, text in plain format
     * @param hashType MD5 OR SHA1
     * @return hash in hashType
     */
    public static String getHash(String txt, String hashType) {
        if (txt == null) {
            return "null";
        }
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance(hashType);
            byte[] array = md.digest(txt.getBytes());
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
            }
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException e) {
            return "null";

        }
    }

    public static String getmd5Hash(String txt) {
        return Hash.getHash(txt, "MD5");
    }

    public static String getsha1Hash(String txt) {
        return Hash.getHash(txt, "SHA1");
    }
}