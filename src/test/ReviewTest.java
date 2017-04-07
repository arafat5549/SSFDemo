package test;

import org.junit.Test;

import com.ssf.service.ReviewService;

public class ReviewTest {

	ReviewService reviewService = new ReviewService();
	
	@Test
	public void baseTest(){
		int pid = 87;
		reviewService.findOrderReviewListByProductId(pid, 1);
	}
}
