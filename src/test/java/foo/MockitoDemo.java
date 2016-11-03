package foo;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import foo.dao.UserDao;
import foo.service.impl.UserServiceImpl;

/**
 * Mockito 推荐度5星<p>
 * 
 * 测试真实环境
 * 模拟真实环境的Mock包
 * @author wyy
 *
 */
public class MockitoDemo 
{
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Mock
    private UserDao userDAO;
    private UserServiceImpl userService;
    @Before
    public void setUp() throws Exception
    {
        //MockitoAnnotations.initMocks( this );
        //userService = new UserServiceImpl();
        //userService.setUserDAO(userDAO);
    }
	
	@Test
	public void MockitoWebTest()
	{
		List mockedList = mock(List.class);
		when(mockedList.get(0)).thenReturn("first");
		System.out.println(mockedList.get(0));
		
//        User person = new User("Phillip" ,"1","123",1);
//        when( userDAO.fetchPerson(1) ).thenReturn( person );
//        boolean updated = userService.update( 1, "David" );
//        assertTrue( updated );
        //logger.info(person.toString());
//        verify( userDAO ).fetchPerson( 1 );
//        ArgumentCaptor<User> personCaptor = ArgumentCaptor.forClass( User.class );
//        verify( userDAO ).update( personCaptor.capture() );
//        User updatedPerson = personCaptor.getValue();
//        assertEquals( "David", updatedPerson.getPersonName() );
//        // asserts that during the test, there are no other calls to the mock object.
//        verifyNoMoreInteractions( userDAO );
	}
	
	@Test
	public void MockitoTest()
	{
//		 //mock creation
//		 List<Object> mockedList = mock(List.class);
//		 //using mock object
//		 mockedList.add("one");
//		 logger.info(mockedList.toString());
//		 mockedList.clear();
//		 //verification
//		 verify(mockedList).add("one");
//		 verify(mockedList).clear();
		 
//		 //2.
//		 //You can mock concrete classes, not just interfaces
		 LinkedList mockedList = mock(LinkedList.class);
//		 //stubbing
//		 when(mockedList.get(0)).thenReturn("first");
//		 when(mockedList.get(1)).thenThrow(new RuntimeException());
//		 //following prints "first"
//		 System.out.println(mockedList.get(0));
//		 //following throws runtime exception
//		 System.out.println(mockedList.get(1));
//		 //following prints "null" because get(999) was not stubbed
//		 System.out.println(mockedList.get(999));
//
//		 //Although it is possible to verify a stubbed invocation, usually it's just redundant
//		 //If your code cares what get(0) returns, then something else breaks (often even before verify() gets executed).
//		 //If your code doesn't care what get(0) returns, then it should not be stubbed. Not convinced? See here.
//		 verify(mockedList).get(0);
		 
		 //3.
		 //stubbing using built-in anyInt() argument matcher
		 when(mockedList.get(Mockito.anyInt())).thenReturn("element");
		 //stubbing using custom matcher (let's say isValid() returns your own matcher implementation):
		 //when(mockedList.contains(argThat(isValid()))).thenReturn("element");
		 //following prints "element"
		 System.out.println(mockedList.get(999));
		 //you can also verify using an argument matcher
		 verify(mockedList).get(Mockito.anyInt());
		 //argument matchers can also be written as Java 8 Lambdas
		 //verify(mockedList).add(Mockito.anyString() -> Mockito.anyString().length() > 5);
		 
		 //4.
//		 verify(mock).someMethod(Mockito.anyInt(), Mockito.anyString(), Mockito.eq("third argument"));
//		 //above is correct - eq() is also an argument matcher
//		 verify(mock).someMethod(Mockito.anyInt(), Mockito.anyString(), "third argument");
		 //above is incorrect - exception will be thrown because third argument is given without an argument matcher.
	}
}
