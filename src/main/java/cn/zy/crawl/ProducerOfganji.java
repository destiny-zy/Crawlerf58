package cn.zy.crawl;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.zy.util.Patterns;

public class ProducerOfganji implements Runnable {
	private volatile boolean isRunning = true;
	private ConcurrentHashMap<String, Object> history;
	private BlockingQueue<String> cqueue;
	private AtomicInteger chanSum = new AtomicInteger();
	private AtomicInteger pageCount;

	private static Logger log = LoggerFactory.getLogger(ProducerOfganji.class);

	public ProducerOfganji(BlockingQueue<String> cqueue,
			AtomicInteger pageCount, ConcurrentHashMap<String, Object> history) {
		this.cqueue = cqueue;
		this.pageCount = pageCount;
		this.history = history;
	}

	public void run() {

		while (pageCount.intValue() < 35) {
			String url = "http://tj.ganji.com/fang1/a1m1o/"
					+ pageCount.getAndIncrement();
			String response = CrawlTool.requestApi(url);
			Matcher m = CrawlTool.getMatcher(response, Patterns._ganjilianjie);
			while (m.find()) {
				String data = Patterns.index + m.group(1);
				if (!history.containsKey(data) && !cqueue.contains(data)) {
					cqueue.add(data);
					log.info("生产了：" + chanSum.incrementAndGet());
				}
			}
		}
		log.info("生产完毕");

	}
}
