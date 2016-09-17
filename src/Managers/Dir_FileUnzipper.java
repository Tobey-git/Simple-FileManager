package Managers;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class Dir_FileUnzipper {

    /** 
     * 解压ZIP文件 
     * @param zipFile  要解压的ZIP文件路径 
     * @param destination  解压到哪里 
     * @throws IOException 
     */ 
    public String decompression(String zipFile,String destination) throws IOException { 
        ZipFile zip=new ZipFile(zipFile); 
        if(!new File(zipFile).exists()){
			return "no "+zipFile+" exists!\n";
		}
        Enumeration en=zip.entries(); 
        ZipEntry entry=null; 
        byte[] buffer=new byte[8192]; 
        int length=-1; 
        InputStream input=null; 
        BufferedOutputStream bos=null; 
        File file=null; 
        
        while(en.hasMoreElements()) { 
            entry=(ZipEntry)en.nextElement(); 
            
            input=zip.getInputStream(entry); 
            file=new File(destination,entry.getName()); 
            if(!file.getParentFile().exists()) { //文件没有父目录则创建父目录
                file.getParentFile().mkdirs(); 
            } 
            bos=new BufferedOutputStream(new FileOutputStream(file)); 
            
            while(true) { 
                length=input.read(buffer); 
                if(length==-1) break; 
                bos.write(buffer,0,length); 
            } 
            bos.close(); 
            input.close(); 
        } 
        zip.close(); 
        return "Unzipping "+zipFile+" Success!\n";
    } 
}
