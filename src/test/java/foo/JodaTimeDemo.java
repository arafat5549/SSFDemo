package foo;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * JodaTime 推荐度5星
 * 好用的日期时间处理包
 * 原版日期太蠢
 * @author wyy
 *
 */
public class JodaTimeDemo {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Test
	public void jodaDemo()
	{
		Calendar calendar = Calendar.getInstance();
		calendar.set(2000, Calendar.JANUARY, 1, 0, 0, 0);
		logger.info(calendar.toString());
		
		DateTime dateTime = new DateTime(2000, 1, 1, 0, 0, 0, 0);
		logger.info(dateTime.toString());
		
		
		//1.向某一个瞬间加上 90 天并输出结果
		logger.info("1.以 JDK 的方式向某一个瞬间加上 90 天并输出结果");
		calendar = Calendar.getInstance();
		calendar.set(2000, Calendar.JANUARY, 1, 0, 0, 0);
		SimpleDateFormat sdf = new SimpleDateFormat("E MM/dd/yyyy HH:mm:ss.SSS");
		calendar.add(Calendar.DAY_OF_MONTH, 90);
		logger.info(sdf.format(calendar.getTime()));
		//
		dateTime = new DateTime(2000, 1, 1, 0, 0, 0, 0);
		logger.info(dateTime.plusDays(90).toString("E MM/dd/yyyy HH:mm:ss.SSS"));
		
		//2.距离 Y2K 45 天之后的某天在下一个月的当前周的最后一天的日期
		dateTime = new DateTime(2000, 1, 1, 0, 0, 0, 0);
		logger.info(dateTime.plusDays(45).plusMonths(1).dayOfWeek().withMaximumValue()
				.toString("E MM/dd/yyyy HH:mm:ss.SSS"));
		
		//清单 6. 使用 Joda 计算日期
		LocalDate now = new LocalDate();
		LocalDate lastDayOfPreviousMonth =
		  now.minusMonths(1).dayOfMonth().withMaximumValue();
		logger.info(lastDayOfPreviousMonth.toString());
	}
}
