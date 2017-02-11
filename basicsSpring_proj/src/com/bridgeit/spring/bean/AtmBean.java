package com.bridgeit.spring.bean;

public class AtmBean 
{
	public AtmBean() 
	{
		System.out.println(this.getClass().getSimpleName()+" Created");
	}
	
	public void withDraw(int amount)
	{
		System.out.println("Withdraw \t"+ amount);
	}
}
