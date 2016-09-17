package Managers;

import java.io.File;
import java.util.Scanner;

public class Dir_FileDeleter {
/**
 * ɾ���ļ�/Ŀ¼
 * @param SrcP �ļ�·��
 * @param SrcF �ļ��� ��ɾ��Ŀ¼ʱ���ļ���Ϊ�գ�
 * @return
 */
	public String Deleting(String SrcP, String SrcF){

		String sourcePath = SrcP;
		String sourceFile = SrcF;
		File file=new File(sourcePath+File.separator+sourceFile);
		if(!file.exists()){//�ж�·���Ƿ�����
			return "no "+file+" exists!\n";
		}
		//����ɾ������
		else if(file.isFile()){
			return DeleteFile(file);
		}
		else if(file.isDirectory()){
			return DeleteDirectory(file);
		}
		return "deleting false!\n";
	}
	/**
	 * ɾ���ļ�
	 * @param fileName �ļ�
	 * @return
	 */
	public String DeleteFile(File fileName){
			if(fileName.delete())
				return "deleteing "+fileName+" Success!\n";
			else
				return "can't delete "+fileName+" !\n";
		}
	/**
	 * ɾ��Ŀ¼���ݹ�ɾ����
	 * @param dirName Ŀ¼
	 * @return
	 */
	public String DeleteDirectory(File dirName){
		File[]fileList=dirName.listFiles();
		for(int i=0;i<fileList.length;i++){
			if(fileList[i].isDirectory()){
				DeleteDirectory(fileList[i]);
			}
	    	else{
				fileList[i].delete();
			}
		}
		if(dirName.delete())
		    return "deleting "+dirName+" Success!\n";
		else
			return "can't delete "+dirName+" !\n";
	}
	
}


