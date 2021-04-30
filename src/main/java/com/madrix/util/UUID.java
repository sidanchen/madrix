package com.madrix.util;

import java.text.SimpleDateFormat;
import java.util.Random;

public class UUID {
	public static String randomUUID(){
		Random ran = new Random();
		return System.currentTimeMillis() + "" + ran.nextInt(999999999);
	}
}
