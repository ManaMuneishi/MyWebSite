package ec;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//ここの間にあったsuper消した

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//セッション取る時は日本語にする呪文はいらないのか。
		HttpSession session = request.getSession();

		String inputLoginId =session.getAttribute("loginId")!=null?(String) EcHelper.cutSessionAttribute(session,"loginId"):"";
//		String inputLoginId =(String)session.getAttribute("loginId");
//		if (inputLoginId != null) {
//			EcHelper.cutSessionAttribute(session, "loginId");
//		}
		String loginErrorMessage = (String)EcHelper.cutSessionAttribute(session, "loginErrorMessage");

		request.setAttribute("inputLoginId", inputLoginId);
		request.setAttribute("loginErrorMessage", loginErrorMessage);//セットしないとフォワードできないよ。

		request.getRequestDispatcher(EcHelper.LOGIN_PAGE).forward(request, response);//短くていい

//		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/Login.jsp");
//		dispatcher.forward(request, response);
	}
}