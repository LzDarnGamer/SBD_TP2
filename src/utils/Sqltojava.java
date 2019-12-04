package utils;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;

@SuppressWarnings("rawtypes")
public class Sqltojava {
    
	public List<Entry<Object, Class>> row;
    private static Map<String, Class> TYPE;

    static {
        TYPE = new HashMap<String, Class>();

        TYPE.put("INT", Integer.class);
        TYPE.put("INTEGER", Integer.class);
        TYPE.put("TINYINT", Byte.class);
        TYPE.put("SMALLINT", Short.class);
        TYPE.put("BIGINT", Long.class);
        TYPE.put("REAL", Float.class);
        TYPE.put("FLOAT", Double.class);
        TYPE.put("DOUBLE", Double.class);
        TYPE.put("DECIMAL", BigDecimal.class);
        TYPE.put("NUMERIC", BigDecimal.class);
        TYPE.put("BOOLEAN", Boolean.class);
        TYPE.put("CHAR", String.class);
        TYPE.put("VARCHAR", String.class);
        TYPE.put("LONGVARCHAR", String.class);
        TYPE.put("DATE", Date.class);
        TYPE.put("TIME", Time.class);
        TYPE.put("TIMESTAMP", Timestamp.class);
        TYPE.put("SERIAL",Integer.class);
        // ...
    }

    public Sqltojava() {
        row = new ArrayList<Entry<Object, Class>>();
    }

    public <T> void add(T data) {
        row.add(new AbstractMap.SimpleImmutableEntry<Object,Class>(data, data.getClass()));
    }

    public void add(Object data, String sqlType) {
        Class castType = Sqltojava.TYPE.get(sqlType.toUpperCase());

        try {
            this.add(castType.cast(data));
        } catch (NullPointerException e) {
            e.printStackTrace();
            Logger lgr = Logger.getLogger(Sqltojava.class.getName());
            lgr.log(Level.SEVERE, e.getMessage()+" Add the type "+sqlType+" to the TYPE hash map in the Row class.", e);
            throw e;
        }
    }

    private static List<Sqltojava> formTable(ResultSet rs)
            throws SQLException {
    	
    	List<Sqltojava> table = new ArrayList<Sqltojava>();
    	
        if (rs == null)
            return null;

        ResultSetMetaData rsmd;
        try {
            rsmd = rs.getMetaData();

            int NumOfCol = rsmd.getColumnCount();

            while (rs.next()) {
            	Sqltojava current_row = new Sqltojava();

                for (int i = 1; i <= NumOfCol; i++) {
                    current_row.add(rs.getObject(i), rsmd.getColumnTypeName(i));
                }

                table.add(current_row);
            }
           
        } catch (SQLException e) {
            throw e;
        }
        return table;
    }
    
	public static List<Sqltojava> getTable(Connection con, String name) {
		ResultSet rs;
		List<Sqltojava> list = null;
		try {
			Statement stmt = con.createStatement();
			rs = stmt.executeQuery("select * from " + name);
			list = formTable(rs);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
}