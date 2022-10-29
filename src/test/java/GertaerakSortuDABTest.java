import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import dataAccess.DataAccess;
import domain.Event;
import domain.Team;
import test.dataAccess.TestDataAccess;

public class GertaerakSortuDABTest {
	protected static EntityManager  db;
	
	DataAccess dao = new DataAccess();
	
	TestDataAccess testDa = new TestDataAccess();
	
	//Clases de equivalencia válidas
	
	/*
	 * El evento existe en la base de datos
	 * El evento no se inserta
	 * La función devuelve false
	 */

	@Test
	public void test1() { 
		System.out.println("Test 1");
		String description = "Athletic-Real Sociedad";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date eventDate=null;
		try {
			eventDate = sdf.parse("31/10/2022");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String sport = "Futbol";
		//boolean expected = false;
		boolean obtained = dao.gertaerakSortu(description, eventDate, sport);
		assertFalse(obtained);
	}
	
	
	/*
	 * El evento no existe en la base de datos
	 * El evento se inserta
	 * La función devuelve true
	 */
	@Test
	public void test2() { 
		System.out.println("Test 2");
		String description = "Barcelona-Real Sociedad";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date eventDate=null;
		try {
			eventDate = sdf.parse("31/10/2022");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String sport = "Futbol";
		testDa.open();
		boolean obtained = dao.gertaerakSortu(description, eventDate, sport);
		assertTrue(obtained);
		testDa.removeEvent(description);
		testDa.close();
	}
	
	
	
	//Clases de equivalencia No válidas
	
	@Test
	public void descriptionNullTest() {
		System.out.println("Test description null");
		String description = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date eventDate=null;
		try {
			eventDate = sdf.parse("31/10/2022");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String sport = "Futbol";
		try {
			dao.gertaerakSortu(description, eventDate, sport);
			fail("Description null");
		}catch(Exception e) {
			e.printStackTrace();
			assertEquals(e.getClass(), java.lang.NullPointerException.class);
		}
		
	}
	
	/*
	 * La base de datos permite fechas nulas. Comparar un null pointer exception, porque se guarda bien en la bd cuando la fecha es nula.
	 
	@Test
	public void eventDateNullTest() {
		System.out.println("Test event date null");
		String description = "Athletic-Real";
		Date eventDate = null;
		String sport = "Futbol";
		try {
			dao.gertaerakSortu(description, eventDate, sport);
			//fail("Event Date null");
		}catch(Exception e) {
			e.printStackTrace();
			assertEquals(e.getClass(), java.lang.NullPointerException.class);
		}
		
	}*/
	
	@Test
	public void sportNullTest() {
		System.out.println("Test sport null");
		String description = "Athletic-Real";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date eventDate=null;
		try {
			eventDate = sdf.parse("31/10/2022");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String sport = null;
		try {
			dao.gertaerakSortu(description, eventDate, sport);
			fail("Sport null");
		}catch(Exception e) {
			e.printStackTrace();
			assertEquals(e.getClass(), java.lang.IllegalArgumentException.class);
		}
		
	}
	
	@Test
	public void eventDateLessThanTodayTest() {
		System.out.println("Test event date before today");
		String description = "Real-Barcelona";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date eventDate=null;
		try {
			eventDate = sdf.parse("28/10/2022");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String sport = "Futbol";
		testDa.open();
		boolean obtained = dao.gertaerakSortu(description, eventDate, sport);
		assertTrue(obtained);
		testDa.removeEvent(description);
		testDa.close();
		
	}
	
	@Test
	public void sportNotInSportClassTest() {
		System.out.println("Test sport not in sport class");
		String description = "Athletic-Real";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date eventDate=null;
		try {
			eventDate = sdf.parse("31/10/2022");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String sport = "patata";
		//boolean expected = false;
		boolean obtained = dao.gertaerakSortu(description, eventDate, sport);
		assertFalse(obtained);
	}


}
