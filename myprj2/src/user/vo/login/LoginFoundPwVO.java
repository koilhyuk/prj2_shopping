package user.vo.login;

public class LoginFoundPwVO {
	private String name, id, phone;
	

	public LoginFoundPwVO(String name, String id, String phone) {
		super();
		this.name = name;
		this.id = id;
		this.phone = phone;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "LoginFoundPwVO [name=" + name + ", id=" + id + ", phone=" + phone + "]";
	}

	
}//class
