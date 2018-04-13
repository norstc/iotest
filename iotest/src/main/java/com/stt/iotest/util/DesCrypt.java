package com.stt.iotest.util;

import java.security.Key;
import java.security.SecureRandom;
import java.security.Security;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ClassName:DesCrypt  <br/>
 * Function: 提供3DES加密/解密. <br/>
 * Date: 2016年9月18日 下午5:12:13 <br/>
 * @author guimw@sh-stt.com 
 * @version
 * @since JDK 1.6
 * @see
 */
public class DesCrypt {

	private static final Logger logger = LoggerFactory.getLogger(DesCrypt.class);
	
	byte[] PASSWORD_CRYPT_KEY = new byte[] { (byte) 0xcb, (byte) 0xa3,
			(byte) 0xab, (byte) 0xce, (byte) 0x05, (byte) 0x1c, (byte) 0xe2,
			(byte) 0xfe, (byte) 0xd7, (byte) 0x03, (byte) 0xe7, (byte) 0xe3,
			(byte) 0x47, (byte) 0xe8, (byte) 0x46, (byte) 0x5a, (byte) 0x43,
			(byte) 0xd3, (byte) 0xa8, (byte) 0x9c, (byte) 0xae, (byte) 0xc7,
			(byte) 0xb1, (byte) 0x0f };
	byte[] PASSWORD_IV = new byte[] { (byte) 0xa2, (byte) 0xcf, (byte) 0x86,
			(byte) 0x87, (byte) 0xa8, (byte) 0x69, (byte) 0xfb, (byte) 0x42 };

	
	static DesCrypt desCrypt=null;
	
	public static DesCrypt getInstance(){
		if(desCrypt==null){
			desCrypt=new DesCrypt();
		}
		return desCrypt;
	}
	
	/*
	 * 3DES加密
	 */
	public String encrypt(String clearText) {
		try {
			SecureRandom sr = new SecureRandom();
			DESedeKeySpec dks = new DESedeKeySpec(PASSWORD_CRYPT_KEY);
			SecretKeyFactory keyFactory = SecretKeyFactory
					.getInstance("DESede");
			SecretKey securekey = keyFactory.generateSecret(dks);
			IvParameterSpec iv = new IvParameterSpec(PASSWORD_IV);
			Cipher cipher = Cipher.getInstance("DESede/CBC/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, securekey, iv, sr);

			if (clearText == null){
				return null;
			}

			byte[] utf8 = clearText.getBytes("UTF8");

			//加密
			byte[] enc = cipher.doFinal(utf8);

			// 加密完byte[] 后，需要将加密了的byte[] 转换成base64保存

			return new String(Base64.encodeBase64(enc), "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("加密失败" + e.getMessage(), e);
			return null;
		}
	}

	/*
	 * 3DES解密
	 */
	public String decrypt(String Ciphertext) {
		try {
			SecureRandom sr = new SecureRandom();
			DESedeKeySpec dks = new DESedeKeySpec(PASSWORD_CRYPT_KEY);
			SecretKeyFactory keyFactory = SecretKeyFactory
					.getInstance("DESede");
			SecretKey securekey = keyFactory.generateSecret(dks);
			IvParameterSpec iv = new IvParameterSpec(PASSWORD_IV);
			Cipher cipher = Cipher.getInstance("DESede/CBC/PKCS5Padding");
			cipher.init(Cipher.DECRYPT_MODE, securekey, iv, sr);

			//解密前，需要将字符转换成byte[]
			byte[] dec = Base64.decodeBase64(Ciphertext.getBytes());

			//解密
			byte[] utf8 = cipher.doFinal(dec);
			return new String(utf8, "UTF8");

		} catch (Exception e) {
			//System.out.println("<!  解密失败    ------------------------->");
			e.printStackTrace();
			logger.error("解密失败" + e.getMessage(), e);
			return null;
		}
	}

	/*
	 * DES加密
	 */
	public String encrypt(String clearText, String password) {
		try {
			// DES算法要求有一个可信任的随机数源
			SecureRandom sr = SecureRandom.getInstance("SHA1PRNG","SUN");			
			Security.addProvider(new com.sun.crypto.provider.SunJCE());
			KeyGenerator kg = null;						
			kg = KeyGenerator.getInstance("DES");
	
			sr.setSeed(password.getBytes());    
			kg.init(sr);
			//产生密钥			
			Key key = kg.generateKey();					

			Cipher cipher = Cipher.getInstance("DES");
			//用密匙初始化Cipher对象
			cipher.init(Cipher.ENCRYPT_MODE, key, sr);

			if (clearText == null){
				return null;
			}

			byte[] utf8 = clearText.getBytes("UTF8");

			//加密
			byte[] enc = cipher.doFinal(utf8);

			//加密完byte[] 后，需要将加密了的byte[] 转换成base64保存

			return new String(Base64.encodeBase64(enc), "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("DES加密失败" + e.getMessage(), e);
			return null;
		}
	}

	/*
	 * DES解密
	 */
	public String decrypt(String Ciphertext, String password) {
		try {
			// DES算法要求有一个可信任的随机数源
			SecureRandom sr = SecureRandom.getInstance("SHA1PRNG","SUN");			
			Security.addProvider(new com.sun.crypto.provider.SunJCE());
			KeyGenerator kg = null;						
			kg = KeyGenerator.getInstance("DES");
	
			sr.setSeed(password.getBytes());    
			kg.init(sr);
			//产生密钥			
			Key key = kg.generateKey();	
		    
			//Cipher对象实际完成解密操作
			Cipher cipher = Cipher.getInstance("DES");
			// 用密匙初始化Cipher对象
			cipher.init(Cipher.DECRYPT_MODE, key, sr);

			//解密前，需要将字符转换成byte[]
			byte[] dec = Base64.decodeBase64(Ciphertext.getBytes());

			//解密
			byte[] utf8 = cipher.doFinal(dec);
			return new String(utf8, "UTF8");

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("DES解密失败" + e.getMessage(), e);
			return null;
		}
	}

	// 认证加密信息
	public String authentication(String a) {
		try {
			// <账号>@<省代码>@<生成时间>@<随机数>
			// bSoDSYnIuOy8aiSNATnXhMs21bZQj8bEZfHoEksf2s4=
			// 为<gd>@<220>@<2012-07-31 16:00:01>@<65453215>加密后的字符串
			String str = null;
			if (null != a && !a.equals("")) {
				String rz = a.replace(" ", "+");
				// 解密
				str = decrypt(rz);
			}
			// 验证解密是否成功
			if (null == str || str.equals("")) {
				return "fail";
			}
			String[] authentication = str.split("@");
			// 账号
			String account = authentication[0].replace("<", "")
					.replace(">", "");
			// 省代码
			String provCode = authentication[1].replace("<", "").replace(">",
					"");
			// 生成时间
			String generationTime = authentication[2].replace("<", "").replace(
					">", "");

			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date = new Date();
			Date time = df.parse(generationTime);

			long second = (date.getTime() - time.getTime()) / (1000);
			// if(second>=180){
			// return "fail";
			// }
			return provCode + "@" + account;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("认证加密信息失败" + e.getMessage(), e);
			return "fail";
		}
	}

}