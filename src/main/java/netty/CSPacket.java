package netty;

import com.alibaba.fastjson.JSONObject;

public class CSPacket {

	private boolean syn;//是否为同步消息
	private String cmd;
	private long requestId;
	private BaseMsg msg;

	public CSPacket() {
	}

	public CSPacket(String cmd, BaseMsg msg) {
		this.cmd = cmd;
		this.msg = msg;
	}

	public boolean isSyn() {
		return syn;
	}

	public void setSyn(boolean syn) {
		this.syn = syn;
	}

	public String getCmd() {
		return cmd;
	}

	public void setCmd(String cmd) {
		this.cmd = cmd;
	}

	public long getRequestId() {
		return requestId;
	}

	public void setRequestId(long requestId) {
		this.requestId = requestId;
	}

	public BaseMsg getMsg() {
		return msg;
	}

	public void setMsg(BaseMsg msg) {
		this.msg = msg;
	}

	@Override
	public String toString() {
		return JSONObject.toJSONString(this);
	}
}
