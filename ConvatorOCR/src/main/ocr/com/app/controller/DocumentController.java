package com.app.controller;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.app.model.Document;
import com.app.service.DocumentService;
import com.app.service.ResponseMetadata;

import java.io.IOException;
import java.util.List;


@Controller
@RequestMapping(value = "/doc")
public class DocumentController {

    private static final Logger LOG = Logger.getLogger(DocumentController.class);

    @Autowired
    DocumentService documentService;
    
    Document doc;

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public ResponseEntity handleFileUpload(@RequestParam(value = "file") MultipartFile file) throws IOException {
      doc=   documentService.save(file);
         return new ResponseEntity(doc, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public HttpEntity<byte[]> getDocument(@PathVariable String id) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.IMAGE_JPEG);
        return new ResponseEntity<byte[]>(documentService.getDocumentFile(id), httpHeaders, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody
    List<Document> getDocument() {
        return documentService.findAll();
    }

}
