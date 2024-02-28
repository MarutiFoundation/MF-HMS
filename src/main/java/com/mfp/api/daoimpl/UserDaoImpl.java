package com.mfp.api.daoimpl;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import com.mfp.api.dao.UserDao;
import com.mfp.api.entity.Otp;
import com.mfp.api.entity.Role;
import com.mfp.api.entity.User;
import com.mfp.api.security.CustomUserDetail;

@Repository
public class UserDaoImpl implements UserDao {
	private static Logger LOG = LogManager.getLogger(UserDaoImpl.class);

	@Autowired
	private SessionFactory sf;

	@Autowired
	public PasswordEncoder passwordEncoder;

	@Override
	public boolean addUser(User user) {
		Session session = sf.getCurrentSession();
		boolean status;

		try {
			session.save(user);
			status = true;
		} catch (Exception e) {
			LOG.error(e.getMessage());
			status = false;
		}

		return status;

	}

	@Override
	public User loginUser(User user) {
		Session session = sf.getCurrentSession();
		User usr = null;
		try {
			usr = session.get(User.class, user.getUsername());
			boolean matches = passwordEncoder.matches(user.getPassword(), usr.getPassword());
			if (matches) {
				return usr;
			} else {
				usr = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return usr;

	}

	@Override
	public CustomUserDetail loadUserByUserId(String userId) {
		Session session = sf.getCurrentSession();
		CustomUserDetail user = new CustomUserDetail();
		User usr = null;
		try {
			usr = session.get(User.class, userId);
			if (usr != null) {
				user.setUserid(usr.getUsername());
				user.setPassword(usr.getPassword());
				user.setRoles(usr.getRoles());
			}
			System.out.println("dao ..." + user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;

	}

	@Override
	public String deleteUser(String userName) {
		Session session = sf.getCurrentSession();
		try {

			CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
			CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
			Root<User> root = criteriaQuery.from(User.class);

			criteriaQuery.select(root);
			criteriaQuery.where(criteriaBuilder.equal(root.get("username"), userName));

			List<User> users = session.createQuery(criteriaQuery).getResultList();

			if (users.size() == 1) {
				session.delete(users.get(0));
				return "success";
			} else {
				return "failed";
			}
		} catch (Exception e) {
			LOG.error(e);
			return "failed";
		}
	}

	@Override
	public User getUserByUserName(String username) {
		Session session = sf.getCurrentSession();
		try {
			return session.get(User.class, username);

		} catch (Exception e) {
			LOG.error(e.getMessage());
			return null;
		}
	}

	@Override
	public List<User> getAllUsers() {
		List<User> users = null;

		Session currentSession = sf.getCurrentSession();

		try{
			CriteriaBuilder criteriaBuilder = currentSession.getCriteriaBuilder();
			CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
			Root<User> root = criteriaQuery.from(User.class);
			criteriaQuery.select(root);
			// return users; // if no record found in DB
			users = currentSession.createQuery(criteriaQuery).getResultList();
			return users;
		} catch (Exception e) {
			LOG.error(e);
			return users;
		}

	}

	@Override
	public User updateUser(User user) {
		Session currentSession = sf.getCurrentSession();
		try {
			currentSession.saveOrUpdate(user);
			return this.getUserByUserName(user.getUsername());
			
		} catch (Exception e) {
			LOG.error(e);
			return null;
		}
	}

	@Override
	public Long getUsersTotalCounts() {
		Session currentSession = sf.getCurrentSession();
		Long count = null;
		try{
			 CriteriaBuilder builder = currentSession.getCriteriaBuilder();
		        CriteriaQuery<Long> query = builder.createQuery(Long.class);
		        Root<User> root = query.from(User.class);

		        query.select(builder.count(root));
		        
		        return currentSession.createQuery(query).getSingleResult();
			
		} catch (Exception e) {
			LOG.error(e);
			return 0L;
		}

	}

	@Override
	public Long getUsersTotalCounts(String type) {

		Session currentSession = sf.getCurrentSession();

		try {
			CriteriaBuilder criteriaBuilder = currentSession.getCriteriaBuilder();
			CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);

			Root<User> root = criteriaQuery.from(User.class); // SELECT * FROM USER

			criteriaQuery.select(criteriaBuilder.count(root)).where(criteriaBuilder.equal(root.get("type"), type));

			return currentSession.createQuery(criteriaQuery).getSingleResult();

		}

		catch (Exception e) {
			LOG.error(e);
			return 0L;
		}

	}

	@Override
	public Long getUserCountByDateAndType(Date registeredDate, String type) {
		Session currentSession = sf.getCurrentSession();

		try {
			CriteriaBuilder criteriaBuilder = currentSession.getCriteriaBuilder();
			CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
			Root<User> root = criteriaQuery.from(User.class);

			// Constructing the WHERE clause
			Predicate predicate = criteriaBuilder.and(criteriaBuilder.equal(root.get("createdDate"), registeredDate),
					criteriaBuilder.equal(root.get("type"), type));

			// Selecting the count
			criteriaQuery.select(criteriaBuilder.count(root)).where(predicate);

			// Executing the query
			Query<Long> query = currentSession.createQuery(criteriaQuery);
			return query.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace(); // Handle the exception according to your application's needs
			return 0L; // Return -1 to indicate an error
		}
	}

	@Override
	public List<User> getUserByFirstName(String firstName) {
		
		List<User> list;
	    try {
	    	Session currentSession = sf.getCurrentSession();
	        CriteriaBuilder criteriaBuilder = currentSession.getCriteriaBuilder();
	        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);

	        Root<User> root = criteriaQuery.from(User.class);
	        criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("firstname"), firstName));

	        Query<User> query = currentSession.createQuery(criteriaQuery);
	        list = query.getResultList();
	    } catch (Exception e) {
	       LOG.error(e);
	       list = null;
	    }
	    return list;
	}
	
	@Override
	public boolean saveOtp(Otp otp) {
		Session session = this.sf.getCurrentSession();
		try {
			session.save(otp);
			return true;
			
		} catch (Exception e) {
			LOG.error(e);
			return false;
		}
	}

	@Override
	public Otp getOtpByUser(String userId) {
		Session session = this.sf.getCurrentSession();
		try {
			return session.get(Otp.class, userId);
		} catch (Exception e) {
			LOG.error(e);
			return null;
		}
	}

	@Override
	public Role addRole(Role role) {
		Role check;
		try {
			Session session = sf.getCurrentSession();
			check = this.getRoleById(role.getId());
			if (check != null) {
				check = null;

			} else {
				session.save(role);
				return role;
			}
		} catch (Exception e) {
			LOG.info(e.getMessage());
			check = null;
		}
		return check;
	}

	@Override
	public Role getRoleById(int roleId) {

		try {
			Session session = sf.getCurrentSession();
			return session.get(Role.class, roleId);

		} catch (Exception e) {
			LOG.error(e);
			return null;
		}
	}

	@Override
	public void delete(User user) {
		// TODO Auto-generated method stub

	}

	@Override
	public Optional<User> findByUserName(String userName) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

}
