package foo.test;

import java.util.List;
import java.util.Objects;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.google.common.primitives.Ints;

/**
 * 测试Spring框架类
 * @author wyy
 * 2016年10月24日
 *
 */
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations="classpath:spring-mybatis.xml")
public class SpringTest 
{
	private int id;
	public SpringTest(){
		id = 1;
	}
	public enum Weather {
        WINDY,
        RAINY,
        SHINY,
        DOWNPOUR,
        CLOUDY
    };
    
    //前置条件
    public void getSomeSuntan(Weather weather) {
        Preconditions.checkState(weather.equals(Weather.SHINY), "Weather is not the best for a sunbath");
    }

    public void displayFootballTeamMembers(List<String> teamMembers) {
        Preconditions.checkNotNull(teamMembers, "Team can not be null");
        Preconditions.checkArgument(teamMembers.size() == 11, "Full team should consist of 11 players");
    }
	
	public static void main(String[] args) {
		//Sets.newHashSet();
		//new HashSet<E>();
		SpringTest s = new SpringTest();
		s.getSomeSuntan(Weather.SHINY);
		
		List<String> teamMembers = Lists.newArrayList();
		for(int i=0;i<11;i++){
			teamMembers.add("1");
		}
		s.displayFootballTeamMembers(teamMembers);
		
		System.out.println(Objects.toString(s));
		
		//Integer.parseInt("#-1");
		//Integer max = Ints.tryParse(null);
		//System.out.println(max);
		//Objects.equals(a, b)
		//Strings.isNullOrEmpty(string)
		//Strings.is
		
		//int pid = Ints.tryParse(null, 0);
		//System.out.println(pid);
	}
}
