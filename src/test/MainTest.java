package test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;

import org.junit.Test;

import com.google.common.base.CharMatcher;
import com.google.common.base.Function;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.google.common.primitives.Ints;
import com.ssf.service.AccountService;

public class MainTest {
  
	@Test
	public void regxTest(){
		StringBuffer address = new StringBuffer();
		address.append("131231232");
//		for(int i=0;i<10;i++){
//			address.append("a");
//		}
		
		System.out.println(address.toString());
		boolean f = address.toString().matches(".{0,50}");
		System.out.println(f);
	}
	
	@Test
	public void UUIDTest(){
		for(int i=0;i<10;i++){
			String ordercode = UUID.randomUUID().toString().replace("-", "");
			System.out.println(ordercode.length()+" , " + ordercode);
		}
		
	}
   /**
    * GuavaTest
    */
	@Test
	public void guavaTest(){
		 String oiids = "1,2,3,4,52,.c";
		 //Guava
		 
	     List<String> result = Splitter.on(",").trimResults().
	    		 omitEmptyStrings().splitToList(oiids);  
	     List<Integer> lists = Lists.transform(result, new Function<String, Integer>() {
				@Override
				public Integer apply(String str) {
					return Ints.tryParse(str);
				}
	     });
	     //
	     List<Integer> is = new ArrayList<Integer>();
	     String os[] =  oiids.split(",");
	     for (String string : os) {
	    	boolean flag = string.matches("[0-9]+");
	    	if(flag)
	    	{
	    		int i =  Integer.parseInt(string);
		    	is.add(i);
	    	}
		 }
	    
	     
	     System.out.println(result);
	     System.out.println(lists);
	     System.out.println(is);
	}
	
	
  static AccountService accountService = new AccountService();
  
  public static void main(String[] args) {
	  
	    
		Runnable r = new Runnable() {
			@Override
			public void run() {
				
				accountService.transfer(2, 1, 100,false); 
				accountService.transfer(2, 3, 100,false);
				
				try {
					Thread.sleep(1000L);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("aaaaaaaaaaaaaaa");
			}
		};
		
		for (int i = 0; i < 100; i++) {
			new Thread(r).start();
		}
  }
}
