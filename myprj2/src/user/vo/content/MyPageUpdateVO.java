package user.vo.content;

public class MyPageUpdateVO {
	private String id, pass, newpass, addr, birth ,phone, email, cipherText;

	public MyPageUpdateVO(String id, String pass, String newpass, String addr, String birth, String phone, String email,
			String cipherText) {
		super();
		this.id = id;
		this.pass = pass;
		this.newpass = newpass;
		this.addr = addr;
		this.birth = birth;
		this.phone = phone;
		this.email = email;
		this.cipherText = cipherText;
	}

	public String getId() {
		return id;
	}

	public String getPass() {
		return pass;
	}

	public String getNewpass() {
		return newpass;
	}

	public String getAddr() {
		return addr;
	}

	public String getBirth() {
		return birth;
	}

	public String getPhone() {
		return phone;
	}

	public String getEmail() {
		return email;
	}

	public String getCipherText() {
		return cipherText;
	}

	


	}//class
