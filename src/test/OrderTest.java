package test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.Test;

import com.ssf.model.CartItem;
import com.ssf.model.Order;
import com.ssf.model.Order.OrderStatus;
import com.ssf.model.OrderItem;
import com.ssf.service.OrderItemService;
import com.ssf.service.OrderService;

public class OrderTest {
	OrderService orderService = new OrderService();
	OrderItemService orderItemService= new OrderItemService();
	@Test
	public void orderTest(){
		int userId = 1;
		int orderId = orderService.findMaxId();
		Order order =new Order();
		order.setId(orderId);
		String ordercode = UUID.randomUUID().toString().replace("-", ""); //订单号（唯一,32个字符）
		order.setOrdercode(ordercode);
		order.setStatus(OrderStatus.WAIT_PAY.getValue());
		
		order.setAddress("地址");
		order.setReceiver("王耀");
		order.setMobile("12345678912");
		order.setUserId(userId);
		order.setMessage("--");
		
		OrderItem item = new OrderItem();
		item.setId(orderItemService.findMaxId());
		item.setProductId(1);
		item.setCount(1);
		item.setOrderId(orderId);
		item.setUserId(userId);
		order.getItems().add(item);
		
		List<CartItem> items= new ArrayList<CartItem>();
		String error = orderService.order(order,items);
		System.out.println(error);
		
		//List<Order> orders = orderService.findAllByUserId(1);
		//System.out.println(orders);		
	}
}
