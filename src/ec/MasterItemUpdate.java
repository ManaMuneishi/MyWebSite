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

@WebServlet("/MasterItemUpdate")
public class MasterItemUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// アイテムIDをリクエストから取得
		// アイテムIDをリクエストにセット
		String itemId = request.getParameter("itemId");
		ItemDataBeans itemDetail = null;
		try {
			itemDetail = ItemDAO.getItemByItemID(Integer.parseInt(itemId));
		} catch (NumberFormatException | SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		request.setAttribute("itemDetail", itemDetail);
		request.getRequestDispatcher(EcHelper.MASTER_ITEM_UPDATE_PAGE).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		//ここに飛ばした-
		ItemDataBeans itemDetail = new ItemDataBeans();
		itemDetail.setId(Integer.parseInt(request.getParameter("itemId")));
		itemDetail.setName(request.getParameter("item_name"));
		itemDetail.setPrice(Integer.parseInt(request.getParameter("item_price")));
		itemDetail.setDetail(request.getParameter("item_detail"));
		itemDetail.setFileName(request.getParameter("item_file"));

		//詰めてupdateCONFへ送る

		request.setAttribute("itemDetail", itemDetail);
		request.getRequestDispatcher(EcHelper.MASTER_ITEM_UPDATE_CONF_PAGE).forward(request, response);
	}
}
