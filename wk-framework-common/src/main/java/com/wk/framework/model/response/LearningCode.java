package com.wk.framework.model.response;

/**
 * @ClassName LearningCode
 * @Description 类作用描述
 * @Author LuoHong
 * @Date 2020/12/18 20:53
 * @Version 1.0
 **/
public enum LearningCode implements ResultCode{

    LEARNING_GETMEDIA_ERROR(false,20003,"学习中心获取媒体信息失败！");

    //private static ImmutableMap<Integer, CommonCode> codes ;
    //操作是否成功
    boolean success;
    //操作代码
    int code;
    //提示信息
    String message;
    private LearningCode(boolean success,int code, String message){
        this.success = success;
        this.code = code;
        this.message = message;
    }

    @Override
    public boolean success() {
        return success;
    }

    @Override
    public int code() {
        return code;
    }

    @Override
    public String message() {
        return message;
    }


}
