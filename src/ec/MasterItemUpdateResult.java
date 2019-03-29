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

@WebServlet("/MasterAllResult")
public class MasterItemUpdateResult extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.getRequestDispatcher(EcHelper.MASTER_ALL_RESULT_PAGE).forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher(EcHelper.MASTER_ALL_RESULT_PAGE).forward(request, response);

		//ここに飛ばしたよー
		HttpSession session = request.getSession(); //セッションとる
		try {
			String inputItemName = request.getParameter("item_name"); //jspのフォーム取り込む
			String inputItemDetail= request.getParameter("item_detail");
			String inputItemPrice = request.getParameter("item_price");
			String inputItemFile = request.getParameter("item_file");

			ItemDataBeans idb = new ItemDataBeans();
			idb.setName(inputItemName);
			idb.setDetail(inputItemDetail);
			idb.setPrice(Integer.parseInt(inputItemPrice));
			idb.setFileName(inputItemFile);

			//getしてなかった
			String confirm_button = request.getParameter("confirm_button");

			switch (confirm_button) {
			case "cancel":
				session.setAttribute("idb", idb);//も一回表示してあげるように
				response.sendRedirect("MasterItemUpdate");
				break;

			case "regist":
				ItemDAO.insertItem(idb);
				//OKだけのページに飛ばす
				request.getRequestDispatcher(EcHelper.MASTER_ALL_RESULT_PAGE).forward(request, response);
				break;//ここ忘れてた
			}

		} catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("errorMessage", e.toString());
			response.sendRedirect("Error");
		}
	}
}
