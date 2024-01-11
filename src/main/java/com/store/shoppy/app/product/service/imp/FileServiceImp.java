package com.store.shoppy.app.product.service.imp;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FileServiceImp {

    void save(MultipartFile file);
    Resource load(String file);

}
