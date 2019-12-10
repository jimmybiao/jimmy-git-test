package com.jimmy;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {
	
	public static final String XML_FILE_PATH_A="/Users/biaojimmy/Documents/Dev/nid/1A.xml";
	public static final String XML_FILE_PATH_B="/Users/biaojimmy/Documents/Dev/nid/1B.xml";
	public static final String XML_FILE_PATH_C="/Users/biaojimmy/Documents/Dev/nid/3C.xml";
	public static final String XML_FILE_PATH_D="/Users/biaojimmy/Documents/Dev/nid/3D.xml";
	public static final String ACCT_FEDEX_FILE_PATH="/Users/biaojimmy/Documents/Dev/nid/fed_acct.txt";
	public static final String ACCT_TNT_FILE_PATH="/Users/biaojimmy/Documents/Dev/nid/tnt_acct.txt";
	
	public static void main(String[] args) throws Exception {
		
		final List<String> aLines=getlines(XML_FILE_PATH_A);
		final List<String> bLines=getlines(XML_FILE_PATH_B);
		final List<String> cLines=getlines(XML_FILE_PATH_C);
		final List<String> dLines=getlines(XML_FILE_PATH_D);
		
		//FEDEX
		final List<String> fedAcctList=getlines(ACCT_FEDEX_FILE_PATH);
		
		ReadFileRunnable runnableFedA=new ReadFileRunnable(XML_FILE_PATH_A,aLines, fedAcctList, "FEDEX");
		ReadFileRunnable runnableFedB=new ReadFileRunnable(XML_FILE_PATH_B,bLines, fedAcctList, "FEDEX");
		ReadFileRunnable runnableFedC=new ReadFileRunnable(XML_FILE_PATH_C,cLines, fedAcctList, "FEDEX");
		ReadFileRunnable runnableFedD=new ReadFileRunnable(XML_FILE_PATH_D,dLines, fedAcctList, "FEDEX");
		
		Thread t1=new Thread(runnableFedA);
		Thread t2=new Thread(runnableFedB);
		Thread t3=new Thread(runnableFedC);
		Thread t4=new Thread(runnableFedD);
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		
		//TNT
		final List<String> tntAcctList=getlines(ACCT_TNT_FILE_PATH);
		
		ReadFileRunnable runnableTntA=new ReadFileRunnable(XML_FILE_PATH_A,aLines, tntAcctList, "TNT");
		ReadFileRunnable runnableTntB=new ReadFileRunnable(XML_FILE_PATH_B,bLines, tntAcctList, "TNT");
		ReadFileRunnable runnableTntC=new ReadFileRunnable(XML_FILE_PATH_C,cLines, tntAcctList, "TNT");
		ReadFileRunnable runnableTntD=new ReadFileRunnable(XML_FILE_PATH_D,dLines, tntAcctList, "TNT");
		
		Thread t5=new Thread(runnableTntA);
		Thread t6=new Thread(runnableTntB);
		Thread t7=new Thread(runnableTntC);
		Thread t8=new Thread(runnableTntD);
		t5.start();
		t6.start();
		t7.start();
		t8.start();
	}
	
	private static List<String> getlines(String filePath) throws Exception{
		List<String> lines=new ArrayList<String>();
		BufferedReader reader=new BufferedReader(new FileReader(new File(filePath)));
		String line=null;
		while((line=reader.readLine())!=null) {
			lines.add(line);
		}
		reader.close();
		return lines;
	}
}
