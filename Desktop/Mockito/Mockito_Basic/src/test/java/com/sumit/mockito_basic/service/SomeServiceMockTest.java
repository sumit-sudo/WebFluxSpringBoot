package com.sumit.mockito_basic.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;

import com.sumit.mockito_basic.dao.SomeDaoRepo;

public class SomeServiceMockTest {

	
	@Test
	public void calculatorUsingDaoLayer()
	{
		SomeServiceImpl obj=new SomeServiceImpl();
		SomeDaoRepo repo=mock(SomeDaoRepo.class);// We are injecting object to interface
		obj.setDao(repo);
		
		/*
		 * First we need to understand we are not testing SomeDaoRepo, It is already
		 * well tested( example - JPARepositor -> findById(),save() etc )
		 * Here, By using when statement we are also returning value to be return 
		 * from retrieveAllData() method. So without implementing retrieveAllData() method
		 * we can test as well.
		 * 
		 * Note: If we don't write when statement we will get null pointer exception as the
		 * the array will be blank.
		 */
		when(repo.retrieveAllData()).thenReturn(new int[] {10,20,30,40});
		int res=obj.calculateSumUsingDataRepo();
		assertEquals(res, 100);
	}
}
