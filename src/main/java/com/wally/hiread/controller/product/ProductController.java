package com.wally.hiread.controller.product;

import com.wally.hiread.dao.product.TeacherCommentMapper;
import com.wally.hiread.dao.product.UserProductMapper;
import com.wally.hiread.model.product.PractiseUser;
import com.wally.hiread.model.product.Product;
import com.wally.hiread.model.product.TeacherComment;
import com.wally.hiread.model.product.UserProduct;
import com.wally.hiread.model.sys.SR;
import com.wally.hiread.model.user.User;
import com.wally.hiread.service.product.ProductService;
import com.wally.hiread.service.product.TeacherCommentService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@SuppressWarnings("SpringJavaAutowiringInspection")
@Controller
@RequestMapping("/product/product")
public class ProductController {
	private Logger log = Logger.getLogger(this.getClass());
	@Autowired
	TeacherCommentService tcService;
	@Autowired
	ProductService prodService;
	@Autowired
	UserProductMapper upMapper;
	@Autowired
	private TeacherCommentMapper tcMapper;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String index() {
		return "product/product";
	}

	@RequestMapping(value = "/load", method = RequestMethod.GET)
	public @ResponseBody
	SR<Product> load(String id){
		SR<Product> sr=new SR<Product>();
		Product product = prodService.load(id,false);
		sr.setEntity(product);
		sr.setStatus(SR.SUCCESS);
		return sr;
	}

	@RequestMapping(value = "/onlineComment/myOnlinePractises", method = RequestMethod.GET)
	public @ResponseBody
	SR<List<PractiseUser>> teacherComments(String userProductId){
		SR<List<PractiseUser>> sr=new SR<List<PractiseUser>>();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if(auth instanceof AnonymousAuthenticationToken){
			sr.setMsg("Please login");
			sr.setFailSubTye(SR.FAIL_TYPE_BIZ_AUTH_FAIL);
			return sr;
		}
		User user=(User)auth.getDetails();
		String userId=user.getId();
		UserProduct userProduct = upMapper.selectByPrimaryKey(userProductId);
		if(userProduct==null||!userProduct.getUserId().equals(userId)){
			sr.setMsg("request illegal");
			return sr;
		}
		List<PractiseUser> practiseUsers = tcService.myOnlinePractises(userProduct);
		sr.setEntity(practiseUsers);
		sr.setStatus(SR.SUCCESS);
		return sr;
	}
	@RequestMapping(value = "/onlineComment/hasRead", method = RequestMethod.POST)
	public @ResponseBody
	SR hasRead(String teacherCommentId){
		SR sr=new SR();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if(auth instanceof AnonymousAuthenticationToken){
			sr.setMsg("Please login");
			sr.setFailSubTye(SR.FAIL_TYPE_BIZ_AUTH_FAIL);
			return sr;
		}
		User user=(User)auth.getDetails();
		String userId=user.getId();
		TeacherComment teacherComment = tcMapper.selectByPrimaryKey(teacherCommentId);
		teacherComment.setHasRead("1");
		tcMapper.updateByPrimaryKey(teacherComment);
		sr.setStatus(SR.SUCCESS);
		return sr;
	}


}
