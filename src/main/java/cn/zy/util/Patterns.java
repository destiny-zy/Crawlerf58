package cn.zy.util;

public class Patterns {
	public static final int depth = 3;

	public final static String index = "http://tj.ganji.com";

	public final static String _ganjilianjie = "\"img-box\".+?(/fang1/.+?htm)";
	public final static String _58lianjie1 = "(http://jing.58.com/adJump.+?)\".+class";
	public final static String _58lianjie2 = "(http://tj.58.com/zufang.+shtml).+class";
	public final static String _58allAttr = "bigtitle\">.+?>(.+?)<[\\d\\D]+?time\">([\\d\\D]+?)</li>[\\d\\D]+?bigpri arial\">(.+?)</";
	public final static String _ganjiallAttr = "title-name\">(.+)</h1>";
}
