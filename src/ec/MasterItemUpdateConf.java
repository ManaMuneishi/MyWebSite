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

@WebServlet("/MasterItemUpdateConf")
public class MasterItemUpdateConf extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();

		try {
			String inputItemId = request.getParameter("item_id");
			String inputItemName = request.getParameter("item_name"); //jspのフォーム取り込む
			String inputItemDetail= request.getParameter("item_detail");
			String inputItemPrice = request.getParameter("item_price");
			String inputItemFile = request.getParameter("item_file");

			ItemDataBeans idb = new ItemDataBeans();
			idb.setId(Integer.parseInt(inputItemId));
			idb.setName(inputItemName);
			idb.setDetail(inputItemDetail);
			idb.setPrice(Integer.parseInt(inputItemPrice));
			idb.setFileName(inputItemFile);

			String confirmed = request.getParameter("confirm_button");

			switch (confirmed) {
			case "cancel":
				response.sendRedirect("MasterItemUpdate?itemId="+inputItemId);
				break;

			case "regist":
				ItemDAO.updateItem(idb);
				request.getRequestDispatcher(EcHelper.MASTER_ALL_RESULT_PAGE).forward(request, response);
				break;//ここ忘れてた
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect("Error");
		}
	}
}

