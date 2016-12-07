package foo.lesson.serizable;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Map;

public class JDKSerialization implements SerializationTest,Serializable{

	@Override
	public String getTestName() {
		return "Java";
	}

	@Override
	public Map<String, Object> testBytes2Map(byte[] bytes) {
		Map<String, Object> result = null;
        try {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
            ObjectInputStream inputStream = new ObjectInputStream(byteArrayInputStream);
            result = (Map<String, Object>) inputStream.readObject();
            inputStream.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
	}

	@Override
	public byte[] testMap2Bytes(Map<String, Object> map) {
		 byte[] bytes = null;
	        try {
	            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
	            ObjectOutputStream outputStream = new ObjectOutputStream(byteArrayOutputStream);

	            outputStream.writeObject(map);
	            outputStream.close();

	            bytes = byteArrayOutputStream.toByteArray();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        return bytes;
	}

}
