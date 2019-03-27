package beans;

	import java.text.SimpleDateFormat;
import java.util.Date;

	// 追加のBEANS　

	public class BuyHistoryBeans {

	private int userId;
	private int totalPrice;
	private int deliveryMethodId;
	private Date createDate;
	private int buyId;
	private int itemId;
	private String name;
	private String detail;
	private int price;
	private String fileName;

	public BuyHistoryBeans() {
	}

	public BuyHistoryBeans(int userId, int totalPrice, int deliveryMethodId, Date createDate, int buyId,
			int itemId, String name, String detail, int price, String fileName) {
		super();
		this.userId = userId;
		this.totalPrice = totalPrice;
		this.deliveryMethodId = deliveryMethodId;
		this.createDate = createDate;
		this.buyId = buyId;
		this.itemId = itemId;
		this.name = name;
		this.detail = detail;
		this.price = price;
		this.fileName = fileName;
	}

	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}
	public int getDeliveryMethodId() {
		return deliveryMethodId;
	}
	public void setDeliveryMethodId(int deliveryMethodId) {
		this.deliveryMethodId = deliveryMethodId;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getFormatDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日HH時mm分");
				return sdf.format(createDate);
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
			public String getName() {
				return name;
			}
			public void setName(String name) {
				this.name = name;
			}
			public String getDetail() {
				return detail;
			}
			public void setDetail(String detail) {
				this.detail = detail;
			}
			public int getPrice() {
				return price;
			}
			public void setPrice(int price) {
				this.price = price;
			}
			public String getFileName() {
				return fileName;
			}
			public void setFileName(String fileName) {
				this.fileName = fileName;
			}
	}
