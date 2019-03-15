package ec;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpSession;

public class EcHelper {


	//TOPページ
	static final String TOP_PAGE = "/WEB-INF/jsp/index.jsp";
	//検索結果
	static final String ITEM_SEARCH_RESULT_PAGE = "/WEB-INF/jsp/item.jsp";
	//商品ページ
	static final String ITEM_PAGE = "/WEB-INF/jsp/item.jsp";
	//エラーページ
	static final String ERROR_PAGE = "/WEB-INF/jsp/error.jsp";
	//カートページ
	static final String CART_PAGE = "/WEB-INF/jsp/cart.jsp";
	//購入
	static final String BUY_PAGE = "/WEB-INF/jsp/buy.jsp";
	//購入確認
	static final String BUY_CONFIRM_PAGE = "/WEB-INF/jsp/buyconfirm.jsp";
	//購入完了
	static final String BUY_RESULT_PAGE = "/WEB-INF/jsp/buyresult.jsp";
	//ユーザ情報
	static final String USER_DATA_PAGE = "/WEB-INF/jsp/userdata.jsp";
	//ユーザ情報更新確認
	static final String USER_DATA_UPDATE_CONFIRM_PAGE = "/WEB-INF/jsp/userdataupdateconfirm.jsp";
	//ユーザ情報更新完了
	static final String USER_DATA_UPDATE_RESULT_PAGE = "/WEB-INF/jsp/userdataupdateresult.jsp";
	//ユーザ購入履歴
	static final String USER_BUY_HISTORY_DETAIL_PAGE = "/WEB-INF/jsp/userbuyhistorydetail.jsp";
	//ログアウト
	static final String LOGOUT_PAGE = "/WEB-INF/jsp/logout.jsp";
	//ログイン
	static final String LOGIN_PAGE = "/WEB-INF/jsp/login.jsp";
	//新規登録
	static final String REGIST_PAGE = "/WEB-INF/jsp/regist.jsp";
	//新規登録入力内容確認
	static final String REGIST_CONFIRM_PAGE = "/WEB-INF/jsp/registconfirm.jsp";
	//新規登録完了
	static final String REGIST_RESULT_PAGE = "/WEB-INF/jsp/registresult.jsp";

	public static EcHelper getInstance() {
		return new EcHelper();
	}

	/**
	 * ハッシュ関数 ここはコピペした。
	 *
	 * @param target
	 * @return
	 */
	public static String getSha256(String target) {
		MessageDigest md = null; //何かを初期化
		StringBuffer buf = new StringBuffer();//stringの親戚らしい
		try {
			md = MessageDigest.getInstance("SHA-256");//この名前の暗号があるらしい
			md.update(target.getBytes());
			byte[] digest = md.digest();

			for (int i = 0; i < digest.length; i++) {
				buf.append(String.format("%02x", digest[i]));
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return buf.toString();
	}

		//ほんとはobject.だがstring で書いてみよう ←かけない。というか面倒い。
		//getattributeはobjectクラスにいるのでわざわざキャストする必要がある。わかりにくくなるのでやめよう。
	public static Object cutSessionAttribute(HttpSession session, String str) {
		Object test = session.getAttribute(str);
		session.removeAttribute(str);

		return test;
	}
	public static boolean isLoginIdValidation(String inputLoginId) {
		if (inputLoginId.matches("[0-9a-zA-Z-_]")) { //0から9、aからz、AからZ、あと_が入力されているか否かの判定。
			return true;
		}
		//elseをつけなくても動くらしい ←メソッド内で一旦returnを返してるので、次のreturnへ飛ぶことはない。のでtrueで止まるからelseはいらない。
			return false;
		}
}
