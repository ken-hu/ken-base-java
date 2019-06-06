package com.hui.base.common.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * <b><code>AESUtil</code></b>
 * <p/>
 * Description: AES算法加密工具
 * <p/>
 * <b>Creation Time:</b> 2018/12/28 17:44.
 *
 * @author Hu weihui
 */
@Slf4j
public class AESUtil {

    /**
     * 加密的密码
     */
    public static final String sKey = "Hu_hui";
    /**
     * 使用AES算法加密
     */
    private static final String KEY_ALGORITHM = "AES";
    /**
     * 默认的加密算法
     */
    private static final String DEFAULT_CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";

    private static final String charsetName = "utf-8";

    /**
     * AES 加密操作
     *
     * @param content  加密前的内容
     * @param password 加密的密码
     * @return 返回Base64转码后的加密数据
     * @author : Hu weihui
     */
    public static String encrypt(final String content, String password) {
        try {
            // 初始化密码器
            Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);

            byte[] byteContent = content.getBytes();

            // 初始化为加密模式的密码器
            cipher.init(Cipher.ENCRYPT_MODE, getSecretKey(password));

            // 加密处理
            byte[] result = cipher.doFinal(byteContent);

            //通过Base64转码返回
            return Base64.encodeBase64String(result);
        } catch (Exception ex) {
            log.info("AESUtil 加密失败", ex);
        }
        return null;
    }

    /**
     * AES 解密操作
     *
     * @param content
     * @param password
     * @return
     */
    public static String decrypt(String content, String password) {

        try {
            //实例化
            Cipher cipher = Cipher.getInstance(KEY_ALGORITHM);

            //使用密钥初始化，设置为解密模式
            cipher.init(Cipher.DECRYPT_MODE, getSecretKey(password));

            //执行操作
            byte[] result = cipher.doFinal(Base64.decodeBase64(content));

            return new String(result, charsetName);
        } catch (Exception ex) {
            log.info("AESUtil 解密失败", ex);
        }

        return null;
    }

    /**
     * 生成加密秘钥
     *
     * @return
     */
    private static SecretKeySpec getSecretKey(final String password) throws UnsupportedEncodingException {

        KeyGenerator kg = null;

        try {
            kg = KeyGenerator.getInstance(KEY_ALGORITHM);

            kg.init(128, new SecureRandom(password.getBytes()));

            SecretKey secretKey = kg.generateKey();

            return new SecretKeySpec(secretKey.getEncoded(), KEY_ALGORITHM);
        } catch (NoSuchAlgorithmException ex) {
            log.error("生成加密秘钥失败");
        }

        return null;

    }

    /**
     * Main测试
     *
     * @param args the input arguments
     * @author : Hu weihui
     * @since nile -szcst 0.1.0
     */
    public static void main(String[] args) {
        String s = "admin";

        System.out.println("get msg:" + s);

        String s1 = AESUtil.encrypt(s, "admin");

        System.out.println("after encrypt: " + s1);

        System.out.println("after decrypt: " + AESUtil.decrypt(s1, "admin"));
    }
}
