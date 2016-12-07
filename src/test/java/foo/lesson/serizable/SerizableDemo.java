package foo.lesson.serizable;

import java.util.Map;

import org.junit.Test;

import com.google.common.collect.Maps;

public class SerizableDemo {

	@Test
	public void Stest(){
		JDKSerialization jdk = new JDKSerialization();
		JsonSerialization json = new JsonSerialization();
		Map<String, Object> map = Maps.newHashMap();
		map.put("id", 1);
		map.put("pId", -1);
		map.put("name", "根节点");
		
		long begin = System.currentTimeMillis();
		for(int i=0;i<10000;i++){
			byte[] b= jdk.testMap2Bytes(map);
			Map<String, Object> result= jdk.testBytes2Map(b);
			//System.out.println(result);
		}
		System.out.println("end="+(System.currentTimeMillis() - begin));
		
		
		begin = System.currentTimeMillis();
		for(int i=0;i<10000;i++){
			byte[] b= json.testMap2Bytes(map);
			Map<String, Object> result= json.testBytes2Map(b);
			//System.out.println(result);
		}
		System.out.println(json.getTestName()+"end="+(System.currentTimeMillis() - begin));
	}
}
