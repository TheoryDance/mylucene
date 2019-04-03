package com.theorydance.mylucene;

import java.nio.file.Paths;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.junit.Test;

/**
 * 本demo参考地址：https://blog.csdn.net/m0_37913549/article/details/78989078
 * @author TheoryDance
 */
public class Demo1 {

	@Test
	public void test() throws Exception{
		Analyzer analyzer = new StandardAnalyzer();
		// Store the index in memory:
		// Directory directory = new RAMDirectory();
		Directory directory = FSDirectory.open(Paths.get("d:/tmp/index/"));
		IndexWriterConfig conf = new IndexWriterConfig(analyzer);
		IndexWriter writer = new IndexWriter(directory, conf);
		Document doc = new Document();
		String text = "This is the text to be indexed.";
		doc.add(new Field("fieldname", text, TextField.TYPE_STORED));
//		writer.addDocument(doc);
		Term term = new Term("fieldname", "text");
		System.out.println(term.text());
		writer.updateDocument(term, doc);
		writer.close();		

		// new search the index:
		IndexReader reader = DirectoryReader.open(directory);
		IndexSearcher searcher = new IndexSearcher(reader);
		// parse a simple query that searches for "text":
		QueryParser parser = new QueryParser("fieldname", analyzer);
		// QueryParser parser = new MultiFieldQueryParser(new String[]{"fieldname"}, analyzer);
		Query query = parser.parse("index");
		
		ScoreDoc[] hits = searcher.search(query, 1000).scoreDocs;
		for (int i = 0; i < hits.length; i++) {
			Document hitDoc = searcher.doc(hits[i].doc);
			System.out.println(hitDoc.get("fieldname"));
		}
		reader.close();
		directory.close();		
	}
	
	@Test
	public void getDefaultConf(){
		IndexWriterConfig conf = new IndexWriterConfig();
		// CREATE_OR_APPEND
		System.out.println(conf.getOpenMode());
		// org.apache.lucene.analysis.standard.StandardAnalyzer@26ba2a48
		System.out.println(conf.getAnalyzer());
	}
}
