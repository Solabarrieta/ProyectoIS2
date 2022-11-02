import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import businessLogic.BLFacade;
import dataAccess.DataAccess;
import domain.Registered;
import test.dataAccess.TestDataAccess;

public class RankingLortuDABTest {
	protected static EntityManager  db;
	
	DataAccess dao = new DataAccess();
	
	TestDataAccess testDa = new TestDataAccess();
	
	//static DataAccess sut=new DataAccess();

	/*@Test
	public void test1() {
		List<Registered> resultado = sut.rankingLortu();
		
		assertTrue(resultado.size() != 0);
	}*/
	
	/*@Test
	public void test2() {

		try {
			Mockito.doReturn(true)
			 .when(dao)
			 .rankingLortu();
			
			List<Registered> obtenido = sut.rankingLortu();
			List<Registered> esperado = null;
			assertEquals(esperado, obtenido);
			System.out.println(obtenido);
			
		}catch (Exception e){
			e.printStackTrace();
		}
	}*/
	
	@Test
	public void registeredNullTest() {
		System.out.println("Test registered null");
		
		/*List<Registered> expected = new ArrayList<Registered>();
		Registered reg1 =new Registered("registered", "123", 1234);
		Registered reg2 = new Registered("andrea", "123", 1111);
		Registered reg3 = new Registered("markel", "123", 1111);
		Registered reg4 = new Registered("mikel", "123", 1111);
		expected.add(reg1);
		expected.add(reg4);
		expected.add(reg3);
		expected.add(reg2);*/
		
		List<Registered> obtained = dao.rankingLortu();
		
		assertEquals(null, obtained);
		
	}
	
	@Test
	public void registeredNotNullTest() {
		System.out.println("Test registered not null");

		List<Registered> obtained = dao.rankingLortu();
		
		assertTrue(obtained.size()>0);
		
	}
}
