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

@WebServlet("/MasterItemDetail")
public class MasterItemDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		HttpSession session = request.getSession();
		try {
			int id = Integer.parseInt(request.getParameter("item_id"));

			ItemDataBeans item = ItemDAO.getItemByItemID(id);
			request.setAttribute("item", item);
			request.getRequestDispatcher(EcHelper.MASTER_ITEM_DETAIL_PAGE).forward(request, response);

		}catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("errorMessage", e.toString());
			response.sendRedirect("Error");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		try {
			 int id = Integer.parseInt(request.getParameter("item_id"));
			 String buttons = request.getParameter("action_button");

			 switch (buttons) {
			case "delete":
				session.setAttribute("item_id", id);//次ページ、ここ用のdaoがいる
				request.getRequestDispatcher(EcHelper.MASTER_ITEM_DELETE_CONF_PAGE).forward(request, response);
				break;

			case "regist"://ここもdaoいる
				request.getRequestDispatcher(EcHelper.MASTER_ITEM_UPDATE_PAGE).forward(request, response);
				break;
			}
			 }catch (Exception e) {
					e.printStackTrace();
					session.setAttribute("errorMessage", e.toString());
					response.sendRedirect("Error");

			 }
	}
}
