package Managers;

import java.io.File;
import java.util.Scanner;

public class FileLister {
	/**
	 * ��ʾĿ¼�е�����
	 * @param dirPath Ŀ¼·��
	 * @param dirName Ŀ¼��
	 * @return
	 */
	public String Listing(String dirPath, String dirName){
		
		File file=new File(dirPath+File.separator+dirName);
		File []temp=file.listFiles();
		String dir_list = "    Ŀ¼��\n";
		String file_list = "    �ļ���\n";
		String result = file.getAbsolutePath() + "\n";
		for(int i=0;i<temp.length;i++){//ѭ�����Ŀ¼����
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
