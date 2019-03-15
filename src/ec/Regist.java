package ec;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.UserDataBeans;


@WebServlet("/Regist")
public class Regist extends HttpServlet {
	private static final long serialVersionUID = 1L;



	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();//セッションとった
		request.setCharacterEncoding("UTF-8");//日本語にした

		//下下記これと同じ意味。
		//UserDataBeans udb = session.getAttribute("udb") != null?(UserDataBeans) EcHelper.cutSessionAttribute(session, "udb"):new UserDataBeans();

		UserDataBeans udb = (UserDataBeans)session.getAttribute("udb");//ここさえあれば動く。ifなくても平気。

		if (udb != null) {
			EcHelper.cutSessionAttribute(session, "udb");
		}

		request.setAttribute("udb",udb);
		request.getRequestDispatcher(EcHelper.REGIST_PAGE).forward(request, response);

	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
