package Managers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class Dir_FileCopyer {
	/**
	 * �����ļ���Ŀ¼
	 * @param srcPath ԭ·��
	 * @param srcFile �ļ���
	 * @param tarPath Ŀ��·��
	 * @return
	 * @throws IOException
	 */
	public String Copying(String srcPath, String srcFile, String tarPath) throws IOException{
		//�ж��ļ��Ƿ����
		File sourcefile=new File(srcPath+File.separator+srcFile);
		if(!sourcefile.exists()){
			return "no "+sourcefile+" exists!\n";
		}
		//��Ŀ��·�������ļ�
		File targetfile=new File(tarPath+File.separator+srcFile);
		if(sourcefile.isFile()){
			//�����ļ��͵���CopyFile()
			if(CopyFile(sourcefile.toString(),targetfile.toString()))
				return "Copying "+sourcefile+"to " + tarPath + " Success!\n";
			else 
				return "Can't Copy "+sourcefile+"!\n";
		}
		else if(sourcefile.isDirectory()){
			//�����ļ��о͵���CopyDirectory()
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
	 * �����ļ�
	 * @param sourcefile Դ�ļ�
	 * @param targetfile Ŀ���ļ�
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
	          int byteRead=input_Sor.read(bs); //���ļ������ݸ��ֽ�����
	          if (byteRead==-1) //���ļ�β�������ݿɶ�
	              break;  //�˳�ѭ��           
	          output_Tat.write(bs,0,byteRead);  //������������д��Ŀ���ļ�
		}
			input_Sor.close();
			output_Tat.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return true;		
	}
	/**
	 * ����Ŀ¼
	 * @param sour Ҫ���Ƶ�Ŀ¼
	 * @param targ ���Ƶ�Ŀ��·��
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
				//�ݹ����CopyDirectory()�Կ�����Ŀ¼
				CopyDirectory(file[i].toString(),targ+File.separator+file[i].getName());
			}
		}
		return true;
	}
}


