package Dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import Model.DsThi;
import Model.KiHoc;
import utils.JpaUtils;

abstract public class Dao<T> {
	abstract public String getdatabase();

	abstract public Class<T> getclass();

	private JpaUtils conn;
	private EntityManager entity;
	private EntityTransaction transaction;

	public Dao() {
		this.conn = new JpaUtils();
		
	}

	public T insert(T t) {
		this.entity = conn.getEntityManager();
		this.transaction = this.entity.getTransaction();
		try {
			this.transaction.begin();
			this.entity.persist(t);
			this.transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			this.transaction.rollback();
		}
		return t;
	};

	public T findbyid(int id) {
		this.entity = conn.getEntityManager();
		this.transaction = this.entity.getTransaction();
		T t = this.entity.find(getclass(), id);
		return t;
	}

	public KiHoc findid(int id) {
		this.entity = conn.getEntityManager();
		this.transaction = this.entity.getTransaction();
		KiHoc k = this.entity.find(KiHoc.class, id);
		return k;
	}

	public void delete(int id) {
		this.entity = conn.getEntityManager();
		this.transaction = this.entity.getTransaction();
		T t = this.findbyid(id);
		try {
			this.transaction.begin();
			this.entity.remove(t);
			this.transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void update(T t) {
		this.entity = conn.getEntityManager();
		this.transaction = this.entity.getTransaction();
		try {
			this.transaction.begin();
			this.entity.merge(t);
			this.transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public List<T> getall() {
		this.entity = conn.getEntityManager();
		this.transaction = this.entity.getTransaction();
		String hql = "From" + getdatabase();
		TypedQuery<T> query = entity.createQuery(hql, getclass());
		List<T> t = query.getResultList();
		return t;
	}

	

}
