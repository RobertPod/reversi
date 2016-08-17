package robert.reversi_v5web.impl;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import robert.reversi_v5web.api.UserDAO;
import robert.reversi_v5web.domain.CurrentJavaSqlTimestamp;

@Component
public class HibDBUserDAOImpl /* implements HibDBUserDAO */ {
	private static String SQL_SELECT_EMAIL = "SELECT userDaoImpl FROM UserDAOImpl userDaoImpl WHERE userDaoImpl.email LIKE :email";

	@PersistenceContext
	private EntityManager entityManager;

	@Transactional
	public boolean addUser(UserDAO userDao) {
		CurrentJavaSqlTimestamp ts = new CurrentJavaSqlTimestamp();
		Timestamp timestamp = ts.getCurrentJavaSqlTimestamp();
		userDao.setLast_log(timestamp);

		entityManager.persist(userDao);
		return false;
	}

	@Transactional
	public UserDAO getUser(long userID) {
		return entityManager.find(UserDAOImpl.class, userID);
		// return null;
	}

	@Transactional
	public UserDAO searchFirstUserByEmail(String email) {
		Query queryUserByEmail = entityManager.createQuery(SQL_SELECT_EMAIL);
		queryUserByEmail.setParameter("email", email);
		// Collection users = queryUserByEmail.getResultList();
		// return (UserDAO) queryUserByEmail.getSingleResult();
		// List<UserDAO> list = (List<UserDAO>)
		// queryUserByEmail.getResultList();
		// if (list == null || list.isEmpty()) {
		// return null;
		// }
		// return list.get(0);
		List<?> elementList = queryUserByEmail.getResultList();
		return (UserDAO) (CollectionUtils.isEmpty(elementList) ? null : elementList.get(0));
	}

	@Transactional
	public List<?> searchUserByEmail(String email) {
		Query queryUserByEmail = entityManager.createQuery(SQL_SELECT_EMAIL);
		queryUserByEmail.setParameter("email", email);
		List<?> elementList = queryUserByEmail.getResultList();
		return elementList;
	}

	@Transactional
	public boolean existUserWithEmail(String email) {
		return this.searchFirstUserByEmail(email) == null ? false : true;
	}

}
