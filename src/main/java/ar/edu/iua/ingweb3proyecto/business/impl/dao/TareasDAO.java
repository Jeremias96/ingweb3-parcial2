package ar.edu.iua.ingweb3proyecto.business.impl.dao;

import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import ar.edu.iua.ingweb3proyecto.business.exception.BusinessException;
import ar.edu.iua.ingweb3proyecto.model.Lista;
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
	public List<Tarea> findAll() throws BusinessException {
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
            throw new BusinessException();
        } finally {
            session.close();
        }
		
		return resultListTareas;
	}

	@Override
	public Tarea save(Tarea tarea) throws BusinessException {
		Session session = emf.unwrap(SessionFactory.class).openSession();
		Transaction tx;
		// cambiar factory para que devuelva tarea
		//Tarea tarea = null;
		Tarea t = new Tarea();

		try {
			tx = session.beginTransaction();
			int id = (Integer) session.save(tarea);

            t = session.get(Tarea.class, id);

            // t.getLista().getNombre() == "backlog".caseLess() -> List error
            // t.getLista() == null -> NOT FOUND

			tx.commit();
		} catch (HibernateException e) {
            throw new BusinessException();
		} finally {
			session.close();
		}

		return t;
	}

    @Override
    public Tarea findById(Integer id) throws BusinessException {
        Session session = emf.unwrap(SessionFactory.class).openSession();
        Transaction tx;

        Tarea t = null;

        try {
            tx = session.beginTransaction();
            session.flush();

            CriteriaBuilder builder = session.getCriteriaBuilder();

            CriteriaQuery<Tarea> query = builder.createQuery(Tarea.class);
            Root<Tarea> from = query.from(Tarea.class);

            query.select(from).where(builder.equal(from.get("id"), id));
            t = session.createQuery(query).getSingleResult();

            tx.commit();
        } catch (HibernateException e) {
            throw new BusinessException();
        } finally {
            session.close();
        }

        return t;
    }

    @Override
    public void delete(Integer id) throws BusinessException {
        Session session = emf.unwrap(SessionFactory.class).openSession();
        Transaction tx;

        Tarea t = null;

        try {
            tx = session.beginTransaction();

            t = session.get(Tarea.class, id);

            session.delete(t);

            tx.commit();
        } catch (HibernateException e) {
            throw new BusinessException();
        } finally {
            session.close();
        }
    }

    @Override
    public Tarea update(Tarea tarea) throws BusinessException {
        Session session = emf.unwrap(SessionFactory.class).openSession();
        Transaction tx;

        Tarea t = null;
        Lista l = null;

        try {
            tx = session.beginTransaction();

            l = session.get(Lista.class, tarea.getLista().getId());

            t = session.get(Tarea.class, tarea.getId());

            t.setLista(l);

            session.update(t);

            tx.commit();
        } catch (HibernateException e) {
            throw new BusinessException();
        } finally {
            session.close();
        }

        return t;
    }

}
