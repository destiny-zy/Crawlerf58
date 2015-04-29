package cn.zy.crawl;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import cn.zy.util.Patterns;

@ActiveProfiles("production")
@ContextConfiguration(locations = { "/applicationContext.xml" })
public class CrawlHouseOfganji extends
		AbstractTransactionalJUnit4SpringContextTests {
	private static Logger log = LoggerFactory
			.getLogger(CrawlHouseOfganji.class);
	public static final int cThreadNum = 1;
	public static final int pThreadNum = 1;

	ConcurrentHashMap<String, Object> history = new ConcurrentHashMap<String, Object>();
	BlockingQueue<String> cqueue = new LinkedBlockingDeque<String>();
	AtomicInteger pageCount = new AtomicInteger(2);

	@Test
	public void getHouse() {
		String url = "http://tj.ganji.com/fang1/a1m1o1/";
		String response = CrawlTool.requestApi(url);
		Matcher matcher = CrawlTool
				.getMatcher(response, Patterns._ganjilianjie);

		while (matcher.find()) {
			String res = Patterns.index + matcher.group(1);
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
				service.execute(new ProducerOfganji(cqueue, pageCount, history));
			}
			Thread.sleep(500);
			for (int i = 0; i < cThreadNum; i++) {
				service.execute(new ConsumerOfganji(cqueue, history,
						count4Consumer));
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
