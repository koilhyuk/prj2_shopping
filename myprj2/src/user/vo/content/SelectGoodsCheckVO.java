package user.vo.content;

public class SelectGoodsCheckVO {
	private String brandCheck, clothesType, detailType, searchData, selectCombo;

	public SelectGoodsCheckVO(String brandCheck, String clothesType, String detailType, String searchData,
			String selectCombo) {
		super();
		this.brandCheck = brandCheck;
		this.clothesType = clothesType;
		this.detailType = detailType;
		this.searchData = searchData;
		this.selectCombo = selectCombo;
	}

	public String getBrandCheck() {
		return brandCheck;
	}

	public String getClothesType() {
		return clothesType;
	}

	public String getDetailType() {
		return detailType;
	}

	public String getSearchData() {
		return searchData;
	}

	public String getSelectCombo() {
		return selectCombo;
	}

	@Override
	public String toString() {
		return "SelectGoodsCheckVO [brandCheck=" + brandCheck + ", clothesType=" + clothesType + ", detailType="
				+ detailType + ", searchData=" + searchData + ", selectCombo=" + selectCombo + "]";
	}

}
