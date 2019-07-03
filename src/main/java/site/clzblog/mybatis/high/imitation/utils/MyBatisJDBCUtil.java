package site.clzblog.mybatis.high.imitation.utils;

import site.clzblog.mybatis.high.imitation.session.Datasource;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MyBatisJDBCUtil {
    private static String url = null;
    private static String username = null;
    private static String password = null;
    private static String driver = null;

    public MyBatisJDBCUtil(Datasource dataSource) {
        url = dataSource.getJdbcUrl();
        username = dataSource.getUsername();
        password = dataSource.getPassword();
        driver = dataSource.getDriver();
    }

    private static Connection getConnection() {
        try {
            Class.forName(driver);
            return DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean insertDeleteUpdateExecute(String sql, ArrayList<Object> paras) {
        Connection conn = getConnection();
        PreparedStatement pst = null;
        boolean flag;
        int index = 1;
        try {
            if (conn == null) return false;
            pst = conn.prepareStatement(sql);
            if (paras != null && paras.size() > 0) {
                pst.clearParameters();
                for (Object para : paras) pst.setObject(index++, para);
            }
            int result = pst.executeUpdate();
            flag = result > 0;
        } catch (SQLException e) {
            flag = false;
            e.printStackTrace();
        } finally {
            close(conn, pst);
        }
        return flag;
    }

    public <T> T findBySingleObject(String sql, ArrayList<Object> paras, Class<T> cls) {
        Connection conn = getConnection();
        PreparedStatement pst = null;
        ResultSet rs;
        T singleObject = null;
        int index = 1;

        try {
            if (conn == null) return null;
            pst = conn.prepareStatement(sql);
            if (paras != null && paras.size() > 0) {
                pst.clearParameters();
                for (Object para : paras) {
                    pst.setObject(index++, para);
                }
            }
            rs = pst.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();
            while (rs.next()) {
                singleObject = cls.newInstance();
                for (int i = 0; i < columnCount; i++) {
                    String columnName = rsmd.getColumnName(i + 1);
                    Object columnValue = rs.getObject(columnName);
                    Field field = cls.getDeclaredField(columnName);
                    field.setAccessible(true);
                    field.set(singleObject, columnValue);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(conn, pst);

        }
        return singleObject;

    }

    public <T> List<T> queryListExecute(String sql, Object[] paras, Class<T> cls) {
        Connection conn = getConnection();
        PreparedStatement pst = null;
        ResultSet rs = null;
        T singleObject;
        int index = 1;
        List<T> list = new ArrayList<>();
        try {
            if (conn == null) return null;
            pst = conn.prepareStatement(sql);
            if (paras != null && paras.length > 0) {
                pst.clearParameters();
                for (Object para : paras) {
                    pst.setObject(index++, para);
                }
            }
            rs = pst.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();
            while (rs.next()) {
                singleObject = cls.newInstance();
                for (int i = 0; i < columnCount; i++) {
                    common(cls, rs, singleObject, rsmd, i);
                }
                list.add(singleObject);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(conn, pst, rs);
        }

        return list;
    }

    private <T> void common(Class<T> cls, ResultSet rs, T singleObject, ResultSetMetaData rsmd, int i) throws SQLException, NoSuchFieldException, IllegalAccessException {
        String columnName = rsmd.getColumnName(i + 1);
        Object columdValue = rs.getObject(columnName);
        Field field = cls.getDeclaredField(columnName);
        field.setAccessible(true);
        field.set(singleObject, columdValue);
    }


    private static void close(Connection conn, PreparedStatement pst) {
        try {
            if (pst != null) pst.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void close(Connection conn, PreparedStatement pst, ResultSet rs) {
        try {
            if (rs != null) rs.close();
            if (pst != null) pst.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
