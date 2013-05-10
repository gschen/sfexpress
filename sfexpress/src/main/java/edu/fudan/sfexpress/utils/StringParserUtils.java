package edu.fudan.sfexpress.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringEscapeUtils;

public class StringParserUtils {
	/**
	 * regex list: <br/>
	 * (?:s\\d{1,}.key=\")(.*?)(?:\";) <br/>
	 * (?:s\\d{1,}.value=\")(.*?)(?:\";) <br/>
	 * (?:s\\d{1,}.blearDeliverTm=\")(.*?)(?:\";)<br/>
	 * (?:s\\d{1,}.productTypeCode=\")(.*?)(?:\";)
	 * 
	 * @param str
	 * @return
	 */
	public static List<String> parseString(String str, String regex,
			String prefix) {
		List<String> list = new ArrayList<String>();

		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(str);
		while (m.find()) {
			// System.out.println(m.group(0)); // 整个匹配到的内容
			// System.out.println(StringEscapeUtils.unescapeJava(m.group(1)));
			list.add(prefix + StringEscapeUtils.unescapeJava(m.group(1)));
		}

		return list;
	}

	public static List<String> parseString(String str, String regex) {
		List<String> list = new ArrayList<String>();

		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(str);
		while (m.find()) {
			// System.out.println(m.group(0)); // 整个匹配到的内容
			// System.out.println(StringEscapeUtils.unescapeJava(m.group(1)));
			list.add(StringEscapeUtils.unescapeJava(m.group(1)));
		}

		return list;
	}

}
