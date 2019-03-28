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

/**
 * Servlet implementation class ItemAdd
 */
@WebServlet("/ItemAdd")
public class ItemAdd extends HttpServlet {
	private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//postだった！！！！！！！！！！！！！

    	HttpSession session = request.getSession();
		try {
			int id = Integer.parseInt(request.getParameter("item_id"));

			ItemDataBeans item = ItemDAO.getItemByItemID(id);

			request.setAttribute("item", item);

			ArrayList<ItemDataBeans> cart = (ArrayList<ItemDataBeans>)session.getAttribute("cart");

			if (cart == null) {
				cart = new ArrayList<ItemDataBeans>();
			}
			cart.add(item);
			session.setAttribute("cart", cart);
			request.setAttribute("cartActionMessage", "商品を追加しました");
			request.getRequestDispatcher(EcHelper.CART_PAGE).forward(request, response);
		}catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("errorMessage", e.toString());
			response.sendRedirect("Error");
		}
	}
}
