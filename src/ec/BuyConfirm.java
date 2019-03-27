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
import beans.DeliveryMethodDataBeans;
import beans.ItemDataBeans;
import dao.DeliveryMethodDAO;

@WebServlet("/BuyConfirm")
public class BuyConfirm extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		try {
			int inputDeliveryMethodId = Integer.parseInt(request.getParameter("delivery_method_id")); //stringでくるから
			//配送idで配送料を取る。
			DeliveryMethodDataBeans userSelectDMB = DeliveryMethodDAO.getDeliveryMethodDataBeansByBuyId(inputDeliveryMethodId);

			ArrayList<ItemDataBeans> cartIDBList = (ArrayList<ItemDataBeans>) session.getAttribute("cart");

			//トータル

			int cartInItemTotalPrice = EcHelper.getTotalItemPrice(cartIDBList);
			int deliveryPrice = userSelectDMB.getPrice();
			int totalPrice = cartInItemTotalPrice + deliveryPrice; //これが支払額

			String deliveryName = userSelectDMB.getName();

			//使うかどうかは購入を押すまで分からないけどsetする
			BuyDataBeans bdb = new BuyDataBeans();
			bdb.setUserId((int) session.getAttribute("userId"));
			bdb.setDeliveryMethodPrice(deliveryPrice);
			bdb.setDeliveryMethodName(deliveryName);
			bdb.setTotalPrice(totalPrice);
			bdb.setDelivertMethodId(userSelectDMB.getId());//直接書いていい。

			session.setAttribute("bdb", bdb);
			request.getRequestDispatcher(EcHelper.BUY_CONFIRM_PAGE).forward(request, response);

		}catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("errorMessage", e.toString());
			response.sendRedirect("Error");
			return;//いる
		}
	}
}
