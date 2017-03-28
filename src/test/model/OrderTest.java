package test.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.Test;

import com.ssf.model.CartItem;
import com.ssf.model.Order;
import com.ssf.model.Order.orderStatus;
import com.ssf.service.OrderService;

public class OrderTest {
	OrderService orderService = new OrderService();
	@Test
	public void orderTest(){
//		int userId = 1;
//		int orderId = orderService.findMaxCount();
//		Order order =new Order();
//		order.setId(orderId);
//		String ordercode = UUID.randomUUID().toString().replace("-", ""); //订单号（唯一,32个字符）
//		order.setOrdercode(ordercode);
//		order.setStatus(orderStatus.STATUS_WAITPAY.getValue());
//		
//		order.setAddress("地址");
//		order.setReceiver("王耀");
//		order.setMobile("12345678912");
//		order.setUserId(userId);
//		
//		List<CartItem> items= new ArrayList<CartItem>();
//		
//		
//		String error = orderService.order(items,order);
//		System.out.println(error);
		
//		List<Order> orders = orderService.findAllByUserId(1);
//		System.out.println(orders);
		
		Order order = orderService.findById(1);
		String error = orderService.pay(order);
		System.out.println(error);
	}
}
