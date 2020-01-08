package user.vo.content;

public class MyPageDetailVO {
	private String id, pass, newPass, addr, birth, phoneFront, phoneBehind, email;

	public MyPageDetailVO(String id, String pass, String newPass, String addr, String birth, String phoneFront,
			String phoneBehind, String email) {
		super();
		this.id = id;
		this.pass = pass;
		this.newPass = newPass;
		this.addr = addr;
		this.birth = birth;
		this.phoneFront = phoneFront;
		this.phoneBehind = phoneBehind;
		this.email = email;
	}

	public MyPageDetailVO() {
	}

	public String getId() {
		return id;
	}

	public String getPass() {
		return pass;
	}

	public String getNewPass() {
		return newPass;
	}

	public String getAddr() {
		return addr;
	}

	public String getBirth() {
		return birth;
	}

	public String getPhoneFront() {
		return phoneFront;
	}

	public String getPhoneBehind() {
		return phoneBehind;
	}

	public String getEmail() {
		return email;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public void setNewPass(String newPass) {
		this.newPass = newPass;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}

	public void setPhoneFront(String phoneFront) {
		this.phoneFront = phoneFront;
	}

	public void setPhoneBehind(String phoneBehind) {
		this.phoneBehind = phoneBehind;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
}//class
