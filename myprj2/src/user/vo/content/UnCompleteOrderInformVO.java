package user.vo.content;

public class UnCompleteOrderInformVO {
	private String gImg, mName, oDate;

	public UnCompleteOrderInformVO(String gImg, String mName, String oDate) {
		super();
		this.gImg = gImg;
		this.mName = mName;
		this.oDate = oDate;
	}

	public String getgImg() {
		return gImg;
	}

	public String getmName() {
		return mName;
	}

	public String getoDate() {
		return oDate;
	}

	@Override
	public String toString() {
		return "UnCompleteOrderInformVO [gImg=" + gImg + ", mName=" + mName + ", oDate=" + oDate + "]";
	}

}
