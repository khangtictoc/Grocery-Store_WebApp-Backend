package com.cybersoft.grocerystore.libraries.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cybersoft.grocerystore.app.product.service.imp.FileServiceImp;

@CrossOrigin
@RestController
@RequestMapping("/file")
public class FileController {

    @Autowired
    FileServiceImp fileServiceImp;

    @GetMapping("/{filename}")
    public ResponseEntity<?> downloadFile(@PathVariable String filename){
        try {
            Resource resource = fileServiceImp.load(filename);
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename =\"" + filename + "\"")
                    .body(resource);
        } catch (Exception exception) {
            throw new RuntimeException("loi khong tim thay file name, vui long nhap day du ten file + extension");
        }
    }
}
