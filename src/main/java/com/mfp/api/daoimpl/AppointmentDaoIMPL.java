package com.mfp.api.daoimpl;

import java.sql.Date;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mfp.api.dao.AppointmentDao;
import com.mfp.api.entity.Appointment;

@Repository
public class AppointmentDaoIMPL implements AppointmentDao {

	@Autowired
	private SessionFactory sf;

	private static final Logger LOG = LogManager.getLogger(AppointmentDao.class);

	@Override
	public Appointment addAppointment(Appointment appointment) {
		Session session = sf.getCurrentSession();
		try {
			session.save(appointment);
			return appointment;
		} catch (Exception e) {
			LOG.error(e);
			return null;
		}

	}

	@Override
	public Appointment updateAppointment(Appointment appointment) {

		Session session = sf.getCurrentSession();
		try {
			Appointment dbAppointment = this.getAppointmentById(appointment.getAppointmentpatientid());
			if (dbAppointment != null) {
				session.delete(dbAppointment);
				session.save(appointment);
				boolean b = true;
				return appointment;
			}else {
				return null;
			}
		} catch (Exception e) {
			LOG.error(e);
			return null;
		}

	}

	@Override
	public Appointment getAppointmentById(String patientId) {

		Session session = sf.getCurrentSession();
		try {
			return session.get(Appointment.class, patientId);
		} catch (Exception e) {
			LOG.error(e);
			return null;
		}
	}

	@Override
	public List<Appointment> getAppointmentsByPatientsIds(List<String> patientsId) {
		return null;
	}

	@Override
	public List<Appointment> getAppointmentsByDoctorIdAndAppointmentDate(String doctorId, Date appointmentDate) {
		List<Appointment> appointments = null;
		Session session = sf.getCurrentSession();

		try {
			CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
			CriteriaQuery<Appointment> criteriaQuery = criteriaBuilder.createQuery(Appointment.class);
			Root<Appointment> root = criteriaQuery.from(Appointment.class);

			Predicate doctorIdPredicate = criteriaBuilder.equal(root.get("doctorid"), doctorId);
			Predicate datePredicate = criteriaBuilder.equal(root.get("appointmentdate"), appointmentDate);

			criteriaQuery.where(doctorIdPredicate, datePredicate);
			appointments = session.createQuery(criteriaQuery).getResultList();
			return appointments;

		} catch (Exception e) {
			LOG.error(e);
			return appointments;
		}
	}

	@Override
	public List<Appointment> getAppointmentsByDoctorIdAndAppointmentDate(String doctorId, Date appointmentDate,
			String appointmentTime) {
		List<Appointment> resultSet = null;

		Session session = sf.getCurrentSession();
		try {

			CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
			CriteriaQuery<Appointment> criteriaQuery = criteriaBuilder.createQuery(Appointment.class);
			Root<Appointment> root = criteriaQuery.from(Appointment.class);

			Predicate doctorIdPredicate = criteriaBuilder.equal(root.get("doctorid"), doctorId);
			Predicate datePredicate = criteriaBuilder.equal(root.get("appointmentdate"), appointmentDate);
			Predicate timePredicate = criteriaBuilder.equal(root.get("appointmenttime"), appointmentTime);

			criteriaQuery.where(doctorIdPredicate, datePredicate, timePredicate);

			resultSet = session.createQuery(criteriaQuery).getResultList();
			return resultSet;

		} catch (Exception e) {
			LOG.error(e);
			return resultSet;

		}
	}

	@Override
	public List<Appointment> getAppointmentsByDate(Date date) {
		List<Appointment> appointments = null;
		Session session = this.sf.getCurrentSession();
		try {

			CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
			CriteriaQuery<Appointment> criteriaQuery = criteriaBuilder.createQuery(Appointment.class);
			Root<Appointment> root = criteriaQuery.from(Appointment.class);

			criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("appointmentdate"), date));

			appointments = session.createQuery(criteriaQuery).getResultList();
			return appointments;

		} catch (Exception e) {
			LOG.error(e);
			return appointments;
		}
	}

	@Override
	public Long getCountByAppointmentDate(Date appointmentDate) {
		return null;
	}

	@Override
	public List<Appointment> getAppointmentsByBillingDate(Date billingDate) {
		List<Appointment> resultList = null;

		Session session = sf.getCurrentSession();

		try {
			CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
			CriteriaQuery<Appointment> createQuery = criteriaBuilder.createQuery(Appointment.class);
			Root<Appointment> root = createQuery.from(Appointment.class);

			createQuery.where(criteriaBuilder.equal(root.get("billingDate"), billingDate));
			resultList = session.createQuery(createQuery).getResultList();

			return resultList;

		} catch (Exception e) {
			LOG.error(e);
			return resultList;

		}

	}

	@Override
	public Long getAppointmentsTotalCount() {
		Session session = sf.getCurrentSession();
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);

		Root<Appointment> root = criteriaQuery.from(Appointment.class);
		criteriaQuery.select(criteriaBuilder.count(root));

		return session.createQuery(criteriaQuery).getSingleResult();
	}

	@Override
	public Long getCountByAppointmentTakenDate(Date appointmentTakenDate) {
		Session session = sf.getCurrentSession();
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);

		Root<Appointment> root = criteriaQuery.from(Appointment.class);
		criteriaQuery.select(criteriaBuilder.count(root));

		Predicate predicate = criteriaBuilder.equal(root.get("appointmentTakenDate"), appointmentTakenDate);
		criteriaQuery.where(predicate);

		try {
			return session.createQuery(criteriaQuery).getSingleResult();
		} catch (NoResultException e) {
			return 0L;
		} catch (Exception e) {
			throw new RuntimeException("Error retrieving count by appointment taken date: " + e.getMessage());
		}

	}

	@Override
	public Long getCountByTreatmentStatusAndBillingDate(String treatmentStatus, Date billingDate) {
		Session session = sf.getCurrentSession();

	            try {
	            	CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
	        		CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);

	        		Root<Appointment> root = criteriaQuery.from(Appointment.class);
	        		
	        		criteriaQuery.select(criteriaBuilder.count(root));

	        		Predicate usernamePredicate = criteriaBuilder.equal(root.get("treatmentstatus"), treatmentStatus);
	        		Predicate surnamePredicate = criteriaBuilder.equal(root.get("billingDate"), billingDate);

	        		criteriaQuery.where(usernamePredicate, surnamePredicate);

	        		TypedQuery<Long> query = session.createQuery(criteriaQuery);

	        		return query.getSingleResult();

	            } catch (Exception e) {
	            	LOG.error(e);
	                return 0L;
	            }
	        }
	@Override
	public List<Appointment> getAllAppointments() {
		List<Appointment> appointments = null;
		Session session = this.sf.getCurrentSession();
		try {
			CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
			CriteriaQuery<Appointment> criteriaQuery = criteriaBuilder.createQuery(Appointment.class);
			Root<Appointment> root = criteriaQuery.from(Appointment.class);
			criteriaQuery.select(root);
			appointments = session.createQuery(criteriaQuery).getResultList();

			return appointments;

		} catch (Exception e) {
			LOG.error(e);
			return appointments;
		}
	}

	@Override
	public List<Appointment> getTop5AppointmentsByDate(Date date) {
		Session session = this.sf.getCurrentSession();
		List<Appointment> top5 = null;
		try {
			 	CriteriaBuilder builder = session.getCriteriaBuilder();
	            CriteriaQuery<Appointment> criteriaQuery = builder.createQuery(Appointment.class);
	            Root<Appointment> root = criteriaQuery.from(Appointment.class);

	            criteriaQuery.select(root);
	            criteriaQuery.where(builder.equal(root.get("appointmentdate"), date)); // Add your where condition here
	            criteriaQuery.orderBy(builder.asc(root.get("appointmentpatientid"))); // Change the sorting criteria as needed

	            TypedQuery<Appointment> query = session.createQuery(criteriaQuery).setMaxResults(5);

	            top5 = query.getResultList();
	            return top5;
    
		} catch (Exception e) {
			LOG.error(e);
			return top5;
		}
}
	


	@Override
	public Appointment getAppointmentByDoctorId(String dId) {
		Session session = this.sf.getCurrentSession();
		try {

			CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
			CriteriaQuery<Appointment> criteriaQuery = criteriaBuilder.createQuery(Appointment.class);
			Root<Appointment> root = criteriaQuery.from(Appointment.class);
			criteriaQuery.select(root);

			criteriaQuery.where(criteriaBuilder.equal(root.get("doctorid"), dId));
			List<Appointment> resultList = session.createQuery(criteriaQuery).getResultList();
			if (resultList != null) {
				return resultList.get(0);
			} else {
				return null;
			}

		} catch (Exception e) {
			LOG.error(e);
			return null;
		}

	}

}
