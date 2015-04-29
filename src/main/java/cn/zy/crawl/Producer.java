package cn.zy.crawl;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.zy.util.Patterns;

public class Producer implements Runnable {
	private volatile boolean isRunning = true;
	private ConcurrentHashMap<String, Integer> history;
	private BlockingQueue<String> cqueue;
	private AtomicInteger chanSum = new AtomicInteger();
	private AtomicInteger pageCount;

	private static Logger log = LoggerFactory.getLogger(Producer.class);

	public Producer(BlockingQueue<String> cqueue, AtomicInteger pageCount,
			ConcurrentHashMap<String, Integer> history) {
		this.cqueue = cqueue;
		this.pageCount = pageCount;
		this.history = history;
	}

	public void run() {

		while (pageCount.get() < 35) {
			String url = "http://tj.58.com/zufang/0/pn"
					+ pageCount.getAndIncrement();
			String response = CrawlTool.requestApi(url);
			Matcher m1 = CrawlTool.getMatcher(response, Patterns._58lianjie1);
			Matcher m2 = CrawlTool.getMatcher(response, Patterns._58lianjie2);
			while (m1.find()) {
				String data = m1.group(1);
				if (!history.containsKey(data)) {
					history.put(data, pageCount.get() - 1);
					cqueue.add(data);
					log.info("生产了：" + chanSum.incrementAndGet());
				}
			}
			while (m2.find()) {
				String data = m2.group(1);
				if (!history.containsKey(data)) {
					history.put(data, pageCount.get() - 1);
					cqueue.add(data);
					log.info("生产了：" + chanSum.incrementAndGet());
				}
			}

		}
		log.info("生产完毕");

	}
}
