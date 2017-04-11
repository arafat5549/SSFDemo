package org.mybatis.generator.internal.types;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class JdbcTypeNameTranslator {
	private static Map<Integer, String> typeToName = new HashMap();
	private static Map<String, Integer> nameToType;

	public static String getJdbcTypeName(int jdbcType) {
		String answer = (String) typeToName.get(Integer.valueOf(jdbcType));
		if (answer == null) {
			answer = "NVARCHAR";
		}

		return answer;
	}

	public static int getJdbcType(String jdbcTypeName) {
		Integer answer = (Integer) nameToType.get(jdbcTypeName);
		if (answer == null) {
			answer = Integer.valueOf(1111);
		}

		return answer.intValue();
	}

	static {
		typeToName.put(Integer.valueOf(2003), "ARRAY");
		typeToName.put(Integer.valueOf(-5), "BIGINT");
		typeToName.put(Integer.valueOf(-2), "BINARY");
		typeToName.put(Integer.valueOf(-7), "BIT");
		typeToName.put(Integer.valueOf(2004), "BLOB");
		typeToName.put(Integer.valueOf(16), "BOOLEAN");
		typeToName.put(Integer.valueOf(1), "CHAR");
		typeToName.put(Integer.valueOf(2005), "CLOB");
		typeToName.put(Integer.valueOf(70), "DATALINK");
		typeToName.put(Integer.valueOf(91), "DATE");
		typeToName.put(Integer.valueOf(3), "DECIMAL");
		typeToName.put(Integer.valueOf(2001), "DISTINCT");
		typeToName.put(Integer.valueOf(8), "DOUBLE");
		typeToName.put(Integer.valueOf(6), "FLOAT");
		typeToName.put(Integer.valueOf(4), "INTEGER");
		typeToName.put(Integer.valueOf(2000), "JAVA_OBJECT");
		typeToName.put(Integer.valueOf(-4), "LONGVARBINARY");
		typeToName.put(Integer.valueOf(-1), "LONGVARCHAR");
		typeToName.put(Integer.valueOf(-15), "NCHAR");
		typeToName.put(Integer.valueOf(2011), "NCLOB");
		typeToName.put(Integer.valueOf(-9), "NVARCHAR");
		typeToName.put(Integer.valueOf(0), "NULL");
		typeToName.put(Integer.valueOf(2), "NUMERIC");
		typeToName.put(Integer.valueOf(1111), "NVARCHAR");
		typeToName.put(Integer.valueOf(7), "REAL");
		typeToName.put(Integer.valueOf(2006), "REF");
		typeToName.put(Integer.valueOf(5), "SMALLINT");
		typeToName.put(Integer.valueOf(2002), "STRUCT");
		typeToName.put(Integer.valueOf(92), "TIME");
		typeToName.put(Integer.valueOf(93), "TIMESTAMP");
		typeToName.put(Integer.valueOf(-6), "TINYINT");
		typeToName.put(Integer.valueOf(-3), "VARBINARY");
		typeToName.put(Integer.valueOf(12), "VARCHAR");

		nameToType = new HashMap();
		nameToType.put("ARRAY", Integer.valueOf(2003));
		nameToType.put("BIGINT", Integer.valueOf(-5));
		nameToType.put("BINARY", Integer.valueOf(-2));
		nameToType.put("BIT", Integer.valueOf(-7));
		nameToType.put("BLOB", Integer.valueOf(2004));
		nameToType.put("BOOLEAN", Integer.valueOf(16));
		nameToType.put("CHAR", Integer.valueOf(1));
		nameToType.put("CLOB", Integer.valueOf(2005));
		nameToType.put("DATALINK", Integer.valueOf(70));
		nameToType.put("DATE", Integer.valueOf(91));
		nameToType.put("DECIMAL", Integer.valueOf(3));
		nameToType.put("DISTINCT", Integer.valueOf(2001));
		nameToType.put("DOUBLE", Integer.valueOf(8));
		nameToType.put("FLOAT", Integer.valueOf(6));
		nameToType.put("INTEGER", Integer.valueOf(4));
		nameToType.put("JAVA_OBJECT", Integer.valueOf(2000));
		nameToType.put("LONGVARBINARY", Integer.valueOf(-4));
		nameToType.put("LONGVARCHAR", Integer.valueOf(-1));
		nameToType.put("NCHAR", Integer.valueOf(-15));
		nameToType.put("NCLOB", Integer.valueOf(2011));
		nameToType.put("NVARCHAR", Integer.valueOf(-9));
		nameToType.put("NULL", Integer.valueOf(0));
		nameToType.put("NUMERIC", Integer.valueOf(2));
		nameToType.put("NVARCHAR", Integer.valueOf(1111));
		nameToType.put("REAL", Integer.valueOf(7));
		nameToType.put("REF", Integer.valueOf(2006));
		nameToType.put("SMALLINT", Integer.valueOf(5));
		nameToType.put("STRUCT", Integer.valueOf(2002));
		nameToType.put("TIME", Integer.valueOf(92));
		nameToType.put("TIMESTAMP", Integer.valueOf(93));
		nameToType.put("TINYINT", Integer.valueOf(-6));
		nameToType.put("VARBINARY", Integer.valueOf(-3));
		nameToType.put("VARCHAR", Integer.valueOf(12));
	}
}
