package beans;

import java.io.Serializable;

//購入詳細
public class BuyDetailDataBeans  implements Serializable {//ユーザが既に買ったデータ
	private int id;
	private int buyId;
	private int itemId;

	public int getId() {
		return id;
	}
	public void setId(int buyDetailId) {
		this.id = buyDetailId;
	}
	public int getBuyId() {
		return buyId;
	}
	public void setBuyId(int buyId) {
		this.buyId = buyId;
	}
	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
}
