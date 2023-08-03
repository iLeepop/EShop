package com.ilee.elecshop.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ilee.elecshop.dao.ProductSwiperImageDao;
import com.ilee.elecshop.dao.SmallTypeDao;
import com.ilee.elecshop.pojo.ProductSwiperImage;
import com.ilee.elecshop.pojo.SmallType;
import com.ilee.elecshop.service.IProductSwiperImageService;
import com.ilee.elecshop.service.ISmallTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("productswiperimageservice")
public class IProductSwiperImageServiceImpl extends ServiceImpl<ProductSwiperImageDao, ProductSwiperImage> implements IProductSwiperImageService{

    @Autowired
    private ProductSwiperImageDao productSwiperImageDao;


}
