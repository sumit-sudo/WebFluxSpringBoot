package com.sumit.mockito_basic.service;

import static org.junit.jupiter.api.Assertions.assertEquals;


import org.junit.jupiter.api.Test;

import com.sumit.mockito_basic.dao.SomeDaoRepo;


class SomeDaoRepoSTUBImpl implements SomeDaoRepo
{
	/*	Disadvantage of STUB implementation
	 * 1) SO if we have 100 methods inside repo interface we will have to create 100
	 *  method here
	 * 2) Suppose there is requirement to add new method inside repo interface then
	 * 	we will have to modify here as well as it is implementing Repo interface.
	 */

	@Override
	public int[] retrieveAllData() {
		// TODO Auto-generated method stub
		return new int[] {10,20,30,40};
	}
	
}
public class SomeServiceTest {

	
//	@BeforeAll
//	public void setUp()
//	{
//		
//	}
	
	
	@Test
	public void testCalculator()
	{
		SomeServiceImpl obj=new SomeServiceImpl();
		int num=obj.calculateSum(new int[] {10,20,30});
		assertEquals( 60,num);
		
		
	}
	@Test
	public void testCalculatorUsingDaoLayer()
	{
		SomeServiceImpl obj=new SomeServiceImpl();
		obj.setDao(new SomeDaoRepoSTUBImpl());
		int sum=obj.calculateSumUsingDataRepo();
		assertEquals(sum, 100);
		
	}
}
