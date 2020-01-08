package user.vo.content;

public class SelectDetailChkVO {
	private String clothesType, brandCheck;

	public SelectDetailChkVO(String clothesType, String brandCheck) {
		super();
		this.clothesType = clothesType;
		this.brandCheck = brandCheck;
	}

	public String getClothesType() {
		return clothesType;
	}

	public String getBrandCheck() {
		return brandCheck;
	}

}
