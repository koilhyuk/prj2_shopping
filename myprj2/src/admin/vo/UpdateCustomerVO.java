package admin.vo;

public class UpdateCustomerVO {
	private String m_code, m_name, m_birth, m_gender,m_phone, m_detail_addr, m_img, m_email, z_zipcode,z_addr;

	public UpdateCustomerVO(String m_code, String m_name, String m_birth, String m_gender, String m_phone,
			String m_detail_addr, String m_img, String m_email, String z_zipcode, String z_addr) {
		super();
		this.m_code = m_code;
		this.m_name = m_name;
		this.m_birth = m_birth;
		this.m_gender = m_gender;
		this.m_phone = m_phone;
		this.m_detail_addr = m_detail_addr;
		this.m_img = m_img;
		this.m_email = m_email;
		this.z_zipcode = z_zipcode;
		this.z_addr = z_addr;
	}

	public String getM_code() {
		return m_code;
	}

	public String getM_name() {
		return m_name;
	}

	public String getM_birth() {
		return m_birth;
	}

	public String getM_gender() {
		return m_gender;
	}

	public String getM_phone() {
		return m_phone;
	}

	public String getM_detail_addr() {
		return m_detail_addr;
	}

	public String getM_img() {
		return m_img;
	}

	public String getM_email() {
		return m_email;
	}

	public String getZ_zipcode() {
		return z_zipcode;
	}

	public String getZ_addr() {
		return z_addr;
	}

	@Override
	public String toString() {
		return "UpdateCustomerVO [m_code=" + m_code + ", m_name=" + m_name + ", m_birth=" + m_birth + ", m_gender="
				+ m_gender + ", m_phone=" + m_phone + ", m_detail_addr=" + m_detail_addr + ", m_img=" + m_img
				+ ", m_email=" + m_email + ", z_zipcode=" + z_zipcode + ", z_addr=" + z_addr + "]";
	}



}
