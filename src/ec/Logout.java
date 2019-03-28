package ec;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/Logout")
public class Logout extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		session.setAttribute("isLogin", false);//ここなかったら無限に半分ログアウト状態続いた。(したというけどしてくれてない)
		session.removeAttribute("userId");//ここすぐ書けなかった。

		request.getRequestDispatcher(EcHelper.LOGOUT_PAGE).forward(request, response);
	}
}
