package cn.zy.crawl;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.zy.repository.ZJW_houseDao;
import cn.zy.repository.ZJW_landlordDao;
import cn.zy.repository.ZJW_userDao;
import cn.zy.util.Patterns;

@Component
public class CrawlHouse {
	public static final int cThreadNum = 1;
	public static final int pThreadNum = 1;

	ConcurrentHashMap<String, Integer> history = new ConcurrentHashMap<String, Integer>();
	BlockingQueue<String> cqueue = new LinkedBlockingDeque<String>();
	AtomicInteger pageCount = new AtomicInteger(2);

	@Autowired
	ZJW_userDao userDao;
	@Autowired
	ZJW_landlordDao landlordDao;
	@Autowired
	ZJW_houseDao houseDao;

	public CrawlHouse() {

	}

	public void getHouse() {
		String url = "http://tj.58.com/zufang/0/pn1";
		String response = CrawlTool.requestApi(url);
		Matcher m1 = CrawlTool.getMatcher(response, Patterns._58lianjie1);
		Matcher m2 = CrawlTool.getMatcher(response, Patterns._58lianjie2);
		while (m1.find()) {
			String res = m1.group(1);
			history.put(res, 1);
			cqueue.add(res);
		}
		while (m2.find()) {
			String res = m2.group(1);
			history.put(res, 1);
			cqueue.add(res);
		}
		startCrawl(cqueue);

	}

	public void startCrawl(BlockingQueue<String> pqueue) {
		try {
			long start = System.currentTimeMillis();
			ExecutorService service = Executors.newCachedThreadPool();
			CountDownLatch count4Consumer = new CountDownLatch(cThreadNum);
			for (int i = 0; i < pThreadNum; i++) {
				service.execute(new Producer(cqueue, pageCount, history));
			}
			Thread.sleep(200);
			for (int i = 0; i < cThreadNum; i++) {
				service.execute(new Consumer(cqueue, history, count4Consumer,
						userDao, landlordDao, houseDao));
			}

			count4Consumer.await();
			service.shutdown();
			long end = System.currentTimeMillis();
			System.out.println("运行时间：" + (end - start) / 1000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
