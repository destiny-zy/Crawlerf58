package cn.zy;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.regex.Matcher;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.zy.crawl.CrawlTool;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class TestReg {

	@Test
	public void test() {

		String response = CrawlTool.requestApi("http://tj.58.com/zufang/0/pn1");
		String p = "(http://tj.58.com/zufang.+shtml).+class";
		// System.out.println(response);
		Matcher m = CrawlTool.getMatcher(response, p);
		int count = 0;
		while (m.find()) {
			count++;
			System.out.println(m.group(1));
		}
		System.out.println(count);
	}

	@Test
	public void test1() {

		String response = CrawlTool.requestApi("http://tj.58.com/zufang/0/pn1");
		String p = "(http://jing.58.com/adJump.+?)\".+class";
		// System.out.println(response);
		Matcher m = CrawlTool.getMatcher(response, p);
		int count = 0;
		while (m.find()) {
			count++;
			System.out.println(m.group(1));
		}
		System.out.println(count);
	}

	@Test
	public void ss() {
		String response = CrawlTool
				.requestApi("http://tj.58.com/zufang/21785246006795x.shtml");
		String p = "bigtitle\">.+?>(.+?)<[\\d\\D]+?time\">([\\d\\D]+?)</li>[\\d\\D]+?bigpri arial\">(.+?)</";
		Matcher m = CrawlTool.getMatcher(response, p);
		int count = 0;
		while (m.find()) {
			count++;
			System.out.println(m.group(1).trim());
		}
		System.out.println(count);
	}

	@Test
	public void ss3() {
		String response = CrawlTool
				.requestApi("http://tj.58.com/zufang/21642053610510x.shtml");
		String p = " (\\d)室 ";
		Matcher m = CrawlTool.getMatcher(response, p);
		int count = 0;
		while (m.find()) {
			count++;
			// System.out.println(m.group(1).trim());
		}

		p = "概况[\\d\\D]+?(\\d+)室[\\d\\D]+?(\\d+)厅[\\d\\D]+?(\\d+)卫[\\d\\D]+?([\\d.]+)";
		m = CrawlTool.getMatcher(response, p);
		count = 0;
		while (m.find()) {
			count++;
			// System.out.println(m.group(4).trim());
		}

		p = "简单装修";
		m = CrawlTool.getMatcher(response, p);
		count = 0;
		while (m.find()) {
			count++;
			// System.out.println(m.group().trim());
		}

		p = "(\\d+)层/(\\d+)层";
		m = CrawlTool.getMatcher(response, p);
		count = 0;
		while (m.find()) {
			count++;
			// System.out.println(m.group().trim());
		}

		p = "区域[\\d\\D]+?\">(.+)</a>.+>(.+)</a>.+\">(.+)</a>";
		m = CrawlTool.getMatcher(response, p);
		count = 0;
		while (m.find()) {
			count++;
			// System.out.println(m.group(3).trim());
		}

		p = "区域[\\d\\D]+?\">(.+)</a>.+>(.+)</a>.+-(.+)";
		m = CrawlTool.getMatcher(response, p);
		count = 0;
		while (m.find()) {
			count++;
			// System.out.println(m.group(3).trim());
		}

		p = "区域[\\d\\D]+?\">(.+)</a>.+-(.+)";
		m = CrawlTool.getMatcher(response, p);
		count = 0;
		while (m.find()) {
			count++;
			// System.out.println(m.group(2).trim());
		}

		p = "su_con w445\">(.+)";
		m = CrawlTool.getMatcher(response, p);
		count = 0;
		while (m.find()) {
			count++;
			// System.out.println(m.group(1).trim());
		}

		p = "description_con[\\d\\D]+?<p>(.+)</p>";
		m = CrawlTool.getMatcher(response, p);
		count = 0;
		while (m.find()) {
			count++;
			// System.out.println(m.group(1).trim());
		}

		p = "descriptionImg[\\d\\D]+";
		m = CrawlTool.getMatcher(response, p);
		count = 0;
		while (m.find()) {

			String res = m.group(0).trim();
			p = "big.+jpg";
			m = CrawlTool.getMatcher(res, p);
			while (m.find()) {
				count++;
			}
		}

		p = "</a>(.+?)<em.+个人";
		m = CrawlTool.getMatcher(response, p);
		int cc = 0;
		while (m.find()) {
			cc++;
			System.out.println(m.group(1).trim());
		}
		System.out.println(cc);
		System.out.println(m.group());

		p = "押一付三";
		m = CrawlTool.getMatcher(response, p);
		count = 0;
		while (m.find()) {
			count++;
			// System.out.println(m.group().trim());
		}

	}

	@Test
	public void ss2() {
		String response = CrawlTool
				.requestApi("http://tj.ganji.com/fang1/a1m1o1/");
		String p = "\"img-box\".+?(/fang1/.+?htm)";
		Matcher m = CrawlTool.getMatcher(response, p);
		int count = 0;
		while (m.find()) {
			System.out.println(m.group(1));
			count++;

		}
		System.out.println(count);
	}

	@Test
	public void p() {
		String p = "title-name\">(.+)</h1>";
		String response = CrawlTool.requestApi("http://tj.ganji.com/");
		System.out.println(response);
		Matcher m = CrawlTool.getMatcher(response, p);
		while (m.find()) {
			System.out.println(m.group(1));
		}
	}

	@Test
	public void ceshiwang() {
		String response = CrawlTool.requestApi("http://www.dianping.com");
		System.out.println(response);
	}

	@Test
	public void ce2() {
		int count = 0;
		String response = CrawlTool
				.requestApi("http://www.dianping.com/shop/534096/review_all?pageno=55");
		Matcher m = CrawlTool
				.getMatcher(
						response,
						"user-id=\"(.+)\" [\\d\\D]+?href=\"/member.+>(.+)</a>[\\d\\D]+?urr-rank(.+)\" [\\d\\D]+?irr-star(.+)0\"[\\d\\D]+?口味(.+)<em[\\d\\D]+?环境(.+)<em[\\d\\D]+?服务(.+)<em[\\d\\D]+?J_brief-cont\">([\\d\\D]+?)</div>[\\d\\D]+?time\">(.+)</span>");
		while (m.find()) {
			System.out.println(m.group(2));
			count++;
		}
		System.out.println(count);
	}

	@Test
	public void ccc() {
		try {
			ObjectOutputStream out = new ObjectOutputStream(
					new FileOutputStream("d:\\ss.out"));
			HashMap<String, String> map = new HashMap<String, String>();
			HashMap<String, String> map1 = new HashMap<String, String>();
			map.put("1", "sdfsdfds");
			out.writeObject(map);
			out.close();
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(
					"d:\\ss.out"));
			map1 = (HashMap<String, String>) in.readObject();
			System.out.println(map1.size());
			System.out.println(map1.get("1"));
			in.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	public void cc2() {

	}
}
