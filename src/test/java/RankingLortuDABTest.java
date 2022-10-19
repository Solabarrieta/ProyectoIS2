import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import businessLogic.BLFacade;
import dataAccess.DataAccess;
import domain.Registered;

public class RankingLortuDABTest {

	@Mock
	DataAccess dao;
	
	@InjectMocks
	BLFacade sut;
	
	//static DataAccess sut=new DataAccess();

	/*@Test
	public void test1() {
		List<Registered> resultado = sut.rankingLortu();
		
		assertTrue(resultado.size() != 0);
	}*/
	
	@Test
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
	}
}
