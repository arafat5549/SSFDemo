package foo.lesson.serizable;

import java.util.Map;

/**
 * 序列化性能测试
 * 
 * @author wyy
 * 2016年12月7日
 *
 */
public interface SerializationTest {

    public String getTestName();

    public Map<String, Object> testBytes2Map(byte[] bytes);

    public byte[] testMap2Bytes(Map<String, Object> map);
}
