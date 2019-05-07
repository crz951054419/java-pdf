package com.beck.util;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class Test {

	public static void main(String[] args) throws Throwable {
		 
		String barcode = "12345678910";
		String uuid=UUID.randomUUID().toString();
		String htmlBasePath="D:\\Mars\\Download\\All\\study_simple_demo-master\\src\\main\\resources\\template";//html的根目录
		String tempathBasePath=Test.class.getResource("/").getPath()+"template/";//模板路径
		String pdfBasePath="D:\\Mars\\Download\\All\\study_simple_demo-master\\src\\main\\resources\\template";//生成的pdf目录
		
		String barCodePath="D:\\Mars\\Download\\All\\study_simple_demo-master\\src\\main\\resources\\template"+uuid+".png";
		String htmlPath=htmlBasePath+uuid+".html";
		String pdfPath=pdfBasePath+uuid+".pdf";
		
		//生成条形码
		File barCodeFile=new File(barCodePath);
		BarcodeUtil.generateToFile(barcode,BarcodeUtil.IMG_TYPE_PNG,barCodeFile);
		System.out.println("生成条形码完成！");
		//生成html
		FreemarkerTemplate tp=new FreemarkerTemplate("UTF-8");
		tp.setTemplateDirectoryPath(tempathBasePath);
		//封装数据 start ，必须是Map
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("barcode",barcode);
		dataMap.put("barcodeImg",barCodePath);
		dataMap.put("userName", "游总");
		dataMap.put("submitDate", "2018-09-18");
		dataMap.put("deptName", "研发平台");
        dataMap.put("img1", "D:\\Mars\\Work\\eclipse_path\\CDPathSta\\src\\main\\resources\\static\\images\\c74577b6572d3b1caa8c4d77f24e3c5.bmp");
		//费用明细
		List<Receipt> receiptList=new ArrayList<Receipt>();//Receipt 必须是public类型的类
		Receipt r1=new Receipt();
		r1.setReceiptType("普通发票");
		r1.setAmount(100);
		
		Receipt r2=new Receipt();
		r2.setReceiptType("普通发票");
		r2.setAmount(100);
		
		receiptList.add(r1);
		receiptList.add(r2);
		
		dataMap.put("receiptList", receiptList);
		//封装数据 end
		File htmlFile=new File(htmlPath);
		tp.processToFile("expense.html", dataMap,htmlFile);
		System.out.println("生成html完成！");
		//生成pdf
		PDFUtils.htmlFileToPDFFile(htmlFile, new File(pdfPath), new File(htmlBasePath));
		System.out.println("生成pdf完成！");
		

	}

}

