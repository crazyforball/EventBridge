package com.emsrepo.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.security.crypto.codec.Base64;

public class EncodeUtil {

	public static String encodeByBase64(String str) {
		byte[] result = Base64.encode(str.getBytes());
		return new String(result);
	}
	
	public static String decodeByBase64(String str) {
		byte[] result = Base64.decode(str.getBytes());
		return new String(result);
	}
	
	public static String encodeByMD5(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		MessageDigest md5 = MessageDigest.getInstance("MD5");
		return new String(md5.digest(str.getBytes()));
	}
}
