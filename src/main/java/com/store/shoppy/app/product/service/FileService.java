package com.store.shoppy.app.product.service;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import com.store.shoppy.app.product.service.imp.FileServiceImp;

@Service
public class FileService implements FileServiceImp {
    @Value("${upload.file.folder}")
    private String uploadFolder;



    @Override
    public void save(MultipartFile file) {

        Path uploadFolderPath = Paths.get(uploadFolder);
        try {
            if (!Files.exists(uploadFolderPath)){
                Files.createDirectories(uploadFolderPath);
            }
            Files.copy(file.getInputStream(),uploadFolderPath.resolve(file.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);

        }catch (Exception e) {
            throw new RuntimeException("loi luu FileService "+e.getMessage());
        }
    }

    @Override
    public Resource load(String fileName) {
        try {
            Path pathImage = Paths.get(uploadFolder).resolve(fileName);
            Resource resource = new UrlResource(pathImage.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            }else {
                throw new RuntimeException("loi ko tim thay file hoac khong doc duoc file ");
            }

        } catch (Exception e) {
                throw new RuntimeException("loi ko tim thay file " + e.getMessage());
        }
    }
}
