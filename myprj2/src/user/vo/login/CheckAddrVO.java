package user.vo.login;

public class CheckAddrVO {

	String sido, gugun, inputAddr;
	
	

	public CheckAddrVO(String sido, String gugun, String inputAddr) {
		super();
		this.sido = sido;
		this.gugun = gugun;
		this.inputAddr = inputAddr;
	}

	public String getSido() {
		return sido;
	}

	public String getGugun() {
		return gugun;
	}

	public String getInputAddr() {
		return inputAddr;
	}

}
