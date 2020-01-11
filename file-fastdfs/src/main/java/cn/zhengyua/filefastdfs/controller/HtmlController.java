package cn.zhengyua.filefastdfs.controller;

import org.springframework.web.bind.annotation.GetMapping;

/**
 * 进入标签页
 *
 * @author tudou
 * @date 2020/1/610:44
 */


public class HtmlController {

    @GetMapping("/")
    public String index() {
        return "upload";
    }

    @GetMapping("/uploadStatus")
    public String uploadStatus() {
        return "uploadStatus";
    }

}
