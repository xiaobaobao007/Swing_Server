package Mysql_operate;

import java.sql.*;

class BaseDao {
	private static final String driver = "com.mysql.cj.jdbc.Driver";
	private static final String url = "jdbc:mysql://localhost:3306/rpggame?serverTimezone=Asia/Shanghai";
	private static final String user = "root";
	private static final String password = "123456";

	static {
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(url, user, password);
	}

	static void closeAll(Connection conn, Statement stmt, ResultSet rs) throws SQLException {
		if (rs != null) {
			rs.close();
		}
		if (stmt != null) {
			stmt.close();
		}
		if (conn != null) {
			conn.close();
		}
	}


//    public static int executeSQL(String preparedSql, Object[] param) throws ClassNotFoundException {
//        Connection conn = null;
//        PreparedStatement pstmt = null;
//        /* 处理SQL,执行SQL */
//        try {
//            conn = getConnection(); // 得到数据库连接
//            pstmt = conn.prepareStatement(preparedSql); // 得到PreparedStatement对象
//            if (param != null) {
//                for (int i = 0; i < param.length; i++) {
//                    pstmt.setObject(i + 1, param[i]); // 为预编译sql设置参数
//                }
//            }
//        ResultSet num = pstmt.executeQuery(); // 执行SQL语句
//        } catch (SQLException e) {
//            e.printStackTrace(); // 处理SQLException异常
//        } finally {
//            try {
//                BaseDao.closeAll(conn, pstmt, null);
//            } catch (SQLException e) {    
//                e.printStackTrace();
//            }
//        }
//        return 0;
//    }
//    
}