package com.sumit.mockito_basic.service;

import com.sumit.mockito_basic.dao.SomeDaoRepo;

public class SomeServiceImpl {

//	int a[]={10,20};
	
	SomeDaoRepo dao;
	
	
	public void setDao(SomeDaoRepo dao) {
		this.dao = dao;
	}
	
	public int calculateSum(int arr[])
	{
		int sum=0;
		int j=0;
		for(int i:arr)
		{
			sum=sum+i;
		}
		return sum;
	}
	public int calculateSumUsingDataRepo()
	{
		// Data we will get from Dao layer/database
		int sum=0;
		int arr[]=dao.retrieveAllData();
		for(int i:arr)
		{
			sum=sum+i;
		}
		return sum;
	}
}
