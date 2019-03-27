package ec;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.DeliveryMethodDataBeans;
import beans.ItemDataBeans;
import dao.DeliveryMethodDAO;

@WebServlet("/Buy")
public class Buy extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();

		try {

			Boolean isLogin = session.getAttribute("isLogin")!= null ? (Boolean) session.getAttribute("isLogin") : false;

			//これをifで
//			Boolean isLogin = (Boolean)session.getAttribute("isLogin"); //objectなのでキャスト
//			if (isLogin != null) {
//				session.getAttribute("isLogin");
//			}else {
//				isLogin = false;
//			}
			ArrayList<ItemDataBeans> cart = (ArrayList<ItemDataBeans>)session.getAttribute("cart");//リストでキャスト(リストとbeans両方のimportいる)
			if (!isLogin) {
				session.setAttribute("returnStrUrl", "Buy");
				response.sendRedirect("Login");//buyのservletをくっつけてリダイレクト

			}else if (cart.size () == 0) { //カート(リスト)はsizeだよー
				request.setAttribute("cartActionMessage", "購入する商品がありません");
				request.getRequestDispatcher(EcHelper.CART_PAGE).forward(request, response);
			}else {
				// 配送方法をDBから取得
				ArrayList<DeliveryMethodDataBeans> dMDBList = DeliveryMethodDAO.getAllDeliveryMethodDataBeans();
				request.setAttribute("dmdbList", dMDBList);
				request.getRequestDispatcher(EcHelper.BUY_PAGE).forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("errorMessage", e.toString());
			response.sendRedirect("Error");
		}
	}
}
