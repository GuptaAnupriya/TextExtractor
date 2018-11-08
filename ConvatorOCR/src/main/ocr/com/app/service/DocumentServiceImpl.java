package com.app.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.app.model.Document;
import com.app.repository.DocumentDao;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;


@Service
public class DocumentServiceImpl implements DocumentService {

    @Autowired
    private DocumentDao documentDao;
    String result =null;
    Properties prop = new Properties();
   	InputStream input = null;

    public Document save(MultipartFile file) throws IOException {

        Document doc = new Document();
        doc.setDocName(file.getOriginalFilename());
        doc.setFile(file.getBytes());
        
    	input = new FileInputStream("D:\\Eclipse_workspace\\ConvatorOCR\\src\\main\\resources\\config.properties");
    	
    	prop.load(input);
    	System.out.println(prop.getProperty("tessdata"));
    	
    File img= new File("D:\\Eclipse_workspace\\ConvatorOCR\\images\\eurotext.pdf");
    	//File img= new File(file.toString());
    	/*File img = new File( file.getOriginalFilename());
    	 file.transferTo(img);*/
    	
    	
    	ITesseract i = new Tesseract();
    	i.setDatapath(prop.getProperty("tessdata"));
    	
    	try{
    		 result= i.doOCR(img);
    		System.out.println(result);
    		}
    	catch(TesseractException e){
    		
    	}
    
        doc.setFileData(result);
        
        
        return documentDao.save(doc);
       
    }

    public byte[] getDocumentFile(String id) {
        return documentDao.findOne(id).getFile();
    }

    public List<Document> findAll() {
        return (List<Document>) documentDao.findAll();
    }

}
