package net.lkrnac.testingexamples.mockautowired;

import java.util.Arrays;

import junit.framework.Assert;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class OrderServiceTest {
	private static final int TEST_ORDER_ID = 15;
	private static final int TEST_SHOES_PRICE = 2;   
	private static final int TEST_SHIRT_PRICE = 1;
	
	@InjectMocks
	private OrderService testingObject;
	
	@Spy
	private PriceService priceService;
	
	@Mock
	private OrderDao orderDao;
	
	@BeforeMethod
	public void initMocks(){
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testGetOrderService(){
		Order order = new Order(Arrays.asList(Item.SHOES, Item.SHIRT));
		Mockito.when(orderDao.getOrder(TEST_ORDER_ID)).thenReturn(order);
		
		//notice different Mockito syntax for spy
		Mockito.doReturn(TEST_SHIRT_PRICE).when(priceService).getActualPrice(Item.SHIRT);
		Mockito.doReturn(TEST_SHOES_PRICE).when(priceService).getActualPrice(Item.SHOES);
		
		//call testing method
		int actualOrderPrice = testingObject.getOrderPrice(TEST_ORDER_ID);
		
		Assert.assertEquals(TEST_SHIRT_PRICE + TEST_SHOES_PRICE, actualOrderPrice);
	}
}
