package admin.vo;

public class SelectAllCusVO {
	private String code, id, name, phone, joinData, stopflag;
	private int useCash;
	public SelectAllCusVO(String code, String id, String name, String phone, String joinData, String stopflag,
			int useCash) {
		super();
		this.code = code;
		this.id = id;
		this.name = name;
		this.phone = phone;
		this.joinData = joinData;
		this.stopflag = stopflag;
		this.useCash = useCash;
	}
	public String getCode() {
		return code;
	}
	public String getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getPhone() {
		return phone;
	}
	public String getJoinData() {
		return joinData;
	}
	public String getStopflag() {
		return stopflag;
	}
	public int getUseCash() {
		return useCash;
	}

}// class
