package user.vo;

public class selectZipcodeVO {
	private String zAddr, zZipcode;

	public selectZipcodeVO(String zAddr, String zZipcode) {
		super();
		this.zAddr = zAddr;
		this.zZipcode = zZipcode;
	}

	public String getzAddr() {
		return zAddr;
	}

	public String getzZipcode() {
		return zZipcode;
	}

}
