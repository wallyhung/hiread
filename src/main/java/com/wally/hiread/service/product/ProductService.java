package com.wally.hiread.service.product;

import com.wally.hiread.dao.product.PractiseMapper;
import com.wally.hiread.dao.product.ProductMapper;
import com.wally.hiread.dao.product.UnitMapper;
import com.wally.hiread.model.product.Product;
import com.wally.hiread.model.product.ProductExample;
import com.wally.hiread.model.product.Unit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import java.util.List;

@SuppressWarnings("SpringJavaAutowiringInspection")
@Service
public class ProductService {
    @Autowired
    private UnitMapper unitMapper;
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private PractiseMapper practiseMapper;
    @Autowired
    private UnitService unitService;
    public Product compute(Product product){
        String videoCoursePrice = product.getVideoCoursePrice();
        String videoDiscountPrice = product.getVideoDiscountPrice();
        String singlePrice=videoCoursePrice;
        if(!StringUtils.isEmpty(videoDiscountPrice)){
            singlePrice=videoDiscountPrice;
        }
        double singlePriceD=0;
        try{
            singlePriceD=Double.parseDouble(singlePrice);
        }catch (Exception ex){

        }
        product.setSinglePriceD(singlePriceD);
        return product;
    }
    public Product load(String id,boolean units){
        Product product = productMapper.selectByPrimaryKey(id);
        if(product==null){
            return null;
        }
        if(units){
            List<Unit> unitList = unitService.listByProductId(id);
            product.setUnits(unitList);
        }
        return compute(product);

    }
    public List<Product> list(){
        ProductExample e=new ProductExample();
        ProductExample.Criteria c = e.createCriteria();
        c.andStatusEqualTo(Product.STATUS_ON_SHELF);
        e.setOrderByClause("dateModified desc");
        List<Product> products = productMapper.selectByExample(e);
        for(Product p:products){
            compute(p);
        }
        return products;

    }

    public List<Product> listByHireadRank(String hireadRank){
        ProductExample e=new ProductExample();
        ProductExample.Criteria c = e.createCriteria();
        c.andStatusEqualTo(Product.STATUS_ON_SHELF);
        c.andHireadRankEqualTo(hireadRank);
        e.setOrderByClause("dateModified desc");
        List<Product> products = productMapper.selectByExample(e);
        return products;

    }

}
