package com.core.utils;

public class ResultUtils {

    public static Result sucess(String message, Object data){
        return new Result("200",data,message);
    }
    public static Result sucess(){
        return new Result();
    }
    public static Result error(String code,String message){
        return new Result(code,null,message);
    }

    public static final class Result{
        private String code;
        private Object data;
        private String message;

        public Result(String code,Object data,String message){
            this.code = code;
            this.data = data;
            this.message = message;
        }
        public Result(){
            code = "200";
            message = "操作成功";
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public Object getData() {
            return data;
        }

        public void setData(Object data) {
            this.data = data;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }

}
