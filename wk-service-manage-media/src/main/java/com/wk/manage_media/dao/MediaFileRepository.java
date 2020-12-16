package com.wk.manage_media.dao;

import com.wk.framework.domain.media.MediaFile;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @ClassName MediaFileRepository
 * @Description 媒体文件持久层接口
 * @Author LuoHong
 * @Date 2020/12/15 14:34
 * @Version 1.0
 **/

public interface MediaFileRepository extends MongoRepository<MediaFile, String> {
}
