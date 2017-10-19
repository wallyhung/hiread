package com.wally.hiread.controller.sys;

import com.wally.hiread.model.sys.SR;
import com.wally.hiread.model.user.SessionOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;


@Controller
@RequestMapping("/sys/session")
public class SessionController {

	//获取SessionOperation
	@RequestMapping(value = "/operation", method = RequestMethod.GET)
	public @ResponseBody
	SR<SessionOperation> operation(HttpSession session){
		SR<SessionOperation> sr=new SR<SessionOperation>();
		SessionOperation operation=(SessionOperation)session.getAttribute(SessionOperation.KEY);
		sr.setStatus(SR.SUCCESS);
		sr.setEntity(operation);
		return sr;
	}

	
}
