package beans;

import java.text.SimpleDateFormat;
import java.util.Date;

public class UserDataBeans {

	private int id;
	private String name;
	private String address;
	private String loginId;
	private String loginPassword;
	private Date createDate;

	public UserDataBeans() {

	}

	public UserDataBeans(int id, String name, String address, String loginId, String loginPassword, Date createDate) {
		this.id = id;
		this.name = name;
		this.address = address;
		this.loginId = loginId;
		this.loginPassword = loginPassword;
		this.createDate = createDate;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	public String getLoginPassword() {
		return loginPassword;
	}
	public void setLoginPassword(String loginPassword) {
		this.loginPassword = loginPassword;
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
}
