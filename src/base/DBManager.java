package base;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBManager {
	public static Connection getConnection() {//connectionにも色々あるらしい。sql以外に。
		Connection con = null; //初期化
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/ec_db?useUnicode=true&characterEncoding=utf8&useSSL=false", "root", "password");
			System.out.println("DBつながりましたー");
			return con;
		}catch (ClassNotFoundException e) {//このeの名前忘れた
			throw new IllegalMonitorStateException();//イリーガルモニターとは
		}catch(SQLException e) {
			throw new IllegalMonitorStateException();
		}
	}
}
