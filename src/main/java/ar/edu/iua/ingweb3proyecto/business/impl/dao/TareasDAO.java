package ar.edu.iua.ingweb3proyecto.business.impl.dao;

import java.util.Dictionary;
import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.criteria.*;

import ar.edu.iua.ingweb3proyecto.business.exception.BusinessException;
import ar.edu.iua.ingweb3proyecto.business.exception.InvalidSortException;
import ar.edu.iua.ingweb3proyecto.model.Lista;
import ar.edu.iua.ingweb3proyecto.model.exception.NotFoundException;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import ar.edu.iua.ingweb3proyecto.model.Tarea;

@Component
public class TareasDAO implements IGenericDAO<Tarea, Integer, HashMap>{

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
	public List<Tarea> findAll(HashMap dic) throws BusinessException, InvalidSortException {
		Session session = emf.unwrap(SessionFactory.class).openSession();
		Transaction tx;
		List<Tarea> resultListTareas = null;
		
		try {
            tx = session.beginTransaction();
            session.flush();

            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Tarea> query = builder.createQuery(Tarea.class);
            Root<Tarea> tarea = query.from(Tarea.class);

            if (dic.isEmpty()) {                                                        //getAll

                query.select(tarea);

            } else if (dic.containsKey("q") && !dic.containsKey("sort")) {              //getByLista

                Join<Tarea, Lista> lista = tarea.join("lista", JoinType.INNER);
                query.select(tarea).where(builder.equal(lista.get("nombre"), dic.get("q")));

            } else if (dic.containsKey("sort") && !dic.containsKey("q")) {                //getAllSorted

                query.orderBy(builder.asc(tarea.get(dic.get("sort").toString())));

            } else if (dic.containsKey("q") && (dic.containsKey("sort"))) {                //getByListaSorted

                Join<Tarea, Lista> lista = tarea.join("lista", JoinType.INNER);
                query.select(tarea).where(builder.equal(lista.get("nombre"), dic.get("q")))
                        .orderBy(builder.asc(tarea.get(dic.get("sort").toString())));

            }

            resultListTareas = session.createQuery(query).getResultList();
            tx.commit();
		} catch (IllegalArgumentException e) {
            throw new InvalidSortException(e);
		} catch (HibernateException e) {
            throw new BusinessException(e);
        } finally {
            session.close();
        }
		
		return resultListTareas;
	}

    @Override
	public Tarea save(Tarea tarea) throws BusinessException {
		Session session = emf.unwrap(SessionFactory.class).openSession();
		Transaction tx;
		Tarea t = new Tarea();

		try {
			tx = session.beginTransaction();
			int id = (Integer) session.save(tarea);

            t = session.get(Tarea.class, id);

			tx.commit();
		} catch (HibernateException e) {
            throw new BusinessException(e);
		} finally {
			session.close();
		}

		return t;
	}

    @Override
    public Tarea findById(Integer id) throws BusinessException, NotFoundException {
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
        } catch (NoResultException e) {
            throw new NotFoundException(e);
        } catch (HibernateException e) {
            throw new BusinessException(e);
        } finally {
            session.close();
        }

        return t;
    }

    @Override
    public void delete(Integer id) throws BusinessException, NotFoundException {
        Session session = emf.unwrap(SessionFactory.class).openSession();
        Transaction tx;

        Tarea t = null;

        try {
            tx = session.beginTransaction();

            t = session.get(Tarea.class, id);

            session.delete(t);

            tx.commit();
        } catch (IllegalArgumentException e) {
            throw new NotFoundException(e);
        } catch (HibernateException e) {
            throw new BusinessException(e);
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
            throw new BusinessException(e);
        } finally {
            session.close();
        }

        return t;
    }

}
