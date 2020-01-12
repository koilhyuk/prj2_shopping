package user.vo.content;

public class UpdateCusDataVO {

	private String m_phone,m_detail_addr,m_email , m_id,z_zipcode, z_addr;

	public UpdateCusDataVO(String m_phone, String m_detail_addr, String m_email, String m_id, String z_zipcode,
			String z_addr) {
		super();
		this.m_phone = m_phone;
		this.m_detail_addr = m_detail_addr;
		this.m_email = m_email;
		this.m_id = m_id;
		this.z_zipcode = z_zipcode;
		this.z_addr = z_addr;
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

	public String getM_id() {
		return m_id;
	}

	public String getZ_zipcode() {
		return z_zipcode;
	}

	public String getZ_addr() {
		return z_addr;
	}

	@Override
	public String toString() {
		return "UpdateCusDataVO [m_phone=" + m_phone + ", m_detail_addr=" + m_detail_addr + ", m_email=" + m_email
				+ ", m_id=" + m_id + ", z_zipcode=" + z_zipcode + ", z_addr=" + z_addr + "]";
	}

	
}
