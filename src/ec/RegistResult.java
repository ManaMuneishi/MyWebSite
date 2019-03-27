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

/**
 * Servlet implementation class RegistResult
 */
@WebServlet("/RegistResult")
public class RegistResult extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public RegistResult() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//これで登録しましたよの画面を作る、
		//そのために、その前の処理(comfirmから受け取ったpostの処理)をする。=これでいいですかにOKしたかどうか。

		HttpSession session = request.getSession(); //セッションとる
		try {

			String inputUserName = request.getParameter("user_name");
			String inputUserAddress = request.getParameter("user_address");
			String inputLoginId = request.getParameter("login_id");
			String inputPassword = request.getParameter("password");

			UserDataBeans udb = new UserDataBeans();
			udb.setName(inputUserName);
			udb.setAddress(inputUserAddress);
			udb.setLoginId(inputLoginId);
			udb.setLoginPassword(inputPassword); //ここまでcomfirmと同じ。表示するのは同じもの。udbでもらってきたもの

			switch ("confirm_button") {//択一ボタンの作り方。

			case "cancel":
				session.setAttribute("udb", udb);
				response.sendRedirect("Regist");
				break;

			case "regist":
				UserDAO.insertUser(udb);
				request.setAttribute("udb", udb);//とりあえず一回だけ返せばいいからrequestで。←違う!!←いや合ってる。
				request.getRequestDispatcher(EcHelper.REGIST_RESULT_PAGE).forward(request, response);

			}

		} catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("errorMessage", e.toString());
			response.sendRedirect("Error");
		}
	}
}
