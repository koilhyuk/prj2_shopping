package admin.vo;

public class updateStopVO {
	private String m_stopflag, m_reason,m_code;
	

	public updateStopVO(String m_stopflag, String m_reason, String m_code) {
		super();
		this.m_stopflag = m_stopflag;
		this.m_reason = m_reason;
		this.m_code = m_code;
	}

	public String getM_stopflag() {
		return m_stopflag;
	}

	public String getM_reason() {
		return m_reason;
	}

	public String getM_code() {
		return m_code;
	}

	@Override
	public String toString() {
		return "updateStopVO [m_stopflag=" + m_stopflag + ", m_reason=" + m_reason + ", m_code=" + m_code + "]";
	}
	

}
