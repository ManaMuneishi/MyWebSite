package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;

import base.DBManager;
import beans.BuyDataBeans;

public class BuyDAO {

	/**
	 * 購入情報登録処理
	 * @param bdb 購入情報
	 * @throws SQLException 呼び出し元にスローさせるため
	 */
	public static int insertBuy(BuyDataBeans bdb) throws SQLException {//ここ分からん写した。
		Connection con = null;
		PreparedStatement st = null;
		int autoIncKey = -1;

		try {
			con = DBManager.getConnection();
			st = con.prepareStatement(
					"INSERT INTO t_buy(user_id,total_price,delivery_method_id,create_date) VALUES(?,?,?,?)",
					Statement.RETURN_GENERATED_KEYS);//なんだっけ←insertを検索可能にするらしい。
			st.setInt(1, bdb.getUserId());
			st.setInt(2,bdb.getTotalPrice());
			st.setInt(3, bdb.getDelivertMethodId());
			st.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
			st.executeUpdate();

			ResultSet rs = st.getGeneratedKeys();
			if(rs.next()) {
				autoIncKey = rs.getInt(1);
			}
			System.out.println("inserting buy-datas has been completed");
			return autoIncKey;
		}catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new SQLException(e);
		} finally {
			if (con != null) {
				con.close();
			}
		}
	}
		 /**
	     * ユーザIDによる購入歴の取得
	     * @param user_id
	     * @return buy_id
	     * @throws SQLException
	     */
		public static ArrayList<BuyDataBeans>getBuyDataBeansListByUserId(int userId) throws SQLException{
			Connection con = null;
			PreparedStatement st = null;
			try {
				con = DBManager.getConnection();

				st = con.prepareStatement(
						"SELECT A.id, A.user_id, A.total_price, A.delivery_method_id, A.create_date, "
						+ "B.name, B.price "
						+ "FROM t_buy A "
						+ "INNER JOIN m_delivery_method B "
						+ "ON A.delivery_method_id = B.id "
						+ "WHERE A.user_id = ? ORDER BY A.create_date DESC");
				st.setInt(1, userId);
				ResultSet rs = st.executeQuery();
				ArrayList<BuyDataBeans> UserBuyDetailList = new ArrayList<BuyDataBeans>();

				while (rs.next()) {

					BuyDataBeans ubb = new BuyDataBeans();

					ubb.setId(rs.getInt("id"));
					ubb.setUserId(rs.getInt("user_id"));
					ubb.setTotalPrice(rs.getInt("total_price"));
					ubb.setBuyDate(rs.getTimestamp("create_date"));
					ubb.setDelivertMethodId(rs.getInt("delivery_method_id"));
					ubb.setDeliveryMethodName(rs.getString("name"));
					ubb.setDeliveryMethodPrice(rs.getInt("price"));
					UserBuyDetailList.add(ubb);
				}
				return UserBuyDetailList;
		}catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new SQLException(e);
		} finally {
			if (con != null) {
				con.close();
			}
		}
		}

		//購入IDからの購入詳細検索
		public static BuyDataBeans getbuyDetailByBuyId(int buyId) throws SQLException{
			Connection con = null;
			PreparedStatement st = null;
			try {
				con = DBManager.getConnection();

				st = con.prepareStatement(
						"SELECT * FROM t_buy"
						+ " JOIN m_delivery_method"
						+ " ON t_buy.delivery_method_id = m_delivery_method.id"
						+ " WHERE t_buy.id = ?");
				st.setInt(1, buyId);

				ResultSet rs = st.executeQuery();

				BuyDataBeans bdb = new BuyDataBeans ();//ここ外に出さないとreturnできん
				if(rs.next()) {
					bdb.setId(rs.getInt("id"));
					bdb.setUserId(rs.getInt("user_id"));
					bdb.setTotalPrice(rs.getInt("total_price"));
					bdb.setDelivertMethodId(rs.getInt("delivery_method_id"));
					bdb.setBuyDate(rs.getDate("create_date"));
					bdb.setDeliveryMethodName(rs.getString("name"));
					bdb.setDeliveryMethodPrice(rs.getInt("price"));
				}
				System.out.println("searching BuyDataBeans by buyID has been completed");
				return bdb;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new SQLException(e);
		} finally {
			if (con != null) {
				con.close();
			}
		}
	}

}