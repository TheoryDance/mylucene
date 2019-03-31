package com.theorydance.mylucene;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.junit.Test;

public class AnalyzerDemo {

	@Test
	public void analyzerChina() throws Exception{
		Analyzer analyzer = new SmartChineseAnalyzer();
		String str = "笑傲江湖，我欲封天，是天蚕土豆写的一篇小说，我觉得很不错！";
		TokenStream tokenStream = analyzer.tokenStream("content", str);
		CharTermAttribute attribute = tokenStream.addAttribute(CharTermAttribute.class);
		tokenStream.reset();
		while (tokenStream.incrementToken()) {
			System.out.println(new String(attribute.toString()));
		}
		analyzer.close();
	}
	
	@Test
	public void analyzerChar() throws Exception{
		Analyzer analyzer = new StandardAnalyzer();
		String str = "hello, this is my test content'sh tesst.sh!";
		TokenStream tokenStream = analyzer.tokenStream("content", str);
		CharTermAttribute attribute = tokenStream.addAttribute(CharTermAttribute.class);
		tokenStream.reset();
		while (tokenStream.incrementToken()) {
			System.out.println(new String(attribute.toString()));
		}
		analyzer.close();
	}
	
	@Test
	public void analyzerChar2() throws Exception{
		List<String> list = new ArrayList<>();
		list.add("hello, there has bikes and apples");
		list.add("http://www.grand-tech.com.cn/wx_web/test.jpg");
		list.add("http://www.grand-tech.com.cn/wx_web/123456.jpg");
		list.add("D:/svn/huawei/productPlatform/grand_base_platform/src/com/ycjk/mapper/api/GkzMapper.java");
		for (String str : list) {
			Analyzer analyzer = new StandardAnalyzer();
			TokenStream tokenStream = analyzer.tokenStream("content", str);
			CharTermAttribute attribute = tokenStream.addAttribute(CharTermAttribute.class);
			tokenStream.reset();
			while (tokenStream.incrementToken()) {
				System.out.print("<"+new String(attribute.toString())+">-");
			}
			analyzer.close();
			System.out.println("\n-----------------------------");
		}
	}
	
	@Test
	public void stringTokenizerTest(){
		// 说明Tokenizer与StringTokenizer没有关系，不是父子
		String str = "hello, there has bikes and apples";
		StringTokenizer tokenizer = new StringTokenizer(str);
		System.out.println(tokenizer.countTokens());
		while(tokenizer.hasMoreTokens()){
			System.out.println(tokenizer.nextToken());
		}
	}
	
}
