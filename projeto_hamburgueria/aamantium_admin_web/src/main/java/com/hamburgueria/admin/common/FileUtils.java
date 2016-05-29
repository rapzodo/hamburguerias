package com.hamburgueria.admin.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import com.hamburgueria.constants.CommonConstants;

public class FileUtils {

	public static void createFile(String directory,String fileName, byte[] content) throws IOException{
		OutputStream os = null;
		try{
			File dir = new File(directory);
			dir.mkdir();
			File fotoFile = new File(dir, fileName);
			os = new FileOutputStream(fotoFile);
			os.write(content);
		}
		finally{
			try {
				os.flush();
				os.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static InputStream readFile(String filePath) throws FileNotFoundException{
		InputStream is = null;
		is = new FileInputStream(filePath);
		return is;
	}
	
	public static StreamedContent getGraphicImage(InputStream is, String filePath) throws FileNotFoundException{
		return new DefaultStreamedContent(readFile(filePath), CommonConstants.IMG_MIME_TYPE);
	}
}
