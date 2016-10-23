package foo.test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.Reader;
import java.net.URL;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class DataPopulator 
{
	private static Logger logger = LoggerFactory.getLogger(DataPopulator.class);
	private static final Properties PROPERTIES = new Properties();
	
	static
	{
		try {
			
			InputStream is = DataPopulator.class.getClassLoader().getResourceAsStream("jdbc.properties");
			PROPERTIES.load(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() 
	{
		String driver   = PROPERTIES.getProperty("jdbc.driverClassName");
		String url      = PROPERTIES.getProperty("jdbc.url");
		String username = PROPERTIES.getProperty("jdbc.username");
		String password = PROPERTIES.getProperty("jdbc.password");
		Connection connection = null;
		try {
			Class.forName(driver);
			connection = DriverManager.getConnection(url, username, password);
		} catch (Exception e) {
			throw new RuntimeException(e);
		} 
		return connection;
	}
	
    public static boolean scwj(String path, String FileName, String body) 
    {
        try {
            File f = new File(path);
            System.out.println(f);
            f.mkdirs();
            path = path + "\\" + FileName;
            f = new File(path);
            PrintWriter out;
            out = new PrintWriter(new FileWriter(f));
            out.print(body + "\n");
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
	
    /**
     * 根据数据库元数据生成建库Sql
     */
	public static void db2ddl(){
        Connection conn = null;
        try {
            //DB2 com.ibm.db2.jcc.DB2Driver "jdbc:db2://localhost:50000/abc", "db2admin","123456"
            //MYSQL com.mysql.jdbc.Driver "jdbc:mysql://localhost:3306/manager","root","root"
            //MSSQLSERVER net.sourceforge.jtds.jdbc.Driver "jdbc:jtds:sqlserver://localhost:1433/abc","sa","123456"
            conn = getConnection();
            DatabaseMetaData odmd = conn.getMetaData();
//          String[] types = { "TABLE" };// 数组变量types
            ResultSet rs = odmd.getTables(null, null, null, null);
//          odmd.getMaxTableNameLength()
            StringBuffer sql = new StringBuffer();
            int counti=1;
            while (rs.next()) {
                // 取表名
                String Tablename = rs.getString(3);
                if (StringUtils.equalsIgnoreCase(rs.getString(4), "TABLE")
//      && StringUtils.equalsIgnoreCase(rs.getString(2),"abc")
//      &&StringUtils.equalsIgnoreCase(rs.getString(3), "ADDetail")
                ) {
                    System.out.println(counti+"-"+Tablename);
                    String commnt = "";
                    String indexu = "";
                    ResultSet pkRSet = odmd.getPrimaryKeys(null, null,Tablename);
                    ResultSet rscol = odmd.getColumns(null, null,Tablename, null);
                    ResultSet inset = odmd.getIndexInfo(null, null, Tablename, false ,true );
                    String colstr = "";
                    while (rscol.next()) {
                        String ColumnName = rscol.getString(4);
                        String ColumnTypeName = rscol.getString(6);
                        String REMARKS  = rscol.getString(12);
                        if(StringUtils.isNotBlank(REMARKS)){
                            commnt = commnt+"COMMENT ON "+Tablename+" ( "+ColumnName+" IS '"+REMARKS+"' ); \n";
                        }
                        while(inset.next()){
                            if(inset.getInt(7)==DatabaseMetaData.tableIndexOther){
                                 indexu = indexu+"CREATE  UNIQUE  INDEX "+inset.getString(6)+" ON "+inset.getString(5)+"("+inset.getString(9)+");\n";
                            }
                            System.out.println();
                        }
                         
                        int displaySize = rscol.getInt(7);
                        int scale = rscol.getInt(9);
                        // int Precision = displaySize-scale;
                        if(StringUtils.isNotBlank(colstr)){
                            colstr = colstr+",\n";
                        }
                        colstr =colstr+"\t" + ColumnName + "\t";
                        if (StringUtils.indexOf(ColumnTypeName,"identity")>=0){
                            colstr =colstr+ColumnTypeName + "(1,1)";
                        }else if (StringUtils.equalsIgnoreCase(ColumnTypeName,"timestamp")
                            ||StringUtils.equalsIgnoreCase(ColumnTypeName,"int")
                            ||StringUtils.equalsIgnoreCase(ColumnTypeName,"datetime")
                            ||StringUtils.equalsIgnoreCase(ColumnTypeName,"long")
                            ||StringUtils.equalsIgnoreCase(ColumnTypeName,"date")
                            ||StringUtils.equalsIgnoreCase(ColumnTypeName,"text")
                            ||StringUtils.equalsIgnoreCase(ColumnTypeName,"image")
                            ||StringUtils.equalsIgnoreCase(ColumnTypeName,"bit")
                            ||StringUtils.equalsIgnoreCase(ColumnTypeName,"ntext")
                            ) {
                            colstr =colstr+ColumnTypeName + "";
                        } else if (StringUtils.equalsIgnoreCase(ColumnTypeName,"decimal")
                                || StringUtils.equalsIgnoreCase(ColumnTypeName,"number")
                                || StringUtils.equalsIgnoreCase(ColumnTypeName,"double")) {
                            if (scale == 0)
                                colstr =colstr+ColumnTypeName + "(" + displaySize+ ")";
                            else
                                colstr =colstr+ColumnTypeName + "(" + displaySize+ "," + scale + ")";
                        } else {
                            colstr =colstr+ColumnTypeName + "(" + displaySize + ")";
                        }
                        String defaultstr = rscol.getString(13);
                        if(defaultstr!=null)
                        colstr =colstr+"\t default "+defaultstr;
                        if (rscol.getInt(11) == DatabaseMetaData.columnNoNulls) {
                            colstr =colstr+"\tnot null";
                        } else if (rscol.getInt(11) == DatabaseMetaData.columnNullable) {
                            // sql.append("\tnull");
                        }
                    }
                    String pkcolstr = "";
                    while (pkRSet.next()) {
 
                        if(StringUtils.isNotBlank(pkcolstr)){
                            pkcolstr = pkcolstr+",\n";
                        }else{
                            if(StringUtils.isNotBlank(colstr)){
                                colstr = colstr+",\n";
                            }
                        }
                        pkcolstr = pkcolstr+"\tconstraint \"" + pkRSet.getObject(6)+ "\" primary key (" + pkRSet.getObject(4)+ ")";
                    }
                    sql.append("create table "+ Tablename + "\n("+colstr+pkcolstr+"\n)\n\n");
                    System.out.println("create table "+ Tablename + "\n("+colstr+pkcolstr+"\n);\n"+commnt+""+indexu+"\n");
                }
                counti++;
            }
            scwj("F:\\", "abc.sql", sql.toString());
            rs.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            try {
                if(conn!=null) conn.close();
            } catch (SQLException e) {
            }
        }
    }
	/**
	 * 根据JavaBean生成建库语句
	 */
	public static void javaBean2ddl(){
		
	}
	/**
	 * 初始化数据库
	 */
	public static void initDatabase()
	{
		Connection connection = null;
		Reader reader = null;
		try {
			connection = getConnection();
			ScriptRunner scriptRunner = new ScriptRunner(connection);
			//reader = Resources.getResourceAsReader("sql/drop_tables.sql");
			//scriptRunner.runScript(reader);
			//logger.info("drop_tables.sql executed successfully");
			reader = Resources.getResourceAsReader("sql/users.sql");
			scriptRunner.runScript(reader );
			logger.info("create_tables.sql executed successfully");
//			reader = Resources.getResourceAsReader("sql/sample_data.sql");
//			scriptRunner.runScript(reader );
//			logger.info("sample_data.sql executed successfully");
			connection.commit();
			reader.close();
			scriptRunner.closeConnection();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
	}
	
	
	
	public static void main(String[] args) {
		
//		String path = System.getProperty("user.dir")+"\\src\\main\\resources\\sql\\";
//		System.out.println("path="+path);
//		Bean2Sql b2q = new Bean2Sql(SqlType.CREATE,new User(),"users");
//		System.out.println("sql="+b2q.getSqlBuffer());
//		scwj(path, "users.sql", b2q.getSqlBuffer().toString());
		
		///initDatabase();
		URL url = DataPopulator.class.getClassLoader().getResource("");
		System.out.println(url);
	}
}
