package Managers;

import java.io.File;
import java.util.Scanner;

import javax.swing.JFrame;

public class DirCreater {

	/**
	 * �½�Ŀ¼
	 * @param DirPath ·��
	 * @param DirName Ŀ¼��
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
