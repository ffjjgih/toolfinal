package Dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import Model.Manageruser;
import Model.User;
import utils.JpaUtils;

public class UserServies {
	private EntityManager em;

	public UserServies() {
		this.em = JpaUtils.getEntityManager();
	}

	public Manageruser findByEmail(String Gmail) {
		String hql = "Select u FROM Manageruser u WHERE u.gmail = :g_mail";
		TypedQuery<Manageruser> query = em.createQuery(hql, Manageruser.class);
		query.setParameter("g_mail", Gmail);
		List<Manageruser> listUser = query.getResultList();
		for (Manageruser us : listUser) {
			return us;
		}
		return null;
	}
}
