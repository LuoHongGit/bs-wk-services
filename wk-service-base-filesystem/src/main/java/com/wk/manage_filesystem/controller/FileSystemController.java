package com.wk.manage_filesystem.controller;


import com.wk.api.filesystem.FileSystemControllerApi;
import com.wk.framework.domain.filesystem.response.UploadFileResult;
import com.wk.manage_filesystem.service.FileSystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/filesystem")
public class FileSystemController implements FileSystemControllerApi {
    @Autowired
    private FileSystemService fileSystemService;

    @PostMapping("/upload")
    public UploadFileResult upload(@RequestParam("file") MultipartFile file,
                                   @RequestParam(value = "filetag", required = false) String
                                           filetag,
                                   @RequestParam(value = "businesskey", required = false) String
                                               businesskey,
                                   @RequestParam(value = "metedata", required = false) String
                                               metadata){
        return fileSystemService.upload(file,filetag,businesskey,metadata);
    }
}
