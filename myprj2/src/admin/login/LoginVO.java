package admin.login;

public class LoginVO {
	private String id, pw;

	public LoginVO(String id, String pw) {
		super();
		this.id = id;
		this.pw = pw;
	}

	public String getId() {
		return id;
	}

	public String getPw() {
		return pw;
	}

}// class
