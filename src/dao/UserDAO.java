package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import base.DBManager;
import beans.UserDataBeans;
import ec.EcHelper;

public class UserDAO {

	//todo:新規登録する

	/**
	 * @param
	 * @return
	 */

	public static void insertUser(UserDataBeans udb) throws SQLException {
		Connection con = null;
		PreparedStatement st = null;

		try {
			con = DBManager.getConnection(); //dbと繋げる
			st = con.prepareStatement(
					"INSERT INTO t_user(name,login_id,address,login_password,create_date) VALUES (?,?,?,?,?)");
			st.setString(1, udb.getName());
			st.setString(2, udb.getLoginId());
			st.setString(3, udb.getAddress());
			st.setString(4, EcHelper.getSha256(udb.getLoginPassword())); //パスワードをgetSha256に入れて実行したものを詰める
			st.setTimestamp(5, new Timestamp(System.currentTimeMillis()));
			st.executeUpdate();
			System.out.println("inserting user has been completed");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new SQLException(e);
		} finally {
			if (con != null) {
				con.close();
			}
		}
	}

	//todo:ログインする
	public static int getUserId(String loginId , String password)throws SQLException {
		Connection con = null;
		PreparedStatement st = null;

		try {
			con = DBManager.getConnection();
			//password は暗号なので一旦loginidだけとる。
			st = con.prepareStatement("SELECT * FROM t_user WHERE login_id = a");
			st.setString(1, loginId);
			ResultSet rs = st.executeQuery();

			int userId = 0; //whileの手前で初期化しないとreturnできない。
			while (rs.next()) {
				if (EcHelper.getSha256(password).equals(rs.getString("login_password"))) {
					userId = rs.getInt("id");
					System.out.println("loginできた-");
					break;
				}
			}
			return userId;
		}catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new SQLException(e); //throw s ではない。sない。
		} finally {
			if (con != null) {
				con.close();
			}
		}
	}

	//todo:編集する
	public static void updateUser(UserDataBeans udb) throws SQLException {
		Connection con = null;
		PreparedStatement st = null;

		try {
			con = DBManager.getConnection();
			st = con.prepareStatement("UPDATE t_user SET name= ?, address= ?, login_id= ? WHERE id= ?");
			st.setString(1, udb.getName());
			st.setString(2, udb.getLoginId());
			st.setString(3, udb.getAddress());
			st.setInt(4, udb.getId());
			st.executeUpdate();//ここで編集を実行。まだ編集内容を返してはいない。

			st = con.prepareStatement("SELECT name, login_id, address FROM ec_db.t_user WHERE id =" + udb.getId());
			ResultSet rs = st.executeQuery();

			UserDataBeans updateUdb = new UserDataBeans();//この名前を返す

			while (rs.next()) {

				updateUdb.setName(rs.getString("name"));
				updateUdb.setLoginId(rs.getString("login_id"));
				updateUdb.setAddress(rs.getString("address"));
			}

			st.close();
			System.out.println("searching update-UserDataBeans has been completed");

			} catch (SQLException e) {
				System.out.println(e.getMessage());
				throw new SQLException(e);
			} finally {
				if (con != null) {
					con.close();
				}
			}
	}
	private static boolean isOverlapLoginId(String loginId) {
		boolean isOverlap = false;
		Connection con = null;
		PreparedStatement st = null;

		try {
			con = DBManager.getConnection();
			st = con.prepareStatement("");
		}
	}
}
//todo:ユーザ一覧する
//todo:ユーザの一人を参照する