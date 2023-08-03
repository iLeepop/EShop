package com.ilee.elecshop.controller.admin;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ilee.elecshop.entity.PageBean;
import com.ilee.elecshop.entity.R;
import com.ilee.elecshop.pojo.BigType;
import com.ilee.elecshop.pojo.OrderDetail;
import com.ilee.elecshop.pojo.SmallType;
import com.ilee.elecshop.service.IBigTypeService;
import com.ilee.elecshop.service.ISmallTypeService;
import com.ilee.elecshop.util.DateUtil;
import com.ilee.elecshop.util.StringUtil;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/admin/bigType")
public class AdminBigTypeController {

    @Autowired
    private IBigTypeService bigTypeService;

    @Autowired
    private ISmallTypeService smallTypeService;

    @Value("${bigTypeImagesFilePath}")
    private String bigTypeImagesFilePath;

    @PostMapping("/list")
    public R list(@RequestBody PageBean pageBean){
        String query = pageBean.getQuery();
        Page<BigType> page = new Page<>(pageBean.getPageNum(), pageBean.getPageSize());
        Page<BigType> bigTypePage = bigTypeService.page(page, new QueryWrapper<BigType>().like(StringUtil.isNotEmpty(query), "name", query));
        Map<String, Object> map = new HashMap<>();
        map.put("bigTypeList", bigTypePage.getRecords());
        map.put("total", bigTypePage.getTotal());
        return R.ok(map);
    }

    @PostMapping("/save")
    public R save(@RequestBody BigType bigType){
        if(bigType.getId() == null || bigType.getId() == -1){
            bigTypeService.save(bigType);
        }else{
            bigTypeService.saveOrUpdate(bigType);
        }
        return R.ok();
    }

    @GetMapping("/{id}")
    public R findById(@PathVariable(value = "id") Integer id){
        BigType bigType = bigTypeService.getById(id);
        Map<String, Object> map = new HashMap<>();
        map.put("bigType", bigType);
        return R.ok(map);
    }

    @GetMapping("/delete/{id}")
    public R delete(@PathVariable(value = "id") Integer id) {
        if (smallTypeService.count(new QueryWrapper<SmallType>().eq("bigTypeId", id)) > 0) {
            return R.error(500, "该大类下有小类，不能删除");
        }else{
            bigTypeService.removeById(id);
            return R.ok();
        }
    }

    @PostMapping("/uploadImage")
    public Map<String, Object> uploadImage(MultipartFile file)throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        if(!file.isEmpty()) {
            String originalFilename = file.getOriginalFilename();
            String suffixName = originalFilename.substring(originalFilename.lastIndexOf("."));
            String newFileName = DateUtil.getCurrentDateStr() + suffixName;
            FileUtils.copyInputStreamToFile(file.getInputStream(), new File(bigTypeImagesFilePath + newFileName));
            resultMap.put("code", 0);
            resultMap.put("msg", "上传成功");
            Map<String, Object> dataMap = new HashMap<>();
            dataMap.put("title", newFileName);
            dataMap.put("src", "/image/bigtype/" + newFileName);
            resultMap.put("data", dataMap);
        }
        return resultMap;
    }

    @GetMapping("/listAll")
    public R listAll() {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("bigTypeList", bigTypeService.list());
        return R.ok(resultMap);
    }
}
