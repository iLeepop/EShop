package com.ilee.elecshop.controller.admin;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ilee.elecshop.entity.PageBean;
import com.ilee.elecshop.entity.R;
import com.ilee.elecshop.pojo.BigType;
import com.ilee.elecshop.pojo.OrderDetail;
import com.ilee.elecshop.pojo.Product;
import com.ilee.elecshop.service.IProductService;
import com.ilee.elecshop.service.ISmallTypeService;
import com.ilee.elecshop.util.DateUtil;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin/product")
public class AdminProductController {

    @Autowired
    private IProductService productService;

    @Autowired
    private ISmallTypeService smallTypeService;

    @Value("${productImagesFilePath}")
    private String productImagesFilePath;

    @Value("${swiperImagesFilePath}")
    private String swiperImagesFilePath;

    @PostMapping("/list")
    public R list(@RequestBody PageBean pageBean) {
        Map<String, Object> map = new HashMap<>();
        map.put("name", pageBean.getQuery().trim());
        map.put("start",pageBean.getStart());
        map.put("pageSize", pageBean.getPageSize());
        List<Product> list = productService.getList(map);
        Long total = productService.getTotal(map);
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("productList", list);
        resultMap.put("total", total);
        return R.ok(resultMap);
    }

    @GetMapping("/delete/{id}")
    public R delete(@PathVariable(value = "id") Integer id) {
        productService.removeById(id);
        return R.ok();
    }

    @GetMapping("/updateHot/{id}/state/{hot}")
    public R updateHot(@PathVariable(value = "id") Integer id,
                       @PathVariable(value = "hot") Boolean hot) {
        Product product = productService.getById(id);
        product.setHot(hot);
        if(hot) {
            product.setHotDateTime(new Date());
        }else {
            product.setHotDateTime(null);
        }
        productService.saveOrUpdate(product);
        return R.ok();
    }

    @GetMapping("/updateSwiper/{id}/state/{swiper}")
    public R updateSwiper(@PathVariable(value = "id") Integer id,
                          @PathVariable(value = "swiper") Boolean swiper) {
        Product product = productService.getById(id);
        product.setSwiper(swiper);
        productService.saveOrUpdate(product);
        return R.ok();
    }

    @PostMapping("/uploadImage")
    public Map<String, Object> uploadImage(MultipartFile file)throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        if(!file.isEmpty()) {
            String originalFilename = file.getOriginalFilename();
            String suffixName = originalFilename.substring(originalFilename.lastIndexOf("."));
            String newFileName = DateUtil.getCurrentDateStr() + suffixName;
            FileUtils.copyInputStreamToFile(file.getInputStream(), new File(productImagesFilePath + newFileName));
            resultMap.put("code", 0);
            resultMap.put("msg", "上传成功");
            Map<String, Object> dataMap = new HashMap<>();
            dataMap.put("title", newFileName);
            dataMap.put("src", "/image/hotImage/" + newFileName);
            resultMap.put("data", dataMap);
        }
        return resultMap;
    }

    @PostMapping("/save")
    public R save(@RequestBody Product product){
        System.out.println(product);
        if(product.getId() == null || product.getId() == -1){
            productService.add(product);
        }else{
            productService.edit(product);
        }
        return R.ok();
    }

    @RequestMapping("/uploadSwiperImage")
    public Map<String,Object> uploadSwiperImage(MultipartFile file)throws Exception{
        Map<String,Object> resultMap=new HashMap<>();
        if(!file.isEmpty()){
            String originalFilename = file.getOriginalFilename();
            String suffixName = originalFilename.substring(originalFilename.lastIndexOf("."));
            String newFileName=DateUtil.getCurrentDateStr()+suffixName;
            FileUtils.copyInputStreamToFile(file.getInputStream(),new File(swiperImagesFilePath+newFileName));
            resultMap.put("code",0);
            resultMap.put("msg","上传成功");
            Map<String,Object> dataMap=new HashMap<>();
            dataMap.put("title",newFileName);
            dataMap.put("src","/image/swiper/"+newFileName);
            resultMap.put("data",dataMap);
        }
        return resultMap;
    }

    @GetMapping("/{id}")
    public R getById(@PathVariable(value = "id") Integer id){
        Product product = productService.one(id);
        Map<String, Object> map = new HashMap<>();
        map.put("product", product);
        return R.ok(map);
    }
}
