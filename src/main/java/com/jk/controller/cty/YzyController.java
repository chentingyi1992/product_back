package com.jk.controller.cty;

import com.alibaba.fastjson.JSON;
import com.jk.model.AreaBean;
import com.jk.model.CommBean;
import com.jk.model.HousBean;
import com.jk.model.TownBean;
import com.jk.service.BookService;
import com.jk.utils.AliyunOSSUtil;
import com.jk.utils.FileUtil;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("yzy")
public class YzyController {

    @Autowired
    private BookService bookService;

    @RequestMapping("yddHousList")
    @ResponseBody
    public HashMap<String, Object> findBookList(Integer page, Integer rows, HousBean housBean){
        return bookService.findBookList(page,rows,housBean);
    }

    //新增和修改
    @RequestMapping("addHous")
    @ResponseBody
    public void addUser(HousBean housBean){
        bookService.addUser(housBean);
    }

    @RequestMapping("findDeptList")
    @ResponseBody
    public List<AreaBean> findDeptList(){
        return bookService.findDeptList();
    }


    @RequestMapping("queryTown")
    @ResponseBody
    public List<TownBean> queryTown(Integer id){
        return bookService.queryTown(id);
    }

    @RequestMapping("queryComm")
    @ResponseBody
    public List<CommBean> queryComm(Integer id){
        return bookService.queryComm(id);
    }

    private final org.slf4j.Logger logger = LoggerFactory.getLogger(getClass());
    @RequestMapping("uploadimg")
    @ResponseBody
    public String uploadimg(MultipartFile file) throws IOException {
        logger.info("========>文件上传");
        String uploadUrl = "";
        try {
            if(null != file){
                String filename = file.getOriginalFilename();
                if(!"".equals(filename.trim())){
                    File newFile = new File(filename);
                    FileOutputStream os = new FileOutputStream(newFile);
                    os.write(file.getBytes());
                    os.close();
                    file.transferTo(newFile);
                    //上传到OSS
                    uploadUrl = AliyunOSSUtil.upload(newFile);
                }
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        String ImgUrl = "https://cold.oss-cn-beijing.aliyuncs.com/"+uploadUrl;
        return JSON.toJSONString(ImgUrl);
    }


    @RequestMapping("queryImgById")
    @ResponseBody
    public HousBean queryImgById(String id){
        HousBean housBean = bookService.queryImgById(id);
        return housBean;
    }

    public static void main(String[] args) {
        String a = "\u4f18\u79c0";
        System.out.println(a);
    }

}
