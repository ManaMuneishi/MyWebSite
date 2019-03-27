package ec;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDAO;

@WebServlet("/LoginResult")
public class LoginResult extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//throwではない。throws。
		request.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession(); //セッションが取れないんだが←取れた。

		try {
			String loginId = request.getParameter("login_id");
			String password = request.getParameter("password");

			int userId = UserDAO.getUserId(loginId,password);

			if(userId !=0) {
				session.setAttribute("isLogin", true);//セッションがあればisloginですよ
				session.setAttribute("userId", userId);

				//ログイン前のページを取得
				String returnStrUrl = (String) EcHelper.cutSessionAttribute(session, "returnStrUrl");

				//ログイン前ページにリダイレクト。(戻るボタン的な。)nullならindexへ。
				response.sendRedirect(returnStrUrl!= null?returnStrUrl:"Index");
			} else {
				session.setAttribute("loginId", loginId);
				session.setAttribute("loginErrorMessage", "入力内容ちがうよー");
				response.sendRedirect("Login");
			}
		} catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("errorMessage", e.toString());
			response.sendRedirect("Error");
		}
	}
}