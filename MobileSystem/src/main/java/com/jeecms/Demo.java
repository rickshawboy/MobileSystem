package com.jeecms;

import java.util.Iterator;

public class Demo implements Iterable<Demo>{
	
	private static Demo demo = new Demo();
	
	
	public static synchronized Demo getInstance() {
		return demo;
	}
	
	
	public static void main(String[] args) {
		
		
		
	}


	@Override
	public Iterator<Demo> iterator() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
