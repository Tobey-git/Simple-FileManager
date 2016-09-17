package Managers;

import java.io.File;
import java.util.Scanner;

public class FileLister {
	/**
	 * 显示目录中的内容
	 * @param dirPath 目录路径
	 * @param dirName 目录名
	 * @return
	 */
	public String Listing(String dirPath, String dirName){
		
		File file=new File(dirPath+File.separator+dirName);
		File []temp=file.listFiles();
		String dir_list = "    目录：\n";
		String file_list = "    文件：\n";
		String result = file.getAbsolutePath() + "\n";
		for(int i=0;i<temp.length;i++){//循环添加目录内容
			if(temp[i].isDirectory()){
				dir_list += "        " + temp[i].getName() + "\n";
			}else if(temp[i].isFile()) {
				file_list += "        " + temp[i].getName() + "\n";
			}
		}
		result += dir_list + file_list;
		return result;
	}

}
