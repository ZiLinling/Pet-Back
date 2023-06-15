package com.xmut.pet.controller;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.xmut.pet.entity.Result;
import com.xmut.pet.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Zi
 * @since 2023-06-08 10:00:22
 */
@RestController
@RequestMapping("/resource")
public class ResourceController {

    @Autowired
    private ResourceService resourceService;

    @Value("${files.upload.path}")
    private String fileUploadPath;

    @PostMapping("/upload/{directory}")
    public Result upload(@RequestBody MultipartFile file, @PathVariable String directory) throws IOException {
        Result<String> res = new Result<>();
        String originalFilename = file.getOriginalFilename();
        String type = FileUtil.extName(originalFilename);
        //存储到设备
        File uploadParentFile = new File(fileUploadPath);
        fileUploadPath = uploadParentFile.getAbsolutePath();
        String uploadPath = fileUploadPath + "\\" + directory + "\\";
//        System.out.println(fileUploadPath);
        if (!uploadParentFile.exists()) {
            uploadParentFile.mkdir();
        }
        String uuid = IdUtil.fastSimpleUUID();
        String fileUUID = uuid + StrUtil.DOT + type;
        File uploadFile = new File(uploadPath + fileUUID);
        file.transferTo(uploadFile);
        String url = "/resource/" + directory + "/" + fileUUID;
        res.setData(url);
        res.success("上传成功");
        return res;
    }

    @GetMapping("/{directory}/{fileUUID}")
    public void download(@PathVariable String fileUUID, @PathVariable String directory, HttpServletResponse response) throws IOException {
        File uploadFile = new File(fileUploadPath + "\\" + directory + "\\" + fileUUID);
        ServletOutputStream outputStream = response.getOutputStream();
        response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileUUID, "UTF-8"));
        response.setContentType("application/octet-stream");
        outputStream.write(FileUtil.readBytes(uploadFile));
        outputStream.flush();
        outputStream.close();
    }

    //删除文件
    @DeleteMapping("/{directory}/{fileUUIDs}")
    public Result delete(@PathVariable String fileUUIDs, @PathVariable String directory) {
        System.out.println(fileUUIDs);
        Result<String> res = new Result<>();
        String[] fileUUID = fileUUIDs.split(",");
        for (String uuid : fileUUID) {
            File uploadFile = new File(fileUploadPath + "\\" + directory + "\\" + uuid);
            if (uploadFile.exists()) {
                uploadFile.delete();
            }
        }
        res.success("删除成功");
        return res;
    }

}
