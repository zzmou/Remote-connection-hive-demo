package tt;

import java.sql.SQLException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.DriverManager;

public class HiveTT {

	// 网上写 org.apache.hadoop.hive.jdbc.HiveDriver ,新版本不能这样写
	private static String driverName = "org.apache.hive.jdbc.HiveDriver";

	// 这里是hive2，网上其他人都写hive,在高版本中会报错
	private static String url = "jdbc:hive2://10.122.141.35:10000/tag_system";
	private static String user = "";
	private static String password = "";
	private static String sql = "";
	private static ResultSet res;

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		Connection conn = null;
		Statement stmt = null;
		try {
			conn = getConn();
			stmt = conn.createStatement();

			sql = "show tables ";
			System.out.println("Running:" + sql);
			res = stmt.executeQuery(sql);
			System.out.println("执行 show tables 运行结果:");
			System.out.println(res.getFetchSize());

			while (res.next()) {
				System.out.println(res.getRow());
				System.out.println(res.getString(1));
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.err.print(driverName + " not found!");
			System.exit(1);
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.print("Connection error!");
			System.exit(1);
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
					stmt = null;
				}
				if (conn != null) {
					conn.close();
					conn = null;
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	private static Connection getConn() throws ClassNotFoundException,
			SQLException {
		Class.forName(driverName);
		Connection conn = DriverManager.getConnection(url, user, password);
		return conn;
	}

	// private static String driverName = "org.apache.hive.jdbc.HiveDriver";
	//
	// public static void main(String[] args) throws SQLException {
	// try {
	// Class.forName(driverName);
	// } catch (ClassNotFoundException e) {
	// e.printStackTrace();
	// System.exit(1);
	// }
	//
	// Connection con = DriverManager.getConnection(
	// "jdbc:hive2://10.122.141.35:10000/default", "", "");
	// Statement stmt = con.createStatement();
	//
	// // show tables
	// String sql =
	// "select userid,genderCode from default.user_register_data where userid='103394262' ";
	// System.out.println("Running: " + sql);
	// ResultSet res = stmt.executeQuery(sql);
	// while (res.next()) {
	// System.out.println(res.getString(1) + "	" + res.getString(2));
	// }
	// System.out.println("end!");
	// stmt.close();
	// con.close();
	// }

}
