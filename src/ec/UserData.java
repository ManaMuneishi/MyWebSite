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
import beans.UserDataBeans;
import dao.BuyDAO;


/**
 * ユーザー情報画面
 *
 * @author d-yamaguchi
 *
 */
@WebServlet("/UserData")
public class UserData extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		try {
			int userId = (int)session.getAttribute("userId");
			UserDataBeans udb = (UserDataBeans) session.getAttribute("returnUDB");
			if(udb == null) {
				BuyDAO.getBuyDataBeansListByUserId(userId);
			}else {
				EcHelper.cutSessionAttribute(session, "returnUDB");
			}//三項でかくと一行

			ArrayList<BuyDataBeans> UserBuyDetailList = BuyDAO.getBuyDataBeansListByUserId(userId);
			request.setAttribute("UserBuyDetailList", UserBuyDetailList);

			BuyDataBeans Data = new BuyDataBeans();//消すかどうかあとで考える。←消してみる

			request.setAttribute("udb", udb);//大事なやつ。

		}catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("errorMessage", e.toString());
			response.sendRedirect("Error");
			return;
		}
		request.setCharacterEncoding("UTF-8");
		request.getRequestDispatcher(EcHelper.USER_DATA_PAGE).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
}
