package com.wk.manage_media;

import com.github.andrewoma.dexx.collection.ArrayList;
import org.junit.Test;

import java.io.File;
import java.io.RandomAccessFile;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @ClassName RandomFileTest
 * @Description 类作用描述
 * @Author LuoHong
 * @Date 2020/12/15 14:36
 * @Version 1.0
 **/

public class RandomFileTest {

    @Test
    public void testTrunk() throws Exception{

        //创建源文件
        File sourceFile = new File("E:\\bs-wk-code\\wk-ui\\wk-video\\lucene.avi");

        //设置分块大小
        long chunkSize = 1004*1024*1;

        //设置分块数量
        long chunkNum = (long)Math.ceil(sourceFile.length()*1.0/chunkSize);

        //设置分块文件路径
        String chunkPathStr = "E:\\bs-wk-code\\wk-ui\\wk-video\\chunk\\";

        //创建分块文件夹对象
        File chunkDir = new File(chunkPathStr);

        //判断是否存在
        if (!chunkDir.exists()) {
            chunkDir.mkdirs();
        }

        //使用RandomAccessFile访问源文件
        RandomAccessFile sourceAccessFile = new RandomAccessFile(sourceFile, "rw");

        //创建缓冲区
        byte[] buffer = new byte[1024];

        //循环输出
        for (int i = 0; i < chunkNum; i++) {
            //创建分块文件对象
            File chunkFile = new File(chunkPathStr + i);

            //创建文件
            boolean isSuccess = chunkFile.createNewFile();

            //使用RandomAccessFile访问分块文件
            RandomAccessFile chunkAccessFile = new RandomAccessFile(chunkFile, "rw");

            if (isSuccess) {
                //开始输出
                int len = -1;
                while ((len = sourceAccessFile.read(buffer)) != -1) {
                    chunkAccessFile.write(buffer,0,len);
                    if (chunkAccessFile.length() > chunkSize) {
                        break;
                    }
                }

            }

            chunkAccessFile.close();
        }

        sourceAccessFile.close();
    }

    @Test
    public void testTrunk1() throws Exception{
        File sourceFile = new File("E:\\bs-wk-code\\wk-ui\\wk-video\\lucene.avi");
        String chunkPath = "E:\\bs-wk-code\\wk-ui\\wk-video\\chunk\\";
        File chunkFolder = new File(chunkPath);
        if(!chunkFolder.exists()){
            chunkFolder.mkdirs();
        }
        //分块大小
        long chunkSize = 1024*1024*1;
        //分块数量
        long chunkNum = (long) Math.ceil(sourceFile.length() * 1.0 / chunkSize );
        if(chunkNum<=0){
            chunkNum = 1;
        }
        //缓冲区大小
        byte[] b = new byte[1024];
        //使用RandomAccessFile访问文件
        RandomAccessFile raf_read = new RandomAccessFile(sourceFile, "r");
        //分块
        for(int i=0;i<chunkNum;i++){
            //创建分块文件
            File file = new File(chunkPath+i);
            boolean newFile = file.createNewFile();
            if(newFile){
                //向分块文件中写数据
                RandomAccessFile raf_write = new RandomAccessFile(file, "rw");
                int len = -1;
                while((len = raf_read.read(b))!=-1){
                    raf_write.write(b,0,len);
                    if(file.length()>chunkSize){
                        break;
                    }
                }
                raf_write.close();
            }
        }
        raf_read.close();
    }

    @Test
    public void testMerge() throws Exception{
        File chunkFolder = new File("E:\\bs-wk-code\\wk-ui\\wk-video\\chunk\\");
        File sourceFile = new File("E:\\bs-wk-code\\wk-ui\\wk-video\\lucene1.avi");
        if(!sourceFile.exists()){
            sourceFile.createNewFile();
        }

        //列出分块文件夹下的所有的文件
        File[] fileArray = chunkFolder.listFiles();
        // 转成集合，便于排序
        List<File> fileList = Arrays.asList(fileArray);

        Arrays.sort(fileArray, new Comparator<File>() {
            @Override
            public int compare(File o1, File o2) {
                return Integer.parseInt(o1.getName()) > Integer.parseInt(o2.getName()) ? 1 : -1;
            }
        });

        //缓冲区大小
        byte[] buffer = new byte[1024];

        //使用RandomAccessFile访问输出文件
        RandomAccessFile outAccessFile = new RandomAccessFile(sourceFile, "rw");

        for (File file : fileList) {
            //使用AccessFile访问分块文件
            RandomAccessFile chunkFile = new RandomAccessFile(file, "rw");

            int len = -1;

            while ((len=chunkFile.read(buffer)) != -1) {
                outAccessFile.write(buffer,0,len);
            }
            chunkFile.close();
        }

        outAccessFile.close();
    }

}
