package foo.lesson.serizable;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonSerialization implements SerializationTest{

	private JsonGenerator jsonGenerator = null;
	private ObjectMapper objectMapper = null;
	
	public JsonSerialization(){
		 objectMapper = new ObjectMapper();
	     try {
	        	
	         jsonGenerator = objectMapper.getFactory().createGenerator(System.out, JsonEncoding.UTF8);
	      } catch (IOException e) {
	            e.printStackTrace();
	      }
	}
	
	@Override
	public String getTestName() {
		return "jackson";
	}

	@Override
	public Map<String, Object> testBytes2Map(byte[] bytes) {
		 Map<String, Object> result = null;
		 try {
			result = objectMapper.readValue(new String(bytes, "UTF-8"),Map.class);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
         //result = gson.fromJson(new String(bytes, "UTF-8"), Map.class);
	     return result;
	}

	@Override
	public byte[] testMap2Bytes(Map<String, Object> map) {
		
		
		String str = null;
		try {
			str = objectMapper.writeValueAsString(map);
		} catch (JsonProcessingException e1) {
			e1.printStackTrace();
		}
		
        byte[] bytes = null;
        try {
            bytes = str.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return bytes;
	}

}
