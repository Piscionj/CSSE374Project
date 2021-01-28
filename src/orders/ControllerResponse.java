package orders;

public class ControllerResponse {
	private long status;
	private String errDesc;
	private long errCode;
	private long orderID;
	
	public ControllerResponse(long orderID, long status, String errDesc, long errCode) {
		this.orderID = orderID;
		this.status = status;
		this.errDesc = errDesc;
		this.errCode = errCode;
	}
	
	public long getStatus() {
		return status;
	}
	public String getErrDesc() {
		return errDesc;
	}
	public long getErrCode() {
		return errCode;
	}
	public long getOrderID() {
		return orderID;
	}
	
}
