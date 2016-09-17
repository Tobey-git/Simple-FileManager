package Managers;

import java.io.File;
import java.util.Scanner;

public class Dir_FileDeleter {
/**
 * 删除文件/目录
 * @param SrcP 文件路径
 * @param SrcF 文件名 （删除目录时，文件名为空）
 * @return
 */
	public String Deleting(String SrcP, String SrcF){

		String sourcePath = SrcP;
		String sourceFile = SrcF;
		File file=new File(sourcePath+File.separator+sourceFile);
		if(!file.exists()){//判断路径是否有误
			return "no "+file+" exists!\n";
		}
		//调用删除操作
		else if(file.isFile()){
			return DeleteFile(file);
		}
		else if(file.isDirectory()){
			return DeleteDirectory(file);
		}
		return "deleting false!\n";
	}
	/**
	 * 删除文件
	 * @param fileName 文件
	 * @return
	 */
	public String DeleteFile(File fileName){
			if(fileName.delete())
				return "deleteing "+fileName+" Success!\n";
			else
				return "can't delete "+fileName+" !\n";
		}
	/**
	 * 删除目录（递归删除）
	 * @param dirName 目录
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


