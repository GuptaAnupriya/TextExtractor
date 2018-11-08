package com.app.service;


import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.app.model.Document;

import java.io.IOException;
import java.util.List;


@Service
public interface DocumentService {

    Document save(MultipartFile multipartFile) throws IOException;

    byte[] getDocumentFile(String id);

    List<Document> findAll();
}
