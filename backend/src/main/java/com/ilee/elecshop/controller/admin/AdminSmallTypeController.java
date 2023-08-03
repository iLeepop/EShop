package com.ilee.elecshop.controller.admin;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ilee.elecshop.entity.PageBean;
import com.ilee.elecshop.entity.R;
import com.ilee.elecshop.pojo.BigType;
import com.ilee.elecshop.pojo.Order;
import com.ilee.elecshop.pojo.OrderDetail;
import com.ilee.elecshop.pojo.SmallType;
import com.ilee.elecshop.service.IBigTypeService;
import com.ilee.elecshop.service.ISmallTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin/smallType")
public class AdminSmallTypeController {

    @Autowired
    private ISmallTypeService smallTypeService;

    @Autowired
    private IBigTypeService bigTypeService;

    @PostMapping("/list")
    public R list(@RequestBody PageBean pageBean){
        Map<String, Object> map = new HashMap<>();
        map.put("name", pageBean.getQuery().trim());
        map.put("start", pageBean.getStart());
        map.put("pageSize", pageBean.getPageSize());
        List<Order> list =  smallTypeService.getList(map);
        Long total = smallTypeService.getTotal(map);

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("smallList", list);
        resultMap.put("total", total);
        return R.ok(resultMap);
    }

    @GetMapping("/delete/{id}")
    public R delete(@PathVariable(value = "id") Integer id) {
        smallTypeService.removeById(id);
        return R.ok();
    }

    @PostMapping("/save")
    public R save(@RequestBody SmallType smallType){
        smallType.setBigTypeId(smallType.getBigType().getId());
        if(smallType.getId() == null || smallType.getId() == -1){
            smallTypeService.save(smallType);
        }else{
            smallTypeService.saveOrUpdate(smallType);
        }
        return R.ok();
    }

    @GetMapping("/{id}")
    public R findById(@PathVariable(value = "id") Integer id){
        SmallType smallType = smallTypeService.getById(id);
        smallType.setBigType(bigTypeService.getById(smallType.getBigTypeId()));
        Map<String, Object> map = new HashMap<>();
        map.put("smallType", smallType);
        return R.ok(map);
    }

    @GetMapping("/listAll/{bigTypeId}")
    public R listAll(@PathVariable(value = "bigTypeId") Integer bigTypeId){
        Map<String, Object> map = new HashMap<>();
        map.put("smallTypeList", smallTypeService.list(new QueryWrapper<SmallType>()
                .eq("bigTypeId", bigTypeId)));
        return R.ok(map);
    }
}
