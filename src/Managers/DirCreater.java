package Managers;

import java.io.File;
import java.util.Scanner;

import javax.swing.JFrame;

public class DirCreater {

	/**
	 * 新建目录
	 * @param DirPath 路径
	 * @param DirName 目录名
	 * @return
	 */
	public String Creating(String dirPath, String dirName){
		
		File file=new File(dirPath+File.separator+dirName);
	    if(file.mkdirs()){
	    	return "creating dirctory " + file.getAbsolutePath() + " Success!" + "\n";
		}
	    else
		    return "can't create directory!\n";

	}

}
