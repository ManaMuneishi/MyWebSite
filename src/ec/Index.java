package ec;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.ItemDataBeans;
import dao.ItemDAO;

@WebServlet("/Index")
public class Index extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//セッション
		HttpSession session = request.getSession();
		try {

			//商品情報を取得
			ArrayList<ItemDataBeans> itemList = ItemDAO.getRandItem(4);//トライキャッチ書かないとここで怒られる//ここあとで変えようー数。
			//スコープにセット
			request.setAttribute("itemList",itemList);
			//フォワード
			request.getRequestDispatcher(EcHelper.TOP_PAGE).forward(request, response);

		} catch (SQLException e) {
				e.printStackTrace();

		}
	}
}
