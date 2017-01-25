package com.gardecote.web;

import org.springframework.web.multipart.MultipartFile;

/**
 * Created by Dell on 25/01/2017.
 */
public class FileBucket {
    MultipartFile file;

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }
}
