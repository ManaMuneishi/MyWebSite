package ec;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.UserDataBeans;
import dao.UserDAO;

@WebServlet("/ResistConfirm")
public class ResistConfirm extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();

		try {
			String inputUserName = request.getParameter("user_name"); //jspのフォーム取り込む
			String inputAddress = request.getParameter("user_address");
			String inputLoginId = request.getParameter("login_id");
			String inputPassword = request.getParameter("password");
			String inputConfirmPassword = request.getParameter("confirm_password");

			UserDataBeans udb = new UserDataBeans();
			udb.setName(inputUserName);
			udb.setAddress(inputAddress);
			udb.setLoginId(inputLoginId);
			udb.setLoginPassword(inputPassword);

			String validationMessage = "";//初期化

			//パスが合ってるか確認
			if (!inputPassword.equals(inputConfirmPassword)) {
				validationMessage += "パスワードが違います";
			}
			//idのチェック
			if (!EcHelper.isLoginIdValidation(udb.getLoginId())) {
				validationMessage += "半角英数とハイフン、アンダースコアのみ入力可";
			}
			//idの重複チェック
			if (UserDAO.isOverlapLoginId(udb.getLoginId())) {
				validationMessage += "他のユーザが使用中のログインIDです";
			}

			//上三つがもしなければ = メッセージの長さが0であればconfirmへ
			if (validationMessage.length() == 0) {
				request.setAttribute("udb", udb);
				request.getRequestDispatcher(EcHelper.REGIST_CONFIRM_PAGE).forward(request, response);
			} else {
				session.setAttribute("udb", udb);
				session.setAttribute("validationMessage", validationMessage);
				response.sendRedirect("Regist");
			}

		} catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("errorMessage", e.toString());
			response.sendRedirect("Error");
		}
	}
}
