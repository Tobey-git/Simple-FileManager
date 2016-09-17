package Managers;

import java.io.File;
import java.util.Scanner;

public class Dir_FileRenamer {
	/**
	 * 重命名
	 * @param oPath 源文件路径
	 * @param oName 源文件名
	 * @param nName 新文件名
	 * @return
	 */
	public String Renaming(String oPath, String oName, String nName){
		String oldPath=oPath;
		String oldName=oName;
		File old=new File(oldPath+File.separator+oldName);
		if(!old.exists()){
			return "no "+old+" exists!\n";

		}
		String newName=nName;
		//重命名
		File rname=new File(oldPath+File.separator+newName);
		old.renameTo(rname);
		return "Renaming Success!\n";

	      }
	}

