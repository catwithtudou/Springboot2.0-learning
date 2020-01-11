package cn.zhengyua.swaggerdemo.config;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 通用响应对象
 *
 * @author tudou
 * @date 2020/1/621:17
 */


@ApiModel(description = "响应对象")
@Data
public class BaseConfig<T> {

    private static final int SUCCESS_CODE = 0 ;
    private static final String SUCCESS_MSG="Success";

    @ApiModelProperty(value = "response code",name="code",required = true,example = ""+SUCCESS_CODE)
    private int code;

    @ApiModelProperty(value = "response message",name = "message",required = true,example = SUCCESS_MSG)
    private String msg;

    @ApiModelProperty(value = "response data",name = "data")
    private T data;

    private BaseConfig(int code,String msg,T data){
        this.code=code;
        this.data=data;
        this.msg=msg;
    }

    private BaseConfig() {
        this(SUCCESS_CODE, SUCCESS_MSG);
    }

    private BaseConfig(int code, String msg) {
        this(code, msg, null);
    }

    private BaseConfig(T data) {
        this(SUCCESS_CODE, SUCCESS_MSG, data);
    }

    public static <T> BaseConfig<T> success(){
        return new BaseConfig<>();
    }

    public static  <T> BaseConfig<T> successWithData(T data){return new BaseConfig<>(data);}

    public static  <T> BaseConfig<T> failWithCodeAndMsg(int code,String msg){
        return new BaseConfig<>(code,msg,null);
    }

    public static <T> BaseConfig<T> buildWithParam(ResponseParam param){
        return new BaseConfig<>(param.getCode(),param.getMsg(),null);
    }

    @Data
    public static class ResponseParam{
        private int code;
        private String msg;

        private ResponseParam(int code,String msg){
            this.code = code;
            this.msg = msg;
        }

        public static ResponseParam buildParam(int code,String msg){return new ResponseParam(code,msg);}
    }



}
