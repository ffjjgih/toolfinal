package Dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import Model.KiHoc;
import utils.JpaUtils;

public class Daokithi extends Dao<KiHoc> {
	private JpaUtils conjpa;
	private EntityManager manager;
	private EntityTransaction transaction;
	private KiHoc k;
	private List<KiHoc> lst;

	public void daokithi() {
		this.conjpa = new JpaUtils();
		this.k = new KiHoc();
		this.lst = new ArrayList<KiHoc>();
	}

	public List<KiHoc> getkihoc() {
		this.manager = this.conjpa.getEntityManager();
		this.transaction = this.manager.getTransaction();
		String hql = "FROM KiHoc k ORDER BY idhk DESC";
		TypedQuery<KiHoc> query = this.manager.createQuery(hql, getclass());
		lst = query.getResultList();
		return lst;
	}
	public void updatekihoc(int id) {
		this.manager = this.conjpa.getEntityManager();
		this.transaction=manager.getTransaction();
		try {
			this.manager.getTransaction().begin();
			manager.flush(); manager.clear();
			String hql = "UPDATE KiHoc k SET trangThai=:status WHERE idhk=:id_hk";
			Query query = this.manager.createQuery(hql);
			query.setParameter("status", "Đã kết thúc");
			query.setParameter("id_hk",id);
			query.executeUpdate();
			this.transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	public KiHoc findkihoc(int id) {
		this.manager=this.conjpa.getEntityManager();
		this.k=this.manager.find(KiHoc.class, id);
		return k;
	}

	@Override
	public String getdatabase() {
		return KiHoc.class.getSimpleName();
	}

	@Override
	public Class<KiHoc> getclass() {
		return KiHoc.class;
	}

}
