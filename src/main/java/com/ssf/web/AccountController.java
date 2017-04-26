package com.ssf.web;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.ssf.service.AccountService;

@Controller
public class AccountController {

	//@Autowired
	@Resource(name="r2")//按名称注入
	AccountService accountService = null;// = new AccountServiceImpl();
	
	public void test(){
		accountService.test();
	}
	
}
