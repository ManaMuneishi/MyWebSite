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

@WebServlet("/MasterItemSearchResult")
public class MasterItemSearchResult extends HttpServlet {

	private static final long serialVersionUID = 1L; //これなに//シリアライズの仲間的な何か
	final static int PAGE_MAX_ITEM_COUNT = 16;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();

		try {
			String searchWord = request.getParameter("search_word");

			int pageNum = Integer.parseInt(request.getParameter("page_num") == null ? "1" : request.getParameter("page_num"));
			session.setAttribute("searchWord", searchWord);

			ArrayList<ItemDataBeans> searchResultItemList =  ItemDAO.getItemsByItemName(searchWord, pageNum, PAGE_MAX_ITEM_COUNT);

			int itemCount = ItemDAO.getItemCount(searchWord);
			int pageMax = (int) Math.ceil(itemCount / PAGE_MAX_ITEM_COUNT);//これ何

			//総アイテム数
			request.setAttribute("itemCount", itemCount);//setattribute内でキャストもできるよ。
			//総ページ数
			request.setAttribute("pageMax", pageMax);
			request.setAttribute("itemList", searchResultItemList);

			request.getRequestDispatcher(EcHelper.MASTER_ITEM_SEARCH_RESULT_PAGE).forward(request, response);

		}catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("errorMessage", e.toString());
			response.sendRedirect("Error");
		}
	}
}
