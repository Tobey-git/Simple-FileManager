package Managers;

import java.io.BufferedInputStream; 
import java.io.File; 
import java.io.FileInputStream; 
import java.io.FileOutputStream; 
import java.io.IOException; 
import java.util.ArrayList; 
import java.util.List; 
import java.util.zip.ZipEntry; 
import java.util.zip.ZipOutputStream; 

public class Dir_FileZipper {
    /** 
     * �ݹ�ѹ���ļ� 
     * @param source Դ·��,�������ļ�,Ҳ����Ŀ¼ 
     * @param destinct  Ŀ��·��,ѹ���ļ��� 
     * @throws IOException 
     */ 
    public String compress(String source,String destinct) throws IOException { 
    	//�ж��ļ��Ƿ����
    	if(!new File(source).exists()){
			return "no "+source+" exists!\n";
		}
    	//��ȡ�ļ��б�
        List fileList=loadFilename(new File(source)); 
        ZipOutputStream zos=new ZipOutputStream(new FileOutputStream(new File(destinct))); 
        
        byte[] buffere=new byte[8192]; 
        int length; 
        BufferedInputStream bis; 
        
        for(int i=0;i<fileList.size();i++) { 
            File file=(File) fileList.get(i); 
            zos.putNextEntry(new ZipEntry(getEntryName(source,file))); 
            bis=new BufferedInputStream(new FileInputStream(file)); 
            
            while(true) { 
                length=bis.read(buffere); 
                if(length==-1) break; 
                zos.write(buffere,0,length); 
            } 
            bis.close(); 
            zos.closeEntry(); 
        } 
        zos.close(); 
        return "zipping "+source+" Success!\n";
    } 
    /** *//** *//** *//** 
     * �ݹ��ø��ļ��������ļ���(������Ŀ¼��) 
     * @param file 
     * @return 
     */ 
    private static List loadFilename(File file) { 
        List filenameList=new ArrayList(); 
        if(file.isFile()) { 
            filenameList.add(file); 
        } 
        if(file.isDirectory()) { 
            for(File f:file.listFiles()) { 
                filenameList.addAll(loadFilename(f)); 
            } 
        } 
        return filenameList; 
    } 
    /** *//** *//** *//** 
     * ���zip entry �ַ��� 
     * @param base 
     * @param file 
     * @return 
     */ 
    private static String getEntryName(String base,File file) { 
        File baseFile=new File(base); 
        String filename=file.getPath(); 
        //int index=filename.lastIndexOf(baseFile.getName()); 
        if(baseFile.getParentFile().getParentFile()==null) 
            return filename.substring(baseFile.getParent().length()); 
        return filename.substring(baseFile.getParent().length()+1); 
    } 
} 
	    
