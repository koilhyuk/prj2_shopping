package admin.vo;

public class SearchAddrVO {
	String zipcode, addr;

	
	
	public SearchAddrVO(String zipcode, String addr) {
		super();
		this.zipcode = zipcode;
		this.addr = addr;
	}

	public String getZipcode() {
		return zipcode;
	}

	public String getAddr() {
		return addr;
	}
	
	
	

}
