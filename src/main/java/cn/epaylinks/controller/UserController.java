package cn.epaylinks.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.epaylinks.model.User;
import cn.epaylinks.service.IUserService;


@Controller
@EnableAutoConfiguration
public class UserController
{
	private Logger logger = Logger.getLogger(UserController.class);
	
	@Autowired
	private IUserService userService;
	
	@RequestMapping("/userlist")
	@ResponseBody
	List<User> userList(){
		logger.info("进入Controller，查询用户list");
		List<User> list = userService.getUserList();
		
		return list;
	}
}
