package ec;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.ItemDataBeans;
import dao.ItemDAO;

@WebServlet("/Item")
public class Item extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		try {
			int id = Integer.parseInt(request.getParameter("item_id"));

			int pageNum = Integer.parseInt(request.getParameter("page_num")== null?"1":request.getParameter("page_num"));

			ItemDataBeans item = ItemDAO.getItemByItemID(id);
			request.setAttribute("item", item);
			request.setAttribute("pageNum", pageNum);
			request.getRequestDispatcher(EcHelper.ITEM_PAGE).forward(request, response);

		}catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("errorMessage", e.toString());
			response.sendRedirect("Error");

		}
	}
}