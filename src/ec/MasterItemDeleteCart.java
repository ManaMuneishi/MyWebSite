package ec;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.ItemDataBeans;
import dao.ItemDAO;

@WebServlet("/MasterItemDeleteCart")//ここもう間違えない。

public class MasterItemDeleteCart extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	//ここに飛ばしたー

		HttpSession session = request.getSession();
		try {
			int id = Integer.parseInt(request.getParameter("item_id"));

			ItemDataBeans item = ItemDAO.getItemByItemID(id);

			request.setAttribute("item", item);

			//下記一行絶対違う。←直しました
			//アドミンは買い物しない＝カートいらない＝このカートをdelete用に使おう!!
			ArrayList<ItemDataBeans> delete_cart = (ArrayList<ItemDataBeans>)session.getAttribute("cart");

			if (delete_cart == null) {
				delete_cart = new ArrayList<ItemDataBeans>();
			}
			delete_cart.add(item);
			session.setAttribute("deleteCart", delete_cart);
			request.setAttribute("cartActionMessage", "削除する商品を追加しました");
			request.getRequestDispatcher(EcHelper.MASTER_ITEM_DELETE_CONF_PAGE).forward(request, response);
		}catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("errorMessage", e.toString());
			response.sendRedirect("Error");
		}
	}
}
