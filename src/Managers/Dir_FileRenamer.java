package Managers;

import java.io.File;
import java.util.Scanner;

public class Dir_FileRenamer {
	/**
	 * ������
	 * @param oPath Դ�ļ�·��
	 * @param oName Դ�ļ���
	 * @param nName ���ļ���
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
		//������
		File rname=new File(oldPath+File.separator+newName);
		old.renameTo(rname);
		return "Renaming Success!\n";

	      }
	}

