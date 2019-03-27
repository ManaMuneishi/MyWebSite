package ec;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.BuyDataBeans;
import beans.BuyDetailDataBeans;
import beans.ItemDataBeans;
import dao.BuyDAO;
import dao.BuyDetailDAO;

@WebServlet("/BuyResult")
public class BuyResult extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		try {
			ArrayList<ItemDataBeans> cart = (ArrayList<ItemDataBeans>) EcHelper.cutSessionAttribute(session, "cart");
			BuyDataBeans bdb = (BuyDataBeans) EcHelper.cutSessionAttribute(session, "bdb");

			int buyId = BuyDAO.insertBuy(bdb);
			for (ItemDataBeans cartInItem : cart) {
				BuyDetailDataBeans bddb = new BuyDetailDataBeans();
				bddb.setBuyId(buyId);
				bddb.setItemId(cartInItem.getId());
				BuyDetailDAO.insertBuyDetail(bddb);
			}

			//購入完了ページset
			BuyDataBeans bdbs = BuyDAO.getbuyDetailByBuyId(buyId);
			request.setAttribute("bdbs", bdbs);
			//購入アイテム情報をsetする
			ArrayList<ItemDataBeans> idbs = BuyDetailDAO.getItemDataBeansListByBuyId(buyId);
			request.setAttribute("idbs", idbs);
			//フォワード
			request.getRequestDispatcher(EcHelper.BUY_RESULT_PAGE).forward(request, response);

	}catch (Exception e) {
		e.printStackTrace();
		session.setAttribute("errorMessage", e.toString());
		response.sendRedirect("Error");
		}
	}
}
