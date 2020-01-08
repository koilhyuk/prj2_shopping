package admin.vo;

public class SelectFiveCheckVO {
	private String brandCheck, clothesType, detailType, searchData, selectCombo;
	private int goods_num;

	public SelectFiveCheckVO(String brandCheck, String clothesType, String detailType, String searchData,
			String selectCombo, int goods_num) {
		super();
		this.brandCheck = brandCheck;
		this.clothesType = clothesType;
		this.detailType = detailType;
		this.searchData = searchData;
		this.selectCombo = selectCombo;
		this.goods_num = goods_num;
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

	public int getGoods_num() {
		return goods_num;
	}

}
