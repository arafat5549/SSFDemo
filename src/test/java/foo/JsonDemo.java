package foo;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import foo.entity.User;

/**
 * 常用的JSON解析包工具<p>
 * 1.jackson  推荐度5星<br>
 * 2.gson     推荐度4星<br>
 * @author wyy
 *
 */
public class JsonDemo 
{
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	//1.jackson
	private JsonGenerator jsonGenerator = null;
	private ObjectMapper objectMapper = null;
	private User bean = null;
	
    @Before
    public void init() {
    	bean = new User("zhangsan","12345","WED111111",1);
        objectMapper = new ObjectMapper();
        try {
        	
            jsonGenerator = objectMapper.getFactory().createGenerator(System.out, JsonEncoding.UTF8);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @After
    public void destory() {
        try {
            if (jsonGenerator != null) {
                jsonGenerator.flush();
            }
            if (!jsonGenerator.isClosed()) {
                jsonGenerator.close();
            }
            jsonGenerator = null;
            objectMapper = null;
            bean = null;
            System.gc();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	@Test
	public void JacksonTest() throws IOException{
		
//		//1.Java对象转Json
//		logger.info("1.将Java对象转Json");
//	    //writeObject可以转换java对象，eg:JavaBean/Map/List/Array等
//	    jsonGenerator.writeObject(bean); 
//	    System.out.println("");
//	    //writeValue具有和writeObject相同的功能
//	    objectMapper.writeValue(System.out, bean);
	    
//	    //2.将Map集合转换成Json字符串
//	    logger.info("2.将Map集合转换成Json字符串");
//        Map<String, Object> map = new HashMap<String, Object>();
//        map.put("name", bean.getName());
//        map.put("account", bean);
//        map.put("account2", new User("e","1","12",1));
//        jsonGenerator.writeObject(map);
//        System.out.println("");
//        //logger.info("objectMapper");
//        //objectMapper.writeValue(System.out, map);
//        
//        //3.将List集合转换成json
//        logger.info("3.将List集合转换成json");
//        List<User> list = new ArrayList<User>();
//        list.add(bean);
//        list.add(new User("address2","2","haha2",1));
//        //list转换成JSON字符串
//        jsonGenerator.writeObject(list);
//        System.out.println();
//        //用objectMapper直接返回list转换成的JSON字符串
//        //System.out.println("1###" + objectMapper.writeValueAsString(list));
//        //System.out.print("2###");
//        //objectMapper.writeValue(System.out, list);
//        
        //4.将json字符串转换成JavaBean对象
//        logger.info("3.将json字符串转换成JavaBean对象");
//        String json = "{\"name\":\"address\",\"id\":\"1\",\"password\":\"email\"}";
//        //String j2=StringEscapeUtils.escapeJava("{"name":"address","id":"1","password":"email"}");
//        logger.info(json);
//        User acc = objectMapper.readValue(json, User.class);
//        System.out.println(acc.getName());
//        System.out.println(acc); 
		
		
		//5.解析特定结构
		String json = IOUtils.toString(new FileInputStream("F:\\data.json"));
		ArrayList<User> userlist = new ArrayList<User>();
		//System.out.println(json); 
		Map maps = objectMapper.readValue(json, Map.class);
		ArrayList<String> list= (ArrayList<String>) maps.get("cols");
		System.out.println(list); 
		ArrayList<Object> list2= (ArrayList<Object>) maps.get("data");
		
		for(Object s:list2){
			ArrayList<String> values = (ArrayList<String>)s;
			User user = new User();
			for (int i=0;i<values.size();i++) 
			{
				String col = list.get(i);
				if(col.equals("name")){
					user.setName(values.get(i));
				}
				else if(col.equals("uid")){
					//user.setUid(Integer.parseInt(values.get(i)));
				}
				else if(col.equals("password")){
					user.setPassword(values.get(i));
				}
				else if(col.equals("email")){
					user.setEmail(values.get(i));
				}
			}
			//System.out.println(user);
			userlist.add(user);
		}
		
		 jsonGenerator.writeObject(userlist);
		//
		
	}
	
	@Test
	public void GsonTest() throws IOException{
		 //GsonBuilder builder = new GsonBuilder();
		
		 Gson gdon = new Gson();
		 String json = "{\"name\":\"address\",\"id\":\"1\",\"password\":\"email\"}";
		 User user =gdon.fromJson(json,User.class);
		 System.out.println("转化成对象:"+user);
		 String str = gdon.toJson(user);
		 System.out.println("转化成JSON:"+str);
		 //gdon.toJson(jsonElement);
//			String json = IOUtils.toString(new FileInputStream("F:\\data.json"));
//			System.out.println(json);
//			User user =gson.fromJson(json, User.class);
//			System.out.println(user);
			List<User> lists = new ArrayList<User>();
			lists.add(user);
			lists.add(user);
			lists.add(user);
			String s2 = gdon.toJson(lists);  
			System.out.println(s2);
			
//			String[] strings = gson.fromJson(json, String[].class);
//			for(String s:strings){
//				System.out.println(s);
//			}
			
//			ArrayList<String> list =new ArrayList<String>();
//			ArrayList<String> list2 =new ArrayList<String>();
//			JsonReader reader = new JsonReader(new StringReader(json));
//			reader.beginObject(); 
//			while (reader.hasNext()) {
//			    String s = reader.nextName();
//			    switch (s) {
//			        case "cols":
//			        	reader.beginArray();
//			        	while(reader.hasNext()){
//			        		String colname = reader.nextString();
//				        	//System.out.println("-"+xxxx);
//			        		list.add(colname);
//			        	}
//			        	reader.endArray();
//			            break;
//			        case "data":
//			        	reader.beginArray();
//			        	while(reader.hasNext()){
//			        		reader.beginArray();
//				        	while(reader.hasNext()){
//				        		String value = reader.nextString();
//				        		list2.add(value);
//				        		//System.out.println("#"+xxxx);
//				        	}
//				        	reader.endArray();
//			        	}
//			        	reader.endArray();
//			            break;
//			    }
//			}
//			reader.endObject();
			
			
//			for (int i=0;i<list2.size();i+=4) 
//			{
//				User user = new User();
//				for (String col : list) {
//					if(col.equals("username")){
//						user.setName(list2.get(i));
//					}
////					if(col.equals("uid")){
////						user.setUid(Integer.parseInt(list2.get(i+1)));
////					}
//					else if(col.equals("password")){
//						user.setPassword(list2.get(i+2));
//					}
//					if(col.equals("email")){
//						user.setEmail(list2.get(i+3));
//					}
//				}
//				System.out.println(user);
//			}
			
//			int indexs[] = new int[list.size()];
//			Field fs[] = User.class.getDeclaredFields();
//			int idx = 0;
//			for(String col : list){
//				for (Field field : fs) 
//				{
//					if(col.equals(field.getName())){
//						System.out.println(col+","+idx);
//					}
//				}
//				idx++;
//			}
			
			
	}
}
