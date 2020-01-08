package user.vo.login;

public class LoginVO {
	private String id,pass;

	public LoginVO(String id, String pass) {
		super();
		this.id = id;
		this.pass = pass;
	}

	public String getId() {
		return id;
	}

	public String getPass() {
		return pass;
	}
	
}
