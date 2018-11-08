package ar.edu.iua.ingweb3proyecto.business.impl.dao;

import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import ar.edu.iua.ingweb3proyecto.model.Tarea;

@Component
public class TareasDAO implements IGenericDAO<Tarea, Integer>{

	//@Autowired
	//private EntityManagerFactory		//EntityManagerFactory.unwrap(SessionFactory.class).openSession();
	@Autowired
	private EntityManagerFactory emf = null;
	
	private static TareasDAO instance = null;
	
	private TareasDAO() {
		
	}
	
	@Bean
	public static TareasDAO getInstance() {
		if (instance == null)
			instance = new TareasDAO();
		
		return instance;		
	}
	
	@Override
	public List<Tarea> findAll() {
		Session session = emf.unwrap(SessionFactory.class).openSession();
		Transaction tx;
		List<Tarea> resultListTareas = null;
		
		try {
			tx = session.beginTransaction();
			session.flush();
			
			CriteriaBuilder builder = session.getCriteriaBuilder();
			
			CriteriaQuery<Tarea> query = builder.createQuery(Tarea.class);
			Root<Tarea> from = query.from(Tarea.class);
			
			query.select(from);
			resultListTareas = session.createQuery(query).getResultList();
			
			tx.commit();			
		} catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
		
		return resultListTareas;
	}

}
