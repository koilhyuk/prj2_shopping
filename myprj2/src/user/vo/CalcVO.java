package user.vo;

public class CalcVO {
	private String id, ip, code, name, pass, birth, gender, phone, addr, email, joindate;
	private int totalmoney;
	public CalcVO(String id, String ip, String code, String name, String pass, String birth, String gender,
			String phone, String addr, String email, String joindate, int totalmoney) {
		super();
		this.id = id;
		this.ip = ip;
		this.code = code;
		this.name = name;
		this.pass = pass;
		this.birth = birth;
		this.gender = gender;
		this.phone = phone;
		this.addr = addr;
		this.email = email;
		this.joindate = joindate;
		this.totalmoney = totalmoney;
	}
	public String getId() {
		return id;
	}
	public String getIp() {
		return ip;
	}
	public String getCode() {
		return code;
	}
	public String getName() {
		return name;
	}
	public String getPass() {
		return pass;
	}
	public String getBirth() {
		return birth;
	}
	public String getGender() {
		return gender;
	}
	public String getPhone() {
		return phone;
	}
	public String getAddr() {
		return addr;
	}
	public String getEmail() {
		return email;
	}
	public String getJoindate() {
		return joindate;
	}
	public int getTotalmoney() {
		return totalmoney;
	}
	
	

	
	
}
