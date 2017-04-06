package com.ssf.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 自定义注解的验证规则
 * @author wyy
 * 2017年4月6日
 *
 */
public class Not999Validator implements ConstraintValidator<Not999, String> {

	@Override
	public void initialize(Not999 arg0) {
		
	}

	@Override
	public boolean isValid(String vaule, ConstraintValidatorContext context) {
		if(vaule.equals("999")){
			return false;
		}else{
			return true;
		}
	}



}
