package user.vo.login;

public class JoinDetailVO {
	private String id, pass,name, birth, gender,  phone, addr, email;
	
	private int z_seq;

	public JoinDetailVO(String id, String pass, String name, String birth, String gender, String phone, String addr,
			String email, int z_seq) {
		super();
		this.id = id;
		this.pass = pass;
		this.name = name;
		this.birth = birth;
		this.gender = gender;
		this.phone = phone;
		this.addr = addr;
		this.email = email;
		this.z_seq = z_seq;
	}

	public String getId() {
		return id;
	}

	public String getPass() {
		return pass;
	}

	public String getName() {
		return name;
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

	public int getZ_seq() {
		return z_seq;
	}

	@Override
	public String toString() {
		return "JoinDetailVO [id=" + id + ", pass=" + pass + ", name=" + name + ", birth=" + birth + ", gender="
				+ gender + ", phone=" + phone + ", addr=" + addr + ", email=" + email + ", z_seq=" + z_seq + "]";
	}
	
	
	

	
}//class
