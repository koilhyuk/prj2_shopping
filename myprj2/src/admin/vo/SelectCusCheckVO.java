package admin.vo;

public class SelectCusCheckVO {
	private int comboIndex;
	private String jftData;

	public SelectCusCheckVO(int comboIndex, String jftData) {
		super();
		this.comboIndex = comboIndex;
		this.jftData = jftData;
	}

	public int getComboIndex() {
		return comboIndex;
	}

	public String getJftData() {
		return jftData;
	}

}
