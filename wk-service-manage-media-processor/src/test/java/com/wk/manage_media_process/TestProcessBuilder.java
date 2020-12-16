package com.wk.manage_media_process;

import org.junit.Test;

import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @ClassName TestProcessBuilder
 * @Description ProcessBuilder测试类
 * @Author LuoHong
 * @Date 2020/12/15 22:17
 * @Version 1.0
 **/
public class TestProcessBuilder {

    @Test
    public void testIpconfig() throws Exception{
        //创建ProcessBuilder
        ProcessBuilder processBuilder = new ProcessBuilder();

        //设置命令
        processBuilder.command("ipconfig");

        //设置流合并
        processBuilder.redirectErrorStream(true);

        //启动进程
        Process process = processBuilder.start();

        //获取输入流
        InputStream inputStream = process.getInputStream();

        //转换为字符流
        InputStreamReader reader = new InputStreamReader(inputStream,"gbk");

        //读取内容输出
        char[] chars = new char[1024];
        int len = -1;
        while ((len = reader.read(chars)) != -1) {
            System.out.println(new String(chars,0,len));
        }

        inputStream.read();
        reader.close();

    }

}
