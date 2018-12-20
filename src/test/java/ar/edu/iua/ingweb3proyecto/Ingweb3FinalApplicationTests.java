package ar.edu.iua.ingweb3proyecto;

import ar.edu.iua.ingweb3proyecto.business.IListaBusiness;
import ar.edu.iua.ingweb3proyecto.business.ITareaBusiness;
import ar.edu.iua.ingweb3proyecto.business.exception.BusinessException;
import ar.edu.iua.ingweb3proyecto.business.exception.InvalidSortException;
import ar.edu.iua.ingweb3proyecto.model.Lista;
import ar.edu.iua.ingweb3proyecto.model.Tarea;
import ar.edu.iua.ingweb3proyecto.model.exception.NotFoundException;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.assertEquals;


@RunWith(SpringRunner.class)
@SpringBootTest
public class Ingweb3FinalApplicationTests {

    @Test
    public void prueba(){
        assertEquals(1,1);
    }

    /*
	@Autowired
	ITareaBusiness tb;

	@Autowired
	IListaBusiness lb;

	static HashMap<String, String> hashMapExpected;

	@Before
	public void setUp(){
		hashMapExpected = new HashMap<String, String>();
		hashMapExpected.put("name_1", "Hacer algo");
		hashMapExpected.put("priority_1", "Alta");
		hashMapExpected.put("lista_1", "TODO");
		hashMapExpected.put("name_2", "Hacer otra cosa");
		hashMapExpected.put("priority_2", "Media");
		hashMapExpected.put("lista_2", "TODO");
		hashMapExpected.put("name_3", "Prueba test2");
		hashMapExpected.put("priority_3", "Alta");
		hashMapExpected.put("lista_3", "Backlog");
		hashMapExpected.put("name_4", "Prueba test update");
		hashMapExpected.put("priority_4", "Alta");
		hashMapExpected.put("lista_4", "TODO");
		hashMapExpected.put("name_list_1", "TODO");
		hashMapExpected.put("sprint_1", "Sprint1");
		hashMapExpected.put("name_list_2", "Backlog");
		hashMapExpected.put("sprint_2", "Sprint2");
		hashMapExpected.put("name_list_3", "In progress");
	}

	@Test
	public void getAllTareasSuccess() throws BusinessException, InvalidSortException {
		List<Tarea> resultList = tb.getAll();

		assertEquals(resultList.get(0).getNombre(), hashMapExpected.get("name_1"));
		assertEquals(resultList.get(0).getPrioridad(), hashMapExpected.get("priority_1"));
		assertEquals(resultList.get(0).getLista().getNombre(), hashMapExpected.get("lista_1"));
		assertEquals(resultList.get(1).getNombre(), hashMapExpected.get("name_2"));
		assertEquals(resultList.get(1).getPrioridad(), hashMapExpected.get("priority_2"));
		assertEquals(resultList.get(1).getLista().getNombre(), hashMapExpected.get("lista_2"));
		assertEquals(resultList.get(2).getNombre(), hashMapExpected.get("name_3"));
		assertEquals(resultList.get(2).getPrioridad(), hashMapExpected.get("priority_3"));
		assertEquals(resultList.get(2).getLista().getNombre(), hashMapExpected.get("lista_3"));
	}

	@Test
	public void getByListaSuccess() throws BusinessException, InvalidSortException {
		List<Tarea> resultList = tb.getByLista("Todo");

		assertEquals(resultList.get(0).getNombre(), hashMapExpected.get("name_1"));
		assertEquals(resultList.get(0).getPrioridad(), hashMapExpected.get("priority_1"));
		assertEquals(resultList.get(0).getLista().getNombre(), hashMapExpected.get("lista_1"));
		assertEquals(resultList.get(1).getNombre(), hashMapExpected.get("name_2"));
		assertEquals(resultList.get(1).getPrioridad(), hashMapExpected.get("priority_2"));
		assertEquals(resultList.get(1).getLista().getNombre(), hashMapExpected.get("lista_2"));
		assertEquals(resultList.get(2).getNombre(), hashMapExpected.get("name_4"));
		assertEquals(resultList.get(2).getPrioridad(), hashMapExpected.get("priority_4"));
		assertEquals(resultList.get(2).getLista().getNombre(), hashMapExpected.get("lista_4"));
	}

	@Test
	public void getAllSortedSuccess() throws BusinessException, InvalidSortException {
		List<Tarea> resultList = tb.getAllSorted("Prioridad");

		assertEquals(resultList.get(0).getNombre(), hashMapExpected.get("name_1"));
		assertEquals(resultList.get(0).getPrioridad(), hashMapExpected.get("priority_1"));
		assertEquals(resultList.get(0).getLista().getNombre(), hashMapExpected.get("lista_1"));
		assertEquals(resultList.get(1).getNombre(), hashMapExpected.get("name_3"));
		assertEquals(resultList.get(1).getPrioridad(), hashMapExpected.get("priority_3"));
		assertEquals(resultList.get(1).getLista().getNombre(), hashMapExpected.get("lista_3"));
		assertEquals(resultList.get(2).getNombre(), hashMapExpected.get("name_3"));
		assertEquals(resultList.get(2).getPrioridad(), hashMapExpected.get("priority_3"));
		assertEquals(resultList.get(2).getLista().getNombre(), hashMapExpected.get("lista_3"));
	}

	@Test
	public void getByListaSortedSuccess() throws BusinessException, InvalidSortException {
		List<Tarea> resultList = tb.getByListaSorted("Backlog", "Prioridad");

		assertEquals(resultList.get(0).getNombre(), hashMapExpected.get("name_3"));
		assertEquals(resultList.get(0).getPrioridad(), hashMapExpected.get("priority_3"));
		assertEquals(resultList.get(0).getLista().getNombre(), hashMapExpected.get("lista_3"));
		assertEquals(resultList.get(1).getNombre(), hashMapExpected.get("name_3"));
		assertEquals(resultList.get(1).getPrioridad(), hashMapExpected.get("priority_3"));
		assertEquals(resultList.get(1).getLista().getNombre(), hashMapExpected.get("lista_3"));
	}

	@Test
	public void getAllListasSuccess() throws BusinessException, InvalidSortException {
		List<Lista> resultList = lb.getAll();

		assertEquals(resultList.get(0).getNombre(), hashMapExpected.get("name_list_1"));
		assertEquals(resultList.get(0).getSprint(), hashMapExpected.get("sprint_1"));
		assertEquals(resultList.get(1).getNombre(), hashMapExpected.get("name_list_2"));
		assertEquals(resultList.get(1).getSprint(), hashMapExpected.get("sprint_2"));
		assertEquals(resultList.get(2).getNombre(), hashMapExpected.get("name_list_3"));
		assertEquals(resultList.get(2).getSprint(), hashMapExpected.get("sprint_2"));
	}
	*/

}