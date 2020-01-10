package user.vo.content;

public class BuyGoodsInformVO {
	private String gCode, oPhone, oAddr, oDelmsg, oPerson, mId;
	private int oQuantity, oBuypay, zSeq;

	public BuyGoodsInformVO(String gCode, String oPhone, String oAddr, String oDelmsg, String oPerson, String mId,
			int oQuantity, int oBuypay, int zSeq) {
		super();
		this.gCode = gCode;
		this.oPhone = oPhone;
		this.oAddr = oAddr;
		this.oDelmsg = oDelmsg;
		this.oPerson = oPerson;
		this.mId = mId;
		this.oQuantity = oQuantity;
		this.oBuypay = oBuypay;
		this.zSeq = zSeq;
	}

	public String getgCode() {
		return gCode;
	}

	public String getoPhone() {
		return oPhone;
	}

	public String getoAddr() {
		return oAddr;
	}

	public String getoDelmsg() {
		return oDelmsg;
	}

	public String getoPerson() {
		return oPerson;
	}

	public String getmId() {
		return mId;
	}

	public int getoQuantity() {
		return oQuantity;
	}

	public int getoBuypay() {
		return oBuypay;
	}

	public int getzSeq() {
		return zSeq;
	}

	@Override
	public String toString() {
		return "BuyGoodsInformVO [gCode=" + gCode + ", oPhone=" + oPhone + ", oAddr=" + oAddr + ", oDelmsg=" + oDelmsg
				+ ", oPerson=" + oPerson + ", mId=" + mId + ", oQuantity=" + oQuantity + ", oBuypay=" + oBuypay
				+ ", zSeq=" + zSeq + "]";
	}

}
