package cn.zy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import cn.zy.crawl.CrawlHouse;

/*
 * 程序入口点
 * 
 * @author zy
 */
@SpringBootApplication
public class Application implements CommandLineRunner {

	@Autowired
	CrawlHouse crawler;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);

	}

	@Override
	public void run(String... arg0) throws Exception {
		crawler.getHouse();

	}
}
