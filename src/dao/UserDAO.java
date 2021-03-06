package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

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
			st = con.prepareStatement("SELECT * FROM t_user WHERE login_id = ?");
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

	//ログインIdが被ってないかどうか調べる
	public static boolean isOverlapLoginId(String loginId) throws SQLException {//throw sです

		boolean isOverlap = false; //false で初期化
		Connection con = null;
		PreparedStatement st = null;

		try {
			con = DBManager.getConnection();
			st = con.prepareStatement("SELECT login_id FROM t_user WHERE login_id = ? ");
			st.setString(1, loginId);
			ResultSet rs = st.executeQuery();

			System.out.println("seaching loginId by inputLoginId has been completed!!");

			if (rs.next()) {
				isOverlap = true;
			}
		}catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new SQLException(e);
		} finally {
			if (con != null) {
				con.close();
			}
		}
		System.out.println("overlap check has been completed");
		return isOverlap;
	}

	//todo:ユーザ一覧する
	public static ArrayList<UserDataBeans> UserAll() throws SQLException{
		Connection con = null;
		PreparedStatement st = null;
		ArrayList<UserDataBeans> userList = new ArrayList<UserDataBeans>();

		try {
			con = DBManager.getConnection();

			st = con.prepareStatement("SELECT * FROM t_user WHERE id NOT IN (2)");

			ResultSet rs = st.executeQuery();

			while(rs.next()) {
				 int id = rs.getInt("id");
				 String name = rs.getString("name");
				 String address = rs.getString("address");
				 String loginId = rs.getString("loginId");
				 String loginPassword = rs.getString("loginPassword");
				 Date createDate = rs.getDate("createDate");
				 UserDataBeans user = new UserDataBeans(id,name,address,loginId,loginPassword,createDate);
				 userList.add(user);
			}
			}catch (SQLException e) {
				System.out.println(e.getMessage());
				return null;
			} finally {
				if (con != null) {
					con.close();
				}
			}
		return userList;
	}


//todo:ユーザの一人(以上)を参照する
	//ここ参考にitemのも作る
	/**
	 * ユーザーIDからユーザー情報を取得する
	 *
	 * @param useId
	 *            ユーザーID
	 * @return udbList 引数から受け取った値に対応するデータを格納する
	 * @throws SQLException
	 *             呼び出し元にcatchさせるためスロー
	 */
	public static UserDataBeans getUserDataBeansByUserId(int userId) throws SQLException {
		UserDataBeans udb = new UserDataBeans();
		Connection con = null;
		PreparedStatement st = null;
		try {
			con = DBManager.getConnection();
			st = con.prepareStatement("SELECT id,name, login_id, address FROM t_user WHERE id =" + userId);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				udb.setId(rs.getInt("id"));
				udb.setName(rs.getString("name"));
				udb.setLoginId(rs.getString("login_id"));
				udb.setAddress(rs.getString("address"));
			}

			st.close();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new SQLException(e);
		} finally {
			if (con != null) {
				con.close();
			}
		}

		System.out.println("searching UserDataBeans by userId has been completed");
		return udb;
	}

	//ユーザ削除用ーーー
	public static void UserDeleteByUserId(int userId) throws SQLException{

		Connection con = null;
		PreparedStatement st = null;
    	 try {
    		con = DBManager.getConnection();//データベースに繋ぐ
    		st = con.prepareStatement("DELETE FROM t_user WHERE id = ?");

    		st.setInt(1, userId);
			st.executeQuery();
			st.close();
   	    } catch (SQLException e) {
   	      e.printStackTrace();
			} finally {
				if (con !=null) {
					try {
					con.close();
					} catch (SQLException e) {
						e.printStackTrace();

					}
				}
			}
    	 	return;
	}
}
