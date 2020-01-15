package admin.vo;

public class SelectClickGoodsDetailDTO {
	private int gPrice, gInventory, gScore, gSaleNum, gLikeNum;
	private String gCode, gName, gStrong, gImg, bName, dName, cName;

	public int getgPrice() {
		return gPrice;
	}

	public void setgPrice(int gPrice) {
		this.gPrice = gPrice;
	}

	public int getgInventory() {
		return gInventory;
	}

	public void setgInventory(int gInventory) {
		this.gInventory = gInventory;
	}

	public int getgScore() {
		return gScore;
	}

	public void setgScore(int gScore) {
		this.gScore = gScore;
	}

	public int getgSaleNum() {
		return gSaleNum;
	}

	public void setgSaleNum(int gSaleNum) {
		this.gSaleNum = gSaleNum;
	}

	public String getgCode() {
		return gCode;
	}

	public void setgCode(String gCode) {
		this.gCode = gCode;
	}

	public String getgName() {
		return gName;
	}

	public void setgName(String gName) {
		this.gName = gName;
	}

	public String getgStrong() {
		return gStrong;
	}

	public void setgStrong(String gStrong) {
		this.gStrong = gStrong;
	}

	public String getgImg() {
		return gImg;
	}

	public void setgImg(String gImg) {
		this.gImg = gImg;
	}

	public String getbName() {
		return bName;
	}

	public void setbName(String bName) {
		this.bName = bName;
	}

	public String getdName() {
		return dName;
	}

	public void setdName(String dName) {
		this.dName = dName;
	}

	public String getcName() {
		return cName;
	}

	public void setcName(String cName) {
		this.cName = cName;
	}

	public int getgLikeNum() {
		return gLikeNum;
	}

	public void setgLikeNum(int gLikeNum) {
		this.gLikeNum = gLikeNum;
	}

	@Override
	public String toString() {
		return "SelectClickGoodsDetailDTO [gPrice=" + gPrice + ", gInventory=" + gInventory + ", gScore=" + gScore
				+ ", gSaleNum=" + gSaleNum + ", gCode=" + gCode + ", gName=" + gName + ", gStrong=" + gStrong
				+ ", gImg=" + gImg + ", bName=" + bName + ", dName=" + dName + ", cName=" + cName + "]";
	}

}
