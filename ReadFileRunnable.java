package com.jimmy;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.List;

public class ReadFileRunnable implements Runnable {

	private List<String> xmlLines;
	private List<String> acctList;
	private String entity;
	private String xmlFilePath;

	public ReadFileRunnable(String xmlFilePath,List<String> xmlLines, List<String> acctList, String entity) {
		this.xmlFilePath=xmlFilePath;
		this.xmlLines = xmlLines;
		this.acctList = acctList;
		this.entity=entity;
	}

	public void run() {
		
		try {
			String acct;
			for(int i=0;i<acctList.size();i++) {
				acct=acctList.get(i);
				System.out.println(acct);
				BufferedWriter writer=new BufferedWriter(new FileWriter(new File(xmlFilePath.substring(0,xmlFilePath.lastIndexOf("/")+1)+entity+"/"+acct+"-"+xmlFilePath.substring(xmlFilePath.indexOf('.')-1,xmlFilePath.indexOf('.'))+".xml")));
				String lineXml;
				for(int j=0;j<xmlLines.size();j++) {
					lineXml=xmlLines.get(j);
					lineXml=lineXml.replace("account>110968124", "account>" + acct);
					if(entity.equals("TNT"))
						lineXml=lineXml.replace("FEDEX", "TNT");
					lineXml=lineXml.replace("110940262", acct);
					writer.write(lineXml+"\n");
				}
				writer.close();	
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
