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
	/**
	 * 商品IDによる商品検索
	 * @param itemId
	 * @return ItemDataBeans
	 * @throws SQLException
	 */
	public static ItemDataBeans getItemByItemID(int itemId) throws SQLException {
		Connection con = null;
		PreparedStatement st = null;
		try {
			con = DBManager.getConnection();

			st = con.prepareStatement("SELECT * FROM  m_item WHERE id = ?");
			st.setInt(1, itemId);

			ResultSet rs = st.executeQuery();
			ItemDataBeans item = new ItemDataBeans();
			if (rs.next()) {
				item.setId(rs.getInt("id"));
				item.setName(rs.getString("name"));
				item.setDetail(rs.getString("detail"));
				item.setPrice(rs.getInt("price"));
				item.setFileName(rs.getString("file_name"));
			}
			System.out.println("searching item by itemID has been completed");

			return item;
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
	 * 商品検索
	 * @param searchWord
	 * @param pageNum
	 * @param pageMaxItemCount
	 * @return
	 * @throws SQLException
	 */
	public static ArrayList<ItemDataBeans> getItemsByItemName (String searchWord, int pageNum, int pageMaxItemCount) throws SQLException{
		Connection con = null;
		PreparedStatement st = null;

		try {
			int startItemNum = (pageNum -1) * pageMaxItemCount;
			int PageItemEndNum = pageMaxItemCount + startItemNum;
			con = DBManager.getConnection();

			if (searchWord.length() == 0) {
				st = con.prepareStatement("SELECT * FROM m_item ORDER BY id ASC LIMIT ?,? ");
				st.setInt(1, startItemNum);
				st.setInt(2, PageItemEndNum);
			}else {
				st = con.prepareStatement("SELECT * FROM m_item WHERE name LIKE ? ORDER BY id ASC LIMIT ?,?");
				st.setString(1, "%" + searchWord + "%");
				st.setInt(2, startItemNum);
				st.setInt(3, PageItemEndNum);
			}

			ResultSet rs = st.executeQuery();
			ArrayList<ItemDataBeans>itemList = new ArrayList<ItemDataBeans>();

			while(rs.next()) {
				ItemDataBeans item = new ItemDataBeans();
				item.setId(rs.getInt("id"));
				item.setName(rs.getString("name"));
				item.setDetail(rs.getString("detail"));
				item.setPrice(rs.getInt("price"));
				item.setFileName(rs.getString("file_name"));
				itemList.add(item);
			}
			System.out.println("get Items by itemName has been completed");
			return itemList;

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
	 * 商品総数を取得
	 *
	 * @param searchWord
	 * @return
	 * @throws SQLException
	 */
	public static int getItemCount(String searchWord) throws SQLException{
		Connection con = null;
		PreparedStatement st = null;
		try {
			con = DBManager.getConnection();
			st = con.prepareStatement("select count(*) as cnt from m_item where name like ?");
			st.setString(1, "%" + searchWord + "%");
			ResultSet rs = st.executeQuery();
			int ItemCount = 0;
			while (rs.next()) {
				ItemCount = Integer.parseInt(rs.getString("cnt"));
			}
			return ItemCount;
		}catch (Exception e) {
			System.out.println(e.getMessage());
			throw new SQLException(e);
		} finally {
			if (con != null) {
				con.close();
			}
		}
	}

	//todo:itemdelete機能// insertと似た感じだと思われる。



	//itemupdateの機能 //下記参考にして直す。//未確認
	public static void updateItem(ItemDataBeans idb) throws SQLException {
		// 更新された情報をセットされたJavaBeansのリスト
		ItemDataBeans updatedIdb = new ItemDataBeans();
		Connection con = null;
		PreparedStatement st = null;

		try {
			System.out.println("inserting item has been completed");

			con = DBManager.getConnection();
			st = con.prepareStatement("UPDATE m_item SET name=?, detail=?, price=? file_name=? WHERE id=?;");
			st.setString(1, idb.getName());
			st.setString(2, idb.getDetail());
			st.setInt(3, idb.getPrice());
			st.setString(4, idb.getFileName());
			st.executeUpdate();
			System.out.println("update has been completed");

			st = con.prepareStatement("SELECT name, detail, price, file_name FROM m_item WHERE id=" + idb.getId());
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				updatedIdb.setName(rs.getString("name"));
				updatedIdb.setDetail(rs.getString("detail"));
				updatedIdb.setPrice(rs.getInt("price"));
				updatedIdb.setFileName(rs.getString("file_name"));
			}

			st.close();
			System.out.println("searching updated-UserDataBeans has been completed");

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new SQLException(e);
		} finally {
			if (con != null) {
				con.close();
			}
		}
	}

	//itemのinsert機能//未確認
	public static void insertItem(ItemDataBeans idb) throws SQLException {
		Connection con = null;
		PreparedStatement st = null;

		try {
			con = DBManager.getConnection();
			st = con.prepareStatement(
					"INSERT INTO m_item(name,detail,price,file_name) VALUES (?,?,?,?)");
			st.setString(1, idb.getName());
			st.setString(2, idb.getDetail());
			st.setInt(3, idb.getPrice());
			st.setString(4, idb.getFileName());
			st.executeUpdate();
			System.out.println("inserting item has been completed");
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
