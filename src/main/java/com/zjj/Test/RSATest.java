package com.zjj.Test;

import com.zjj.utils.RsaUtils;
import org.apache.commons.codec.binary.Base64;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

public class RSATest {
    private static final String SRC = "123456";

    public static void main(String[] args) throws Exception {
        System.out.println("\n");
        RSATest.RsaKeyPair keyPair = generateKeyPair();
        System.out.println("公钥：" + keyPair.getPublicKey());
        System.out.println("私钥：" + keyPair.getPrivateKey());
        System.out.println("\n");
        test1(keyPair);
        System.out.println("\n");
        test2(keyPair);
        System.out.println("\n");
        //
        //String privateKeyText =   "MIIBVAIBADANBgkqhkiG9w0BAQEFAASCAT4wggE6AgEAAkEAzV7ajGcCIpaYczkA\n" +
        //        "89seVkZzWg17gsGMp7llgINTJqKVzVwDeGmkklHjyT+FEUMueCJ5a4dg3d5xEz1V\n" +
        //        "oYpAzQIDAQABAkBV3yKM8IlhAw0tqJkwXd+6fWzAK8EINHvmqcu6R68eCL/NOL8H\n" +
        //        "frm7TBMEY16kp8GyNiAgXtwViyVYWG7TcZHhAiEA5+FKiWFtcO4Y4CR99B+HCYV8\n" +
        //        "N+2/HjhDg1rM/qtEa+kCIQDiu6SyIWT9QuFkbD4TntuiEO287FFEONCgsNO/LHPz\n" +
        //        "RQIhANRhmx19aPn4ejxB8EM65TopUv7++P+61MtozT2srHyRAiAXofVdt8td9o2l\n" +
        //        "uP6Tbvh3oQoWqg8ibEWaqjO39nszfQIgBWmfD+ESyhS2s8lkHnOMrMkwluMNRTRz\n" +
        //        "z+Jj0tNQhpo=";
        //String text = "xUFqBmYnmpRqHs4bzcIucRyJG5ZNUt4FcKnpabQTyImC+r2jeMOZzmJo4tNqQCpE5XzTlQUSvM0O9Aq1Vg9kkw==";
        //System.out.println(decryptByPrivateKey(privateKeyText,text));
    }

    /**
     * 公钥加密私钥解密
     */
    private static void test1(RSATest.RsaKeyPair keyPair) throws Exception {
        System.out.println("***************** 公钥加密私钥解密开始 *****************");
        String text1 = RsaUtils.encryptByPublicKey(keyPair.getPublicKey(), RSATest.SRC);
        String text2 = RsaUtils.decryptByPrivateKey(keyPair.getPrivateKey(), text1);
        System.out.println("加密前：" + RSATest.SRC);
        System.out.println("加密后：" + text1);
        System.out.println("解密后：" + text2);
        if (RSATest.SRC.equals(text2)) {
            System.out.println("解密字符串和原始字符串一致，解密成功");
        } else {
            System.out.println("解密字符串和原始字符串不一致，解密失败");
        }
        System.out.println("***************** 公钥加密私钥解密结束 *****************");
    }

    /**
     * 私钥加密公钥解密
     */
    private static void test2(RSATest.RsaKeyPair keyPair) throws Exception {
        System.out.println("***************** 私钥加密公钥解密开始 *****************");
        String text1 = RsaUtils.encryptByPrivateKey(keyPair.getPrivateKey(), RSATest.SRC);
        String text2 = RsaUtils.decryptByPublicKey(keyPair.getPublicKey(), text1);
        System.out.println("加密前：" + RSATest.SRC);
        System.out.println("加密后：" + text1);
        System.out.println("解密后：" + text2);
        if (RSATest.SRC.equals(text2)) {
            System.out.println("解密字符串和原始字符串一致，解密成功");
        } else {
            System.out.println("解密字符串和原始字符串不一致，解密失败");
        }
        System.out.println("***************** 私钥加密公钥解密结束 *****************");
    }


    /**
     * 构建RSA密钥对
     *
     * @return publicKeyString 公钥 ， privateKeyString 私钥
     */
    public static RsaKeyPair generateKeyPair() throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(1024);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        RSAPublicKey rsaPublicKey = (RSAPublicKey) keyPair.getPublic();
        RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) keyPair.getPrivate();
        String publicKeyString = Base64.encodeBase64String(rsaPublicKey.getEncoded());
        String privateKeyString = Base64.encodeBase64String(rsaPrivateKey.getEncoded());
        return new RsaKeyPair(publicKeyString, privateKeyString);
    }

    /**
     * RSA密钥对对象
     */
    public static class RsaKeyPair {
        private final String publicKey;
        private final String privateKey;

        public RsaKeyPair(String publicKey, String privateKey) {
            this.publicKey = publicKey;
            this.privateKey = privateKey;
        }

        public String getPublicKey() {
            return publicKey;
        }

        public String getPrivateKey() {
            return privateKey;
        }
    }
}
