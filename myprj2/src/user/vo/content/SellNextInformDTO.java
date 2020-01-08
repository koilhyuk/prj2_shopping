package user.vo.content;

import java.util.List;

public class SellNextInformDTO {

	private String gName, gCode, mId, mName, mPhone, mDetailAddr;
	private int mSeq, totalMoney, mQuantity, gPrice;
	private List<MemberCardInformVO> cardList;

	public String getgName() {
		return gName;
	}

	public void setgName(String gName) {
		this.gName = gName;
	}

	public String getgCode() {
		return gCode;
	}

	public void setgCode(String gCode) {
		this.gCode = gCode;
	}

	public String getmId() {
		return mId;
	}

	public void setmId(String mId) {
		this.mId = mId;
	}

	public String getmName() {
		return mName;
	}

	public void setmName(String mName) {
		this.mName = mName;
	}

	public String getmPhone() {
		return mPhone;
	}

	public void setmPhone(String mPhone) {
		this.mPhone = mPhone;
	}

	public String getmDetailAddr() {
		return mDetailAddr;
	}

	public void setmDetailAddr(String mDetailAddr) {
		this.mDetailAddr = mDetailAddr;
	}

	public int getmSeq() {
		return mSeq;
	}

	public void setmSeq(int mSeq) {
		this.mSeq = mSeq;
	}

	public int getTotalMoney() {
		return totalMoney;
	}

	public void setTotalMoney(int totalMoney) {
		this.totalMoney = totalMoney;
	}

	public int getmQuantity() {
		return mQuantity;
	}

	public void setmQuantity(int mQuantity) {
		this.mQuantity = mQuantity;
	}

	public int getgPrice() {
		return gPrice;
	}

	public void setgPrice(int gPrice) {
		this.gPrice = gPrice;
	}

	public List<MemberCardInformVO> getCardList() {
		return cardList;
	}

	public void setCardList(List<MemberCardInformVO> cardList) {
		this.cardList = cardList;
	}

	@Override
	public String toString() {
		return "SellNextInformDTO [gName=" + gName + ", gCode=" + gCode + ", mId=" + mId + ", mName=" + mName
				+ ", mPhone=" + mPhone + ", mDetailAddr=" + mDetailAddr + ", mSeq=" + mSeq + ", totalMoney="
				+ totalMoney + ", mQuantity=" + mQuantity + ", gPrice=" + gPrice + ", cardList=" + cardList + "]";
	}

}
