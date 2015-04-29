package cn.zy.crawl;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;

import org.slf4j.Logger;

import cn.zy.util.Patterns;

/*
 * 消费链接,解析
 */

public class ConsumerOfganji implements Runnable {
	private static Logger log = org.slf4j.LoggerFactory
			.getLogger(ConsumerOfganji.class);
	private static final Object tmpObject = new Object();
	private volatile boolean isRunning = true;
	private BlockingQueue<String> cqueue;
	private ConcurrentHashMap<String, Object> history;
	private static AtomicInteger cunSum = new AtomicInteger();
	private CountDownLatch count;

	public ConsumerOfganji(BlockingQueue<String> cqueue,
			ConcurrentHashMap<String, Object> history, CountDownLatch count) {
		this.cqueue = cqueue;
		this.history = history;
		this.count = count;
	}

	public void run() {

		try {
			while (isRunning) {
				String data = cqueue.poll(1, TimeUnit.SECONDS);
				if (null != data) {
					history.put(data, tmpObject);

					String response = CrawlTool.requestApi(data);
					Matcher mHouse = CrawlTool.getMatcher(response,
							Patterns._ganjiallAttr);
					while (mHouse.find()) {
						log.info("title:" + mHouse.group(1));
						log.info("找到个数：" + cunSum.incrementAndGet());
					}
					log.info("队列剩余：" + cqueue.size());
				} else {
					isRunning = false;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			Thread.currentThread().interrupt();

		} finally {
			count.countDown();
			log.info("剩余消费者：" + count.getCount());
		}
	}
}
