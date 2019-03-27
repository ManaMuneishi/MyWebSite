package ec;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.ItemDataBeans;
import dao.ItemDAO;

@WebServlet("/ItemSearchResult")
public class ItemSearchResult extends HttpServlet {
	private static final long serialVersionUID = 1L;

	final static int PAGE_MAX_ITEM_COUNT = 8;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		try {
			String searchWord = request.getParameter("search_word");

			//未指定の場合=nullなら1ページ目を取得する。
			int pageNum = Integer.parseInt(request.getParameter("page_num") == null ? "1" : request.getParameter("page_num"));

			//新たに検索されたキーワードを格納
			session.setAttribute("searchWord", searchWord);

			//商品リストを取得(1ページ分)
			ArrayList<ItemDataBeans> searchResultItemList =  ItemDAO.getItemsByItemName(searchWord, pageNum, PAGE_MAX_ITEM_COUNT);

			//検索ワードに対しての総ページ数
			int itemCount = ItemDAO.getItemCount(searchWord);
			int pageMax = (int) Math.ceil(itemCount / PAGE_MAX_ITEM_COUNT);

			//総アイテム数
			request.setAttribute("itemCount", itemCount);//setattribute内でキャストもできるよ。
			//総ページ数
			request.setAttribute("pageMax", pageMax);
			request.setAttribute("itemList", searchResultItemList);

			request.getRequestDispatcher(EcHelper.ITEM_SEARCH_RESULT_PAGE).forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("errorMessage", e.toString());
			response.sendRedirect("Error");
		}
	}
}
