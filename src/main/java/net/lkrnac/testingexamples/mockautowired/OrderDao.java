package net.lkrnac.testingexamples.mockautowired;

import org.springframework.stereotype.Repository;

@Repository
public class OrderDao {
	public Order getOrder(int irderId){
		throw new UnsupportedOperationException("Fail is not mocked!");
	}
}
