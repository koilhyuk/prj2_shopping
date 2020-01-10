package user.vo.content;

public class SelectCusDataVO {

	private String m_id, m_name, m_pass, m_birth, m_phone, m_detail_addr, m_email ,z_addr, z_zipcode;

	
	public SelectCusDataVO() {
	}

	public String getM_id() {
		return m_id;
	}

	public String getM_name() {
		return m_name;
	}

	public String getM_pass() {
		return m_pass;
	}

	public String getM_birth() {
		return m_birth;
	}

	public String getM_phone() {
		return m_phone;
	}

	public String getM_detail_addr() {
		return m_detail_addr;
	}

	public String getM_email() {
		return m_email;
	}

	public String getZ_addr() {
		return z_addr;
	}

	public String getZ_zipcode() {
		return z_zipcode;
	}

	public void setM_id(String m_id) {
		this.m_id = m_id;
	}

	public void setM_name(String m_name) {
		this.m_name = m_name;
	}

	public void setM_pass(String m_pass) {
		this.m_pass = m_pass;
	}

	public void setM_birth(String m_birth) {
		this.m_birth = m_birth;
	}

	public void setM_phone(String m_phone) {
		this.m_phone = m_phone;
	}

	public void setM_detail_addr(String m_detail_addr) {
		this.m_detail_addr = m_detail_addr;
	}

	public void setM_email(String m_email) {
		this.m_email = m_email;
	}

	public void setZ_addr(String z_addr) {
		this.z_addr = z_addr;
	}

	public void setZ_zipcode(String z_zipcode) {
		this.z_zipcode = z_zipcode;
	}

	@Override
	public String toString() {
		return "SelectCusDataVO [m_id=" + m_id + ", m_name=" + m_name + ", m_pass=" + m_pass + ", m_birth=" + m_birth
				+ ", m_phone=" + m_phone + ", m_detail_addr=" + m_detail_addr + ", m_email=" + m_email + ", z_addr="
				+ z_addr + ", z_zipcode=" + z_zipcode + "]";
	}

	
}
