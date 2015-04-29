package cn.zy.crawl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;

public class CrawlTool {
	private static Logger log = org.slf4j.LoggerFactory
			.getLogger(CrawlTool.class);

	public static String requestApi(String url) {
		String res = "";

		CloseableHttpClient client = HttpClients.createDefault();
		HttpGet get = new HttpGet(url);
		get.addHeader(
				"User-Agent",
				"Mozilla/5.0 (compatible; Baiduspider/2.0; +http://www.baidu.com/search/spider.html)");
		try {
			HttpResponse response = client.execute(get);
			HttpEntity entity = response.getEntity();
			if (response.getStatusLine().getStatusCode() == 200) {
				res = EntityUtils.toString(entity);
			} else {
				int code = response.getStatusLine().getStatusCode();
				log.info("出错：" + code + url);
				// log.info(EntityUtils.toString(entity));
				if (code != 202)
					System.exit(0);
			}

			client.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}

	public static Matcher getMatcher(String response, String pattern) {
		Pattern p = Pattern.compile(pattern);
		Matcher matcher = p.matcher(response);
		return matcher;
	}

}
