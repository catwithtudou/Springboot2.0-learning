package cn.zhengyua.filefastdfs.utils;

import lombok.Data;

/**
 * FastDFSFile封装类
 *
 * @author tudou
 * @date 2020/1/69:48
 */

@Data
public class DFSFile {

    private String name;

    private byte[] content;

    private String ext;

    private String author;

    private String md5;

    public DFSFile(String name,byte[] content,String ext,String height,String width,String author){
        super();
        this.name=name;
        this.content=content;
        this.ext=ext;
        this.author=author;
    }

    public DFSFile(String name,byte[] content,String ext){
        super();
        this.name = name;
        this.content=content;
        this.ext=ext;
    }



}
