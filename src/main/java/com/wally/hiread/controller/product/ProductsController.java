package com.wally.hiread.controller.product;

import com.wally.hiread.dao.product.UnitMapper;
import com.wally.hiread.model.product.Product;
import com.wally.hiread.model.sys.SR;
import com.wally.hiread.service.product.PractiseService;
import com.wally.hiread.service.product.PractiseUserService;
import com.wally.hiread.service.product.ProductService;
import com.wally.hiread.service.product.UnitService;
import com.wally.hiread.service.sys.FileService;
import com.wally.hiread.service.sys.LogService;
import com.wally.hiread.service.user.LoginService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@SuppressWarnings("SpringJavaAutowiringInspection")
@Controller
@RequestMapping("/product/products")
public class ProductsController {
	private Logger log = Logger.getLogger(this.getClass());
	@Autowired
	UnitMapper unitMapper;
	@Autowired
	FileService fileService;
	@Autowired
	LogService logService;
	@Autowired
	UnitService unitService;
	@Autowired
	PractiseUserService puService;
	@Autowired
	PractiseService pService;
	@Autowired
	LoginService lService;
	@Autowired
	PractiseService practiseService;
	@Autowired
	ProductService prodService;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String index() {
		return "product/products";
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public @ResponseBody
	SR<List<Product>> list(){
		SR<List<Product>> sr=new SR<List<Product>>();
		List<Product> products = prodService.list();
		sr.setEntity(products);
		sr.setStatus(SR.SUCCESS);
		return sr;
	}


}
