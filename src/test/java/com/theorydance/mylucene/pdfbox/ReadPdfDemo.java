package com.theorydance.mylucene.pdfbox;

import java.io.File;
import java.io.FileInputStream;
import java.io.PrintWriter;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;
import org.apache.pdfbox.text.PDFTextStripper;
import org.junit.Test;

/**
 * pdfbox的demo学习，简单得到文本数据和文件的基本属性
 * @author TheoryDance
 */
public class ReadPdfDemo {
	
	@Test
	public void test() throws Exception{
		FileInputStream fis = new FileInputStream(new File("G:/公司技术开发资料/环境空气质量指数.pdf"));
		PDDocument pdfDocument = PDDocument.load(fis);
		
		// pdf的文本剥离，将pdf的内容输出到System.out中
		PDFTextStripper stripper = new PDFTextStripper();
		PrintWriter out = new PrintWriter(System.out);
		stripper.writeText(pdfDocument, out);
		
		// 得到pdf文件的基本属性
		PDDocumentInformation info = pdfDocument.getDocumentInformation();
		System.out.println("Author:"+info.getAuthor());
        System.out.println("CreationDate:"+info.getCreationDate());
        System.out.println("Creator:"+info.getCreator());
        System.out.println("Keywords:"+info.getKeywords());
        System.out.println("ModificationDate:"+info.getModificationDate());
        System.out.println("Producer:"+info.getProducer());
        System.out.println("Subject:"+info.getSubject());
        System.out.println("Title:"+info.getTitle());
        System.out.println("Trapped:"+info.getTrapped());
        pdfDocument.close();
        out.close();
        fis.close();
	}
	
}
