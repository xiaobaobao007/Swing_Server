package netty;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class SyncPacketFuture<T> implements Future<T> {

	private CountDownLatch latch = new CountDownLatch(1);

	// 响应结果
	private T resp;

	// Future被创建的时间
	private long beginTime = System.currentTimeMillis();

	@Override
	public boolean cancel(boolean mayInterruptIfRunning) {
		return false;
	}

	@Override
	public boolean isCancelled() {
		return false;
	}

	@Override
	public boolean isDone() {
		return resp != null;
	}

	@Override
	public T get() throws InterruptedException {
		latch.await();
		return resp;
	}

	@Override
	public T get(long timeout, TimeUnit unit) throws InterruptedException {
		if (latch.await(timeout, unit)) {
			return resp;
		}
		return null;
	}

	public void setResp(T resp) {
		this.resp = resp;
		latch.countDown();
	}

	public long getBeginTime() {
		return beginTime;
	}
}
