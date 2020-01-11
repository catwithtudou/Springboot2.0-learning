package cn.zhengyua.filefastdfs.controller;

import cn.zhengyua.filefastdfs.utils.DFSFile;
import cn.zhengyua.filefastdfs.utils.DFSFileClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.io.InputStream;

/**
 * DFSFileController层
 *
 * @author tudou
 * @date 2020/1/610:42
 */


public class UploadController {

    private static Logger logger = LoggerFactory.getLogger(UploadController.class);

    /**
     * 上传Controller
     */
    @PostMapping("/upload")
    public String singleFileUpload(@RequestParam("file")MultipartFile file, RedirectAttributes redirectAttributes){

        if(file.isEmpty()){
            redirectAttributes.addFlashAttribute("message","Please select a file to upload");
            return "redirect:uploadStatus";
        }
        
        try{
            String path = saveFile(file);
            redirectAttributes.addFlashAttribute("message","" +
                    "uploaded '"+file.getOriginalFilename()+"' successfully ");
            redirectAttributes.addFlashAttribute("path","file path url:'"+path+"'");
        }catch (Exception e){
            logger.error("upload file failed ",e);
        }
        return "redirect:/uploadStatus";
    }
    
    /**
     * 上传到fastDfs中
     */
    public String saveFile(MultipartFile multipartFile) throws IOException{
        String[] fileAbsolutePath = {};
        String fileName = multipartFile.getOriginalFilename();
        String ext = fileName.substring(fileName.lastIndexOf(".")+1);
        byte[] file_buff = null;
        InputStream inputStream = multipartFile.getInputStream();
        if(inputStream!=null){
            int length1= inputStream.available();
            file_buff = new byte[length1];
            inputStream.read(file_buff);
        }
        inputStream.close();
        DFSFile file = new DFSFile(fileName,file_buff,ext);
        try{
            fileAbsolutePath = DFSFileClient.upload(file);
        }catch (Exception e){
            logger.error("upload File Exception",e);
        }

        if (fileAbsolutePath == null){
            logger.error("upload file failed,please upload again!");
        }
        String path = DFSFileClient.getTrackerUrl()+fileAbsolutePath[0]+"/"+fileAbsolutePath[1];
        return path;
    }

}


