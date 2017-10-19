package com.wally.hiread.service.order;

import com.wally.hiread.dao.product.PractiseMapper;
import com.wally.hiread.dao.product.ProductMapper;
import com.wally.hiread.dao.product.UnitMapper;
import com.wally.hiread.model.order.ShoppingCart;
import com.wally.hiread.model.order.ShoppingCartSubmit;
import com.wally.hiread.model.product.Product;
import com.wally.hiread.model.sys.Setup;
import com.wally.hiread.service.product.PractiseOptService;
import com.wally.hiread.service.product.PractiseUserService;
import com.wally.hiread.service.product.ProductService;
import com.wally.hiread.service.sys.SetupService;
import com.wally.hiread.setting.sys.AppCons;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@SuppressWarnings("SpringJavaAutowiringInspection")
@Service
public class ShoppingCartService {
    @Autowired
    private UnitMapper unitMapper;
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private PractiseMapper practiseMapper;
    @Autowired
    private PractiseOptService practiseOptService;
    @Autowired
    private ProductService productService;
    @Autowired
    private PractiseUserService puService;
    @Autowired
    private SetupService setupService;
    private static final String MY_CART="my_cart";
    private static final String MY_CART_SUBMIT="my_cart_submit";

    public List<ShoppingCart> list(HttpSession session){
        List<ShoppingCart> list = (List<ShoppingCart>)session.getAttribute(MY_CART);
        return list;
    }

    public List<ShoppingCart> add(HttpSession session, String productId){
        ShoppingCart cart=new ShoppingCart();
        Date now=new Date();
        SimpleDateFormat dtf=new SimpleDateFormat(AppCons.DATETIME_FORMAT);
        cart.setId(UUID.randomUUID().toString());
        cart.setDatecreated(now);
        cart.setDatemodified(now);
        cart.setProductId(productId);
        Product product = productService.load(productId, false);
        cart.setProduct(product);

        List<ShoppingCart> list = this.list(session);
        if(list==null){
            list=new ArrayList<ShoppingCart>();
            session.setAttribute(MY_CART,list);
        }
        list.add(cart);
        return list;
    }
    public List<ShoppingCart> remove(HttpSession session, String cartId){
        List<ShoppingCart> result=new ArrayList<ShoppingCart>();
        List<ShoppingCart> list = this.list(session);
        for(ShoppingCart cart:list){
            if(!cart.getId().equals(cartId)){
                result.add(cart);
            }
        }
        session.setAttribute(MY_CART,result);
        return result;
    }

    public boolean has(HttpSession session,String productId){
        List<ShoppingCart> list = this.list(session);
        if(list==null){
            return false;
        }
        for(ShoppingCart sc:list){
            if(sc.getProductId().equals(productId)){
                return true;
            }
        }
        return false;
    }

    public void empty(HttpSession session){
        session.removeAttribute(MY_CART);
    }

    public boolean validSelectedList(HttpSession session,List<ShoppingCart> selectedList){
        List<ShoppingCart> list = this.list(session);
        for(ShoppingCart s:selectedList){
            String sid = s.getId();
            boolean hasSid=false;
            for(ShoppingCart c:list){
                if(c.getId().equals(sid)){
                    hasSid=true;
                    break;
                }
            }
            if(!hasSid){
                return false;
            }
        }
        return true;

    }
    public double calculateTotal(HttpSession session,
                                  List<ShoppingCart> selectedList,
                                  double couponPrice,
                                  int usePoint){
        double total=0;
        Setup setup = setupService.loadSetup();
        String compoundRate="1";
        if(selectedList.size()==2){
            compoundRate=setup.getDoubleItemDiscount();
        }else if(selectedList.size()>2){
            compoundRate=setup.getTripleItemDiscount();
        }
        double compoundRateD=1;
        try {
            compoundRateD=Double.parseDouble(compoundRate);
        }catch (Exception e){}
        double compoundPriceSumD=0;
        for(ShoppingCart c:selectedList){
            Product product = c.getProduct();
            product = productService.compute(product);
            double singlePriceD = product.getSinglePriceD();
            double compoundPriceD=compoundRateD*singlePriceD;
            compoundPriceSumD+=compoundPriceD;
        }

        String userPointExchangeRate = setup.getUserPointExchangeRate();
        double userPointExchangeRateD=0.1;
        try {
            userPointExchangeRateD=Double.parseDouble(userPointExchangeRate);
        }catch (Exception e){}
        double userPointPriceD=new BigDecimal(userPointExchangeRateD*usePoint)
                .setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
        couponPrice=new BigDecimal(couponPrice)
                .setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();

        total=compoundPriceSumD-userPointPriceD-couponPrice;
        total=new BigDecimal(total)
                .setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
        return total;

    }

    public void cartSubmit(HttpSession session,ShoppingCartSubmit submit){
        session.setAttribute(MY_CART_SUBMIT,submit);
    }

    public ShoppingCartSubmit getCartSubmit(HttpSession session){
        return (ShoppingCartSubmit)session.getAttribute(MY_CART_SUBMIT);
    }

    public void removeSelected(HttpSession session){
        ShoppingCartSubmit cartSubmit = getCartSubmit(session);
        List<ShoppingCart> cartSelectedList = cartSubmit.getCartSelectedList();
        for(ShoppingCart cart:cartSelectedList){
            this.remove(session,cart.getId());
        }
    }



}
