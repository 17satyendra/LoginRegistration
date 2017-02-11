package com.bridgeit.spring.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.bridgeit.spring.bean.AtmBean;

public class Tester {

	public static void main(String[] args) 
	{
		//AtmBean atm = new AtmBean();
		//atm.withDraw(5000);
		
		String xmlFileName= "context.xml";
		
		ApplicationContext spring = new ClassPathXmlApplicationContext(xmlFileName);
		
		AtmBean atm =spring.getBean(AtmBean.class);
		atm.withDraw(5000);
		
	}
}
