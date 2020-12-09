package com.wk.manage_filesystem.dao;

import com.wk.framework.domain.filesystem.FileSystem;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FileSystemRepository extends MongoRepository<FileSystem, String> {
}
