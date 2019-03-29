package ec;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.ItemDataBeans;
import dao.ItemDAO;

@WebServlet("/MasterItemDeleteConf")
public class MasterItemDeleteConf extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ItemDataBeans itemDetail = null;
		try {
			// 商品情報をDBより取得
			itemDetail =  ItemDAO.getItemByItemID(Integer.parseInt(request.getParameter("itemId")));
		} catch (NumberFormatException | SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		// 取得した商品情報をリクエストにセット
		request.setAttribute("itemDetail", itemDetail);

		// masteritemdeleteconf.jspにフォワード
		request.getRequestDispatcher(EcHelper.MASTER_ITEM_DELETE_CONF_PAGE).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// itemIdを画面から取得
		String itemId = request.getParameter("itemId");
		try {
			ItemDAO.deleteItem(itemId);
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		// 画面遷移
		request.getRequestDispatcher(EcHelper.MASTER_ALL_RESULT_PAGE).forward(request, response);
	}

}
