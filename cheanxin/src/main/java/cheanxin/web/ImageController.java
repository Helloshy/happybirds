package cheanxin.web;

import cheanxin.image.FastDFSFile;
import cheanxin.image.FileManager;
import org.csource.common.MyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * Created by 向麒 on 2017/1/12.
 */
@RestController
@RequestMapping("/image")
public class ImageController extends BaseController {
    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    public ResponseEntity<String> add(@RequestBody(required = false) MultipartFile file) throws IOException, MyException {
        //获取文件扩展名
        String ext_Name = file.getOriginalFilename().split("\\.")[1];
        //获取文件名
        String videoName=file.getOriginalFilename().split("\\.")[0];

        byte[] bytes = null;
        try {
            bytes = file.getBytes(); //将文件转换成字节流形式
        } catch (IOException e) {
            e.printStackTrace();
        }
        //调用上传文件的具体方法
        FastDFSFile file1 = new FastDFSFile(videoName,bytes,ext_Name);
        String fileUrl = FileManager.upload(file1);

        return new ResponseEntity<>(fileUrl,HttpStatus.MULTI_STATUS.OK);
    }
}
