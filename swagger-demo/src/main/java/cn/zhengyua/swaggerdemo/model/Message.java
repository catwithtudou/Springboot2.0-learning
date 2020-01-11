package cn.zhengyua.swaggerdemo.model;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * Message 实体类
 *
 * @author tudou
 * @date 2020/1/621:02
 */


@Data
public class Message {

    private Long id ;

    @ApiModelProperty(value = "message body")
    private String text;

    @ApiModelProperty(value = "message summary")
    private String summary;

    private Date createDate;

    @Override
    public String toString(){
        return "Message{"+
                "id ="+id+
                ", text = "+text+'\''+
                ", summary = "+summary+'\''+
                ", createDate = "+createDate+
                '}';
    }
}
