package Managers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class Dir_FileCopyer {
	/**
	 * 复制文件或目录
	 * @param srcPath 原路径
	 * @param srcFile 文件名
	 * @param tarPath 目标路径
	 * @return
	 * @throws IOException
	 */
	public String Copying(String srcPath, String srcFile, String tarPath) throws IOException{
		//判断文件是否存在
		File sourcefile=new File(srcPath+File.separator+srcFile);
		if(!sourcefile.exists()){
			return "no "+sourcefile+" exists!\n";
		}
		//在目标路径复制文件
		File targetfile=new File(tarPath+File.separator+srcFile);
		if(sourcefile.isFile()){
			//若是文件就调用CopyFile()
			if(CopyFile(sourcefile.toString(),targetfile.toString()))
				return "Copying "+sourcefile+"to " + tarPath + " Success!\n";
			else 
				return "Can't Copy "+sourcefile+"!\n";
		}
		else if(sourcefile.isDirectory()){
			//若是文件夹就调用CopyDirectory()
			if(CopyDirectory(sourcefile.toString(),targetfile.toString())){
				return "Copying "+sourcefile+"to " + tarPath + " Success!\n";
			}
			else 
			{
				return "Can't Copy "+sourcefile+"!\n";
			}
		}
		return "False";
	}
	/**
	 * 复制文件
	 * @param sourcefile 源文件
	 * @param targetfile 目标文件
	 * @return
	 * @throws IOException
	 */
	public boolean CopyFile(String sourcefile,String targetfile) throws IOException{
		byte[]bs=new byte[1024];
		FileInputStream input_Sor;
		try {
			input_Sor = new FileInputStream(sourcefile);
			FileOutputStream output_Tat=new FileOutputStream(targetfile);		
			while (true) {
	          int byteRead=input_Sor.read(bs); //从文件读数据给字节数组
	          if (byteRead==-1) //在文件尾，无数据可读
	              break;  //退出循环           
	          output_Tat.write(bs,0,byteRead);  //将读到的数据写入目标文件
		}
			input_Sor.close();
			output_Tat.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return true;		
	}
	/**
	 * 复制目录
	 * @param sour 要复制的目录
	 * @param targ 复制的目标路径
	 * @return
	 * @throws IOException
	 */
	public boolean CopyDirectory(String sour,String targ) throws IOException{
		File dir=new File(targ);
		dir.mkdirs();
		File sourcefile=new File(sour);
		File[]file=sourcefile.listFiles();
		for(int i=0;i<file.length;i++){
			if(file[i].isFile()){
				CopyFile(file[i].toString(),targ+File.separator+file[i].getName());
			}
			else if(file[i].isDirectory()){
				//递归调用CopyDirectory()以拷贝子目录
				CopyDirectory(file[i].toString(),targ+File.separator+file[i].getName());
			}
		}
		return true;
	}
}


