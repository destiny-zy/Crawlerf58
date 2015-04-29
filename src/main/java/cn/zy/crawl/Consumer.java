package cn.zy.crawl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;

import org.slf4j.Logger;

import cn.zy.entity.ZJW_house;
import cn.zy.entity.ZJW_landlord;
import cn.zy.entity.ZJW_user;
import cn.zy.repository.ZJW_houseDao;
import cn.zy.repository.ZJW_landlordDao;
import cn.zy.repository.ZJW_userDao;
import cn.zy.util.Patterns;

/*
 * 消费链接,解析
 */
public class Consumer implements Runnable {
	private static Logger log = org.slf4j.LoggerFactory
			.getLogger(Consumer.class);
	private volatile boolean isRunning = true;
	private BlockingQueue<String> cqueue;
	private ConcurrentHashMap<String, Integer> history;
	private static AtomicInteger cunSum = new AtomicInteger();
	private CountDownLatch count;
	ZJW_userDao userDao;
	ZJW_landlordDao landlordDao;
	ZJW_houseDao houseDao;

	public Consumer(BlockingQueue<String> cqueue,
			ConcurrentHashMap<String, Integer> history, CountDownLatch count,
			ZJW_userDao userDao, ZJW_landlordDao landlordDao,
			ZJW_houseDao houseDao) {
		this.cqueue = cqueue;
		this.history = history;
		this.count = count;
		this.userDao = userDao;
		this.landlordDao = landlordDao;
		this.houseDao = houseDao;
	}

	public Consumer() {

	}

	public void run() {

		try {
			while (isRunning) {
				String data = cqueue.poll(1, TimeUnit.SECONDS);
				if (null != data) {
					Long lANDLORD_ID = null;
					String tITLE = null;
					Integer sTATE = 1;
					String pROVINCE = "天津";
					String cITY = "天津";
					String dISTRICT = null;
					String bUSINESS_ZONE = null;
					String sTREET = null;
					String aDDRESS = null;
					Integer rENT_STYLE = 1;
					Integer rENT = null;
					Integer rENT_PAYMENT = null; // 1 押一付三 2 押一付一
					Integer bEDROOM_NUM = null;
					Integer lIVING_ROOM_NUM = null;
					Double rOOM_SIZE = null;
					Integer hOUSE_FLOOR = null;
					Integer tOTAL_FLOOR = null;
					Integer dECORATION_DEGREE = null;
					Integer iS_AUTH = 0;
					Integer pIC_NUM = 0;
					String dESCRIPTION = null;
					Date cREATE_TIME = null;

					String response = CrawlTool.requestApi(data);
					if (response.equals(""))
						continue;

					// user and landlord
					ZJW_user user = null;
					ZJW_landlord landlord = null;
					String p = "</a>(.+?)<em.+个人";
					Matcher m = CrawlTool.getMatcher(response, p);
					String name = null;
					while (m.find()) {
						name = m.group(1);
						if (name == null) {
							break;
						}
						String last_name = name.substring(0, 1);
						String first_name = name.substring(1);
						Integer gender = 1;
						if (first_name.equals("女士") || first_name.equals("姐")) {
							gender = 0;
						}
						user = new ZJW_user(last_name + cunSum.get(), null,
								new Date(), gender, 1);
						userDao.save(user);

						Long id = userDao.findByLastname(
								last_name + cunSum.get()).getId();
						if (id != null) {
							landlord = new ZJW_landlord(id, 0, 0, new Date());
							landlordDao.save(landlord);
						}

						lANDLORD_ID = id;
					}
					// 没有名字直接下一个
					if (name == null)
						continue;
					// house
					m = CrawlTool.getMatcher(response, Patterns._58allAttr);
					while (m.find()) {
						DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
						tITLE = m.group(1).trim();
						cREATE_TIME = df.parse(m.group(2).trim());
						if (!m.group(3).trim().equals("面议"))
							rENT = Integer.parseInt(m.group(3).trim());
						else
							rENT = 0;
					}
					p = "概况[\\d\\D]+?(\\d+)室[\\d\\D]+?(\\d+)厅[\\d\\D]+?(\\d+)卫[\\d\\D]+?([\\d.]+)";
					m = CrawlTool.getMatcher(response, p);
					while (m.find()) {
						bEDROOM_NUM = Integer.parseInt(m.group(1));
						lIVING_ROOM_NUM = Integer.parseInt(m.group(2));
						if (!m.group(4).equals(".58."))
							rOOM_SIZE = Double.parseDouble(m.group(4));
					}

					p = "简单装修[\\d\\D]+?区域";
					m = CrawlTool.getMatcher(response, p);
					while (m.find()) {
						dECORATION_DEGREE = 1;
					}

					p = "中等装修[\\d\\D]+?区域";
					m = CrawlTool.getMatcher(response, p);
					while (m.find()) {
						dECORATION_DEGREE = 2;
					}

					p = "精装修[\\d\\D]+?区域";
					m = CrawlTool.getMatcher(response, p);
					while (m.find()) {
						dECORATION_DEGREE = 3;
					}

					p = "豪华装修[\\d\\D]+?区域";
					m = CrawlTool.getMatcher(response, p);
					while (m.find()) {
						dECORATION_DEGREE = 4;
					}

					p = "(\\d+)层/(\\d+)层";
					m = CrawlTool.getMatcher(response, p);
					while (m.find()) {
						hOUSE_FLOOR = Integer.parseInt(m.group(1));
						tOTAL_FLOOR = Integer.parseInt(m.group(2));
					}

					p = "区域[\\d\\D]+?\">(.+)</a>.+>(.+)</a>.+\">(.+)</a>";
					m = CrawlTool.getMatcher(response, p);
					while (m.find()) {
						dISTRICT = m.group(1).trim();
						sTREET = m.group(2).trim();
						bUSINESS_ZONE = m.group(3).trim();
					}
					if (sTREET == null) {
						p = "区域[\\d\\D]+?\">(.+)</a>.+>(.+)</a>.+-(.+)";
						m = CrawlTool.getMatcher(response, p);
						while (m.find()) {
							dISTRICT = m.group(1).trim();
							sTREET = m.group(2).trim();
							bUSINESS_ZONE = m.group(3).trim();
						}
					}
					if (dISTRICT == null) {
						p = "区域[\\d\\D]+?\">(.+)</a>.+-(.+)";
						m = CrawlTool.getMatcher(response, p);
						while (m.find()) {
							dISTRICT = m.group(1).trim();
							bUSINESS_ZONE = m.group(2).trim();
						}
					}

					p = "su_con w445\">(.+)";
					m = CrawlTool.getMatcher(response, p);
					while (m.find()) {
						aDDRESS = m.group(1);
					}

					p = "description_con[\\d\\D]+?<p>(.+)</p>";
					m = CrawlTool.getMatcher(response, p);
					while (m.find()) {
						dESCRIPTION = m.group(1);
					}

					p = "descriptionImg[\\d\\D]+";
					m = CrawlTool.getMatcher(response, p);
					int count = 0;
					while (m.find()) {
						String res = m.group(0).trim();
						p = "big.+jpg";
						m = CrawlTool.getMatcher(res, p);
						while (m.find()) {
							count++;
						}
						pIC_NUM = count;
					}

					p = "押一付三";
					m = CrawlTool.getMatcher(response, p);
					while (m.find()) {
						rENT_PAYMENT = 1;
					}

					p = "押一付一";
					m = CrawlTool.getMatcher(response, p);
					while (m.find()) {
						rENT_PAYMENT = 2;
					}

					ZJW_house house = new ZJW_house(lANDLORD_ID, tITLE, sTATE,
							pROVINCE, cITY, dISTRICT, bUSINESS_ZONE, sTREET,
							aDDRESS, rENT_STYLE, rENT, rENT_PAYMENT,
							bEDROOM_NUM, lIVING_ROOM_NUM, rOOM_SIZE,
							hOUSE_FLOOR, tOTAL_FLOOR, dECORATION_DEGREE,
							iS_AUTH, pIC_NUM, dESCRIPTION, cREATE_TIME);
					log.info(data + ":" + tITLE);
					houseDao.save(house);
					log.info("存了：" + cunSum.incrementAndGet() + ":" + data
							+ ":" + tITLE);
					log.info("当前页数" + history.get(data));
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
