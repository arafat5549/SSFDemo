package foo.test;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.List;
import java.util.Vector;

import org.apache.commons.lang.reflect.FieldUtils;
/** 
 * 生成增删改查SQL
 * @author wyy
 *  
 * */  
public class Bean2Sql 
{  
	private String tableName;
    private Object target ; 
    private String idName ;  
    private Object idValue ;   
    private SqlType currentType ;   
      
    public enum SqlType {  
        INSERT, UPDATE, DELETE ,CREATE  
    }  
      
    public Bean2Sql(SqlType sqlType, Object target,String tableName) { 
    	this.tableName = tableName;
        this.target = target ;   
        switch (sqlType) {  
        case INSERT:  
            currentType = SqlType.INSERT ;  
            createInsert();   
            break;    
        case UPDATE:  
            currentType = SqlType.UPDATE ;  
            createUpdate() ;   
            break;  
        case DELETE:  
            currentType = SqlType.DELETE ;  
            createDelete() ;   
            break ; 
        case CREATE:  
            currentType = SqlType.CREATE ;  
            createTableScheme(this.tableName) ;   
            break ;
        }  
    }  
      
    public Bean2Sql(Class<?> target){  
        String tableName = getTableNameForClass( target );   
        getFields( target );   
          
        StringBuffer sqlBuffer = new StringBuffer() ;   
        sqlBuffer.append("DELETE FROM ").append(tableName).append(" WHERE ") ;   
        for(Field field : fields){  
            if(!Modifier.isStatic(field.getModifiers())){  
                ID id = field.getAnnotation(ID.class);   
                if(null != id){  
                    sqlBuffer.append( field.getName()).append("=?") ;   
                }  
            }  
        }  
        this.sqlBuffer = sqlBuffer.toString() ;   
    }  
      
    /** 
     * 创建跟删除 
     * */  
    private void createDelete() {  
        String tableName = getTableName() ;  
        getFields( target.getClass() );  
        StringBuffer sqlBuffer = new StringBuffer() ;   
        sqlBuffer.append("DELETE FROM ").append(tableName).append(" WHERE ") ;  
        for(Field field : fields){  
            if(!Modifier.isStatic(field.getModifiers())){  
                ID id = field.getAnnotation(ID.class);   
                if(null != id){  
                    sqlBuffer.append( field.getName()).append("=?");  
                    param.add(readField(field));  
                }  
            }  
        }  
        this.sqlBuffer = sqlBuffer.toString();  
    }
    /**
     * 创建建库
     */
    private void createTableScheme(String table)  
    {
    	 String tableName = table!=null ?  table : getTableName();  
         getFields( target.getClass());
         StringBuffer sql     = new StringBuffer();
         StringBuffer colstr  = new StringBuffer();
         StringBuffer pkcolstr =new StringBuffer();
         for(Field field : fields){ 
        	 if(!Modifier.isStatic(field.getModifiers()))
        	 {  
        		 Class<?> type = field.getType();
        		 String fname = field.getName();
              	 colstr.append(fname);  
              	 //
              	 ID id = field.getAnnotation(ID.class);   
              	 if(null != id){  
                	pkcolstr.append("constraint 'PRIMARY' primary key ("+fname+")");
              	 }
              	 //
              	 if(type == String.class){
              		colstr.append("	VARCHAR(20), ");
              	 }
              	 else if(type == Integer.class || type.getName().equals("int")){
              		colstr.append("	INT, ");
              	 }
              	 else if(type == Long.class || type.getName().equals("long")){
              		colstr.append("	BIGINT, ");
              	 }
              	 colstr.append("\n");
                 //param.add(readField(field)); 
             }  
         }
         
         sql.append("create table "+ tableName + "\n(\n"+colstr+pkcolstr+"\n)\n\n");
         this.sqlBuffer = sql.toString(); 
         //System.out.println(sql);
    }
      
    protected Object readField(Field field ){  
        try {  
            return FieldUtils.readField( field , target, true )  ;   
        } catch (Exception e) {  
            throw new RuntimeException( currentType.name() ,e ) ;  
        }  
    }  
      
      
      
    /** 
     * 创建跟新语句 
     * */  
    private void createUpdate() {  
        String tableName = getTableName() ;  
        getFields( target.getClass() );  
        StringBuffer sqlBuffer = new StringBuffer() ;   
        sqlBuffer.append("UPDATE ").append(tableName).append(" SET ") ;   
           
            for(Field field : fields){  
                if(!Modifier.isStatic(field.getModifiers())){  
                    ID id = field.getAnnotation(ID.class);  
                    if(id == null){  
                        sqlBuffer.append( field.getName()).append("=#{"+field.getName()+"},") ;   
                        param.add( readField(field)  ) ;    
                    }else{  
                        idName = field.getName() ;   
                        idValue = readField(field)   ;   
                    }  
                }  
            }  
            if(idName == null){  
                throw new RuntimeException( "not found of " + target.getClass() + "'s ID") ;   
            }  
            sqlBuffer.append(" WHERE ").append(idName)  
            .append("=#{"+idName+"}") ;  
            param.add( idValue );  
           
        this.sqlBuffer = sqlBuffer.toString() ;  
    }  
  
    /** 
     * 根据注解获取表名 
     * */  
    private String getTableName() {  
        String tableName = null ;  
        Class<?> clazz = target.getClass() ;  
        tableName = getTableNameForClass(clazz);  
        return tableName ;  
    }  
  
    private String getTableNameForClass(Class<?> clazz) {  
        String tableName;  
        Table table = clazz.getAnnotation(Table.class) ;   
        if(null != table){  
            tableName = table.name() ;  
            if("".equalsIgnoreCase(tableName)){  
                tableName = clazz.getSimpleName() ;  
            }  
        }else{  
            tableName = clazz.getSimpleName() ;  
        }  
        return tableName;  
    }  
      
    /** 
     * 创建插入语句 
     * */  
    private void createInsert() {  
        String tableName = getTableName() ;  
        getFields( target.getClass() );  
        StringBuffer sqlBuffer = new StringBuffer() ;   
        sqlBuffer.append("INSERT INTO ").append(tableName)  
        .append("(");  
           
            for(Field field : fields){  
                if(!Modifier.isStatic(field.getModifiers())){  
                    ID id = field.getAnnotation(ID.class);  
                    if(id == null){  
                        sqlBuffer.append( field.getName()).append(",") ;   
                        param.add( readField(field) ) ;   
                    }  
                }  
            }  
            int length = sqlBuffer.length() ;  
            sqlBuffer.delete(length-1, length).append(")values(");   
            int size = param.size() ;  
            for(int x=0;x<size;x++){  
                if(x != 0){  
                    sqlBuffer.append(",") ;    
                }  
                sqlBuffer.append("?") ;   
            }  
            sqlBuffer.append(")");  
           
          
        this.sqlBuffer = sqlBuffer.toString() ;  
    }  
      
    private List<Object> param = new Vector<Object>();  
  
    private String sqlBuffer;  
  
    public List<Object> getParam() {  
        return param;  
    }  
  
    public String getSqlBuffer() {  
        return sqlBuffer;  
    }  
      
    public String getIdName() {  
        return idName;  
    }  
  
    public Object getIdValue() {  
        return idValue;  
    }  
    List<Field> fields = new Vector<Field>() ;   
      
    protected void getFields(Class<?> clazz){  
        if(Object.class.equals(clazz)){  
            return ;  
        }  
        Field[]fieldArray = clazz.getDeclaredFields()  ;  
        for(Field file : fieldArray){  
            fields.add( file );  
        }  
        getFields(clazz.getSuperclass());   
    }  
      
    @java.lang.annotation.Target(value={java.lang.annotation.ElementType.TYPE})  
    @java.lang.annotation.Retention(value=java.lang.annotation.RetentionPolicy.RUNTIME)  
    public @interface Table {  
        String name() default "";  
    }  
      
    @java.lang.annotation.Target(value={java.lang.annotation.ElementType.FIELD})  
    @java.lang.annotation.Retention(value=java.lang.annotation.RetentionPolicy.RUNTIME)  
    public @interface ID{  
    }  
     
}  
