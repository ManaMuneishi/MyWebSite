package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import base.DBManager;
import beans.ItemDataBeans;

public class ItemDAO {

	/**
	 * ItemDataBeans をランダムで取得
	 * @param limit ほしい数
	 * @return <ItemDataBeans>
	 * @throws	SQLException
	 */
	public static ArrayList<ItemDataBeans> getRandItem(int limit) throws SQLException {
		Connection con = null; //初期化
		PreparedStatement st = null; //なんとなく初期化。下のとくっつけたらダメなのか。

		try {
			con = DBManager.getConnection();//dbと繋げる

			st = con.prepareStatement("SELECT * FROM m_item ORDER BY RAND() LIMIT ? ");
			st.setInt(1, limit);

			ResultSet rs = st.executeQuery();//実行

			ArrayList<ItemDataBeans>itemList = new ArrayList<ItemDataBeans>();//箱

			while (rs.next()) {//箱の中身
				ItemDataBeans item = new ItemDataBeans();
				item.setId(rs.getInt("id"));//かっこの中はカラムの名前
				item.setName(rs.getString("name"));
				item.setDetail(rs.getString("detail"));
				item.setPrice(rs.getInt("price"));
				item.setFileName(rs.getString("file_name"));
				itemList.add(item);
			}
			System.out.println("item取れたー");
			return itemList;
		}catch(SQLException e) {
			System.out.println(e.getMessage());
			throw new SQLException(e);
		}finally {
			if (con != null) {
				 con.close();
			}
		}
	}
}
