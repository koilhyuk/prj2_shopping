package admin.vo;

public class SelectListVO {
	private String jtfData;
	private int index;
	public SelectListVO(String jtfData, int index) {
		super();
		this.jtfData = jtfData;
		this.index = index;
	}
	public String getJtfData() {
		return jtfData;
	}
	public int getIndex() {
		return index;
	}
	

}
