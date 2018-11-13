package ar.edu.iua.ingweb3final;

/*import org.junit.Test;*/
import ar.edu.iua.ingweb3proyecto.business.ITareaBusiness;
import ar.edu.iua.ingweb3proyecto.business.exception.BusinessException;
import ar.edu.iua.ingweb3proyecto.business.impl.TareaBusiness;
import ar.edu.iua.ingweb3proyecto.model.Tarea;
import ar.edu.iua.ingweb3proyecto.web.services.TareasRESTController;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.HashMap;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Ingweb3FinalApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Test
	void getAlgo() {
		assertEquals(1, 1);
	}

	private TareaBusiness tb = new TareaBusiness();
	private TareasRESTController trc = new TareasRESTController();

	static HashMap<String, String> hashMapExpected;

	@BeforeAll
	static void setUp(){
		hashMapExpected = new HashMap<String, String>();
		hashMapExpected.put("name_1", "Hacer algo");
		hashMapExpected.put("priority_1", "Alta");
		hashMapExpected.put("list_1", "TODO");
		hashMapExpected.put("name_2", "Prueba test2");
		hashMapExpected.put("priority_2", "Backlog");
		hashMapExpected.put("list_2", "TODO");
	}

	@Test
	void getTareasOrdenadasPorPrioridad() throws BusinessException {
		List<Tarea> resultList = tb.getAll();

		assertEquals(resultList.get(0).getNombre(), hashMapExpected.get("name_1"));
		assertEquals(resultList.get(0).getPrioridad(), hashMapExpected.get("priority_1"));
		assertEquals(resultList.get(0).getLista().getNombre(), hashMapExpected.get("list_1"));
		assertEquals(resultList.get(1).getNombre(), hashMapExpected.get("name_2"));
		assertEquals(resultList.get(1).getPrioridad(), hashMapExpected.get("priority_2"));
		assertEquals(resultList.get(1).getLista().getNombre(), hashMapExpected.get("list_2"));
		assertEquals(resultList.get(2).getNombre(), hashMapExpected.get("name_2"));
		assertEquals(resultList.get(2).getPrioridad(), hashMapExpected.get("priority_2"));
		assertEquals(resultList.get(2).getLista().getNombre(), hashMapExpected.get("list_2"));
	}

}
