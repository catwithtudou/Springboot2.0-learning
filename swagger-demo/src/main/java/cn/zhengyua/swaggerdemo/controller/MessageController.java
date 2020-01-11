package cn.zhengyua.swaggerdemo.controller;

import cn.zhengyua.swaggerdemo.config.BaseConfig;
import cn.zhengyua.swaggerdemo.model.Message;
import cn.zhengyua.swaggerdemo.repository.MessageRepository;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * MessageController层
 *
 * @author tudou
 * @date 2020/1/914:40
 */



@Api(value = "消息",protocols = "http")
@RestController
public class MessageController {

    @Autowired
    private MessageRepository messageRepository;

    @ApiOperation(
            value = "消息列表",
            notes = "完整的消息内容列表",
            produces =  "application/json,application/xml",
            consumes = "application/json,application/xml",
            response = List.class
    )
    @GetMapping(value = "/messages")
    public List<Message> list(){
        return this.messageRepository.findAll();
    }


    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "消息ID", required = true, dataType = "Long", paramType = "query"),
            @ApiImplicitParam(name = "text", value = "消息内容", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "summary", value = "消息摘要", required = false, dataType = "String", paramType = "query")
    }
    )
    @PostMapping(value = "/message")
    public Message create(Message message){
        System.out.println(message.toString());
        return this.messageRepository.save(message);
    }


    @ApiOperation(
            value = "修改消息",
            notes = "根据参数修改消息"
    )
    @PutMapping(value = "/message")
    @ApiResponses({
            @ApiResponse(code=100,message="param error"),
            @ApiResponse(code=101,message = "verify error"),
            @ApiResponse(code = 103,message = "innner error")
    })
    public Message modify(Message message){
        return this.messageRepository.update(message);
    }

    @PatchMapping(value = "/message/text")
    public BaseConfig<Message> patch(Message message){
        return BaseConfig.successWithData(this.messageRepository.updateText(message));
    }

    @GetMapping(value = "/message/{id}")
    public Message get(@PathVariable Long id){
        return this.messageRepository.findMessage(id);
    }

    @DeleteMapping(value = "message/{id}")
    public void delete(@PathVariable("id") Long id){
        this.messageRepository.deleteMessage(id);
    }



}
