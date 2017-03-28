package com.ssf.utils.test;

import java.math.BigDecimal;

public class DecimalUtils {

	public static String formatPrice(BigDecimal bd,int num){  
	    if(bd == null || num < 0){  
	        return null;  
	    }  
	    bd = bd.setScale(num, BigDecimal.ROUND_HALF_UP);  
	    return bd + "";  
	}  
	
	
	public static void main(String[] args) {
		BigDecimal bd = new BigDecimal(1.1234);
		
		String str = formatPrice(bd,2);
		System.out.println(bd);
		System.out.println(str);
	}
}
