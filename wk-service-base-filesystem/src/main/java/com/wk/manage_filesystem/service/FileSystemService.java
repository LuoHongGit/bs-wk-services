package com.wk.manage_filesystem.service;

import com.alibaba.fastjson.JSON;
import com.wk.framework.domain.filesystem.FileSystem;
import com.wk.framework.domain.filesystem.response.FileSystemCode;
import com.wk.framework.domain.filesystem.response.UploadFileResult;
import com.wk.framework.exception.ExceptionCast;
import com.wk.framework.model.response.CommonCode;
import com.wk.manage_filesystem.dao.FileSystemRepository;
import org.apache.commons.lang3.StringUtils;
import org.csource.fastdfs.*;
import org.hibernate.tuple.entity.EntityTuplizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@Service
public class FileSystemService {
    @Autowired
    private FileSystemRepository fileSystemRepository;

    @Value("${wk.fastdfs.tracker_servers}")
    String tracker_servers;
    @Value("${wk.fastdfs.connect_timeout_in_seconds}")
    int connect_timeout_in_seconds;
    @Value("${wk.fastdfs.network_timeout_in_seconds}")
    int network_timeout_in_seconds;
    @Value("${wk.fastdfs.charset}")
    String charset;

    /**
     * 初始化客户端配置
     */
    private void initFdfsConfig() {
        try {
            //设置客户端参数
            ClientGlobal.initByTrackers(tracker_servers);
            ClientGlobal.setG_connect_timeout(connect_timeout_in_seconds);
            ClientGlobal.setG_network_timeout(network_timeout_in_seconds);
            ClientGlobal.setG_charset(charset);
        } catch (Exception e) {
            e.printStackTrace();
            ExceptionCast.cast(FileSystemCode.FS_UPLOADFILE_SERVERFAIL);
        }
    }

    /**
     * 上传文件主方法
     * @param file
     * @param filetag
     * @param businessKey
     * @param metadata
     * @return
     */
    @Transactional
    public UploadFileResult upload(MultipartFile file, String filetag, String businessKey, String metadata) {
        //文件判空
        if (file == null) {
            ExceptionCast.cast(FileSystemCode.FS_UPLOADFILE_FILEISNULL);
        }

        //上传文件
        String fileId = fdfs_upload(file);

        //判空
        if (StringUtils.isEmpty(fileId)) {
            ExceptionCast.cast(FileSystemCode.FS_UPLOADFILE_SERVERFAIL);
        }

        //创建FileSystem对象
        FileSystem fileSystem = new FileSystem();

        //设置属性
        fileSystem.setFileId(fileId);
        fileSystem.setFilePath(fileId);
        fileSystem.setFileName(file.getOriginalFilename());
        fileSystem.setFileType(file.getContentType());
        fileSystem.setFileSize(file.getSize());
        fileSystem.setFiletag(filetag);
        fileSystem.setBusinesskey(businessKey);
        if (!StringUtils.isEmpty(metadata)) {
            //将字符串转换为Map
            Map map = JSON.parseObject(metadata, Map.class);
            fileSystem.setMetadata(map);
        }

        //保存到mongodb
        fileSystemRepository.save(fileSystem);

        return new UploadFileResult(CommonCode.SUCCESS, fileSystem);
    }

    /**
     * 将文件保存到MongoDB
     * @param file
     * @return
     */
    public String fdfs_upload(MultipartFile file) {
        try {
            //初始化fdfs客户端
            initFdfsConfig();

            //创建trackerclient
            TrackerClient trackerClient = new TrackerClient();

            //获取连接
            TrackerServer trackerServer = trackerClient.getConnection();

            //获取storageServer
            StorageServer storageServer = trackerClient.getStoreStorage(trackerServer);

            //获取storageClient
            StorageClient1 storageClient1 = new StorageClient1(trackerServer, storageServer);

            //获取文件字节
            byte[] fileBytes = file.getBytes();

            //文件原始名称
            String originalFilename = file.getOriginalFilename();

            //文件扩展名
            String extName = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);

            //上传文件获取文件id
            String fileId = storageClient1.upload_file1(fileBytes, extName, null);

            return fileId;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
