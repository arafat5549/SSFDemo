package foo.database;


import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author yinbin
 *
 * @param <T>
 */
public interface IResultSetCall<T> {

	public T invoke(ResultSet rs) throws SQLException;

}
