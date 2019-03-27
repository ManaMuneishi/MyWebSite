package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import base.DBManager;
import beans.BuyDetailDataBeans;
import beans.ItemDataBeans;

public class BuyDetailDAO {

	public static void insertBuyDetail(BuyDetailDataBeans bddb) throws SQLException{
		Connection con = null;
		PreparedStatement st = null;
		try {
			con = DBManager.getConnection();
			st = con.prepareStatement("INSERT INTO t_buy_detail (buy_id, item_id) VALUE (?,?)");
			st.setInt(1, bddb.getBuyId());
			st.setInt(2, bddb.getItemId());//途中まで打ってでてくるか確認する
			st.executeUpdate();


		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new SQLException(e);
		} finally {
			if (con != null) {
				con.close();
			}
		}
	}
	public static ArrayList<ItemDataBeans> getItemDataBeansListByBuyId(int buyId) throws SQLException{
		Connection con = null;
		PreparedStatement st = null;
	try {
		con = DBManager.getConnection();

		st = con.prepareStatement("SELECT m_item.id,"
				+ " m_item.name,"
				+ " m_item.price"
				+ " FROM t_buy_detail"
				+ " JOIN m_item"
				+ " ON t_buy_detail.item_id = m_item.id"
				+ " WHERE t_buy_detail.buy_id = ? ");
		st.setInt(1, buyId);
		ResultSet rs = st.executeQuery();
		ArrayList<ItemDataBeans> ItemList = new ArrayList<ItemDataBeans>();

		while (rs.next()) {
			ItemDataBeans idb = new ItemDataBeans();
			idb.setId(rs.getInt("id"));
			idb.setName(rs.getString("name"));
			idb.setPrice(rs.getInt("price"));

			ItemList.add(idb);
		}
		return ItemList; //ここよく忘れる
		}catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new SQLException(e);
		} finally {
			if (con != null) {
				con.close();
			}
		}
	}

}
