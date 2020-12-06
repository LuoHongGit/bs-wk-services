package com.wk.manage_cms_client.config;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSBuckets;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * MongoDB配置类
 */
@Configuration
public class MongoConfig {
    @Value("${spring.data.mongodb.database}")
    private String db;

    @Bean
    public GridFSBucket gridFSBucket(MongoClient mongoClient){
        //获取数据库对象
        MongoDatabase database = mongoClient.getDatabase(db);

        //创建Bucket对象
        GridFSBucket bucket = GridFSBuckets.create(database);

        return bucket;
    }
}
