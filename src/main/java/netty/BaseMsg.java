package netty;

import com.alibaba.fastjson.JSONObject;

public abstract class BaseMsg {

	// 请求结果
	private int result;

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	@Override
	public String toString() {
		return JSONObject.toJSONString(this);
	}
}
