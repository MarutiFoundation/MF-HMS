package com.mfp.api.controller;
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.http.HttpStatus; 
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;import java.sql.Date;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mfp.api.entity.Appointment;
import com.mfp.api.exception.ResourceNotFoundException;
import com.mfp.api.exception.SomethingWentWrongException;
import com.mfp.api.service.AppointmentService;
import com.mfp.api.serviceimpl.AppointmentServiceImp;

@RestController
@RequestMapping(value = "/appointment")
public class AppointmentController {

	@Autowired
	private AppointmentService service;

	private static Logger LOG = LogManager.getLogger(AppointmentController.class);

	@PostMapping(value = "/add-appointment")
	public ResponseEntity<Appointment> addAppointment(@RequestBody Appointment appointment) {

		Appointment addedAppointment = service.addAppointment(appointment);
		
		if (addedAppointment != null) {
			LOG.info("Appointment Added !");
			return new ResponseEntity<>(addedAppointment, HttpStatus.OK);
		} else {
			throw new SomethingWentWrongException("Something Went Wrong While Adding Appointment !");
		}
		}
		
	

	@PutMapping(value = "/update-appointment")
	public ResponseEntity<Appointment> updateAppointment(@RequestBody Appointment appointment) {
		
		Appointment updateAppointment = service.updateAppointment(appointment);
		if(updateAppointment!=null) {
			LOG.info("Appointment Updated !");
			return new ResponseEntity<>(updateAppointment, HttpStatus.OK);
		}
	 else {
		throw new ResourceNotFoundException("Something Went Wrong While Updating Appointment ! Appoint Not Found To Update");
	 }
	}

	@GetMapping(value = "/get-appointment-by-id/{id}")
	public ResponseEntity<Appointment> getAppointmentById(@PathVariable String id) {
		Appointment appointmentById = service.getAppointmentById(id);
		
		if (appointmentById!=null) {
			
			return new ResponseEntity<>(appointmentById, HttpStatus.FOUND);
			
		} else {
			throw new ResourceNotFoundException("Id does not exist in database");
		}
	}
	
	@GetMapping(value = "/get-appointment-by-DRID/{dId}")
	public ResponseEntity<Appointment> getAppointmentByDoctorId(@PathVariable String dId) {
		Appointment appointmentByDoctorId = service.getAppointmentByDoctorId(dId);
		
		if (appointmentByDoctorId!=null) {
			
			return new ResponseEntity<>(appointmentByDoctorId, HttpStatus.FOUND);
			
		} else {
			throw new ResourceNotFoundException("Doctor Id does not exist in database");
		}
	}

	@GetMapping(value = "/get-appointment-by-ids/{ids}")
	public ResponseEntity<List<Appointment>> getAppointmentsByPatientsIds(@PathVariable List<String> ids) {
		return null;
	}

	@GetMapping(value = "/get-appointment-by-drid-apointmentdate/{drid}/{date}")
	public ResponseEntity<List<Appointment>> getAppointmentsByDoctorIdAndAppointmentDate(@PathVariable String drid,
			@PathVariable Date date) {
		List<Appointment> appointmentsByDoctorIdAndAppointmentDate = service.getAppointmentsByDoctorIdAndAppointmentDate(drid, date);
		
		if (appointmentsByDoctorIdAndAppointmentDate!=null) {
			return new ResponseEntity<>(appointmentsByDoctorIdAndAppointmentDate,HttpStatus.FOUND);
			
		} else {
			throw new ResourceNotFoundException("Dr.Id And Appointment-Date does not Exist in Database ");
		}
	}

	@GetMapping(value = "/get-appointment-by-drid-and-apointmentdate-and-time")
	public ResponseEntity<List<Appointment>> getAppointmentsByDoctorIdAndAppointmentDate(@RequestParam String doctorId,
			@RequestParam Date appointmentDate, @RequestParam String appointmentTime) {
		 
	List<Appointment> appointmentsByDoctorIdAndAppointmentDateTime = service.getAppointmentsByDoctorIdAndAppointmentDate(doctorId, appointmentDate, appointmentTime);
	
	if(appointmentsByDoctorIdAndAppointmentDateTime!= null) {
		
		return new ResponseEntity<>(appointmentsByDoctorIdAndAppointmentDateTime, HttpStatus.FOUND);
		
	} else {
		throw new ResourceNotFoundException("DR.id, Appointment-date and time Does Not Exist");

	}
	}		
		
		

	@GetMapping(value = "/get-appointment-by-apointmentdate")
	public ResponseEntity<List<Appointment>> getAppointmentsByDate(@RequestParam Date appointmentDate) {
		List<Appointment> list = this.service.getAppointmentsByDate(appointmentDate);
		if (list != null) {
			return new ResponseEntity<>(list, HttpStatus.OK);
		} else {
			throw new SomethingWentWrongException("NO APPOINTMENTS ARE THERE...!");
		}
	}

	@GetMapping(value = "/get-count-by-appointment-date")
	public ResponseEntity<Long> getCountByAppointmentDate(@RequestParam Date date) {
		return null;
	}

	@GetMapping(value = "/get-appointment-by-billingdate")
	public ResponseEntity<List<Appointment>> getAppointmentsByBillingDate(@RequestParam Date billingDate) {
		List<Appointment> appointmentsByBillingDate = service.getAppointmentsByBillingDate(billingDate);
		
		if(appointmentsByBillingDate!=null) {
			return new ResponseEntity<>(appointmentsByBillingDate, HttpStatus.FOUND);
		} 
		else {
			throw new ResourceNotFoundException("THERE ARE NO OPPOINTMENTS");
			
			
		}
	}	
		

	@GetMapping(value = "/get-count-of-appointments")
	public ResponseEntity<Long> getAppointmentsTotalCount() {	
      try {
    	  Long appointmentsTotalCount = service.getAppointmentsTotalCount();  
    	  return ResponseEntity.ok(appointmentsTotalCount);
	
    } catch (Exception e) {
    	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(0L);
    }
      }

	@GetMapping(value = "/get-count-by-appointmenttaken-date")
	public ResponseEntity<Long> getCountByAppointmentTakenDate(@RequestParam Date appointmentTakenDate) {
		
		try {
			Long countByAppointmentTakenDate = service.getCountByAppointmentTakenDate(appointmentTakenDate);
			
			if (countByAppointmentTakenDate> 0) {
                return ResponseEntity.ok(countByAppointmentTakenDate);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(0L);
            }
		} catch (Exception e) {
			 return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(0L);
        }
		}
		
	


	@GetMapping(value = "/get-count-by-treatmentstatus-and billingdate")
	public ResponseEntity<Long> getCountByTreatmentStatusAndBillingDate(@RequestParam String treatmentStatus,
			@RequestParam Date billingDate) {
		
		Long countByTreatmentStatusAndBillingDate = service.getCountByTreatmentStatusAndBillingDate(treatmentStatus, billingDate);
		if (countByTreatmentStatusAndBillingDate>0) {
			
			return new ResponseEntity<>(countByTreatmentStatusAndBillingDate, HttpStatus.FOUND );
		} else {
			throw new ResourceNotFoundException(" treatment status and billing date does not exist in database ");

		}
	
	}

	@GetMapping(value = "/get-all-appointments")
	public ResponseEntity<List<Appointment>> getAllAppointments() {
		List<Appointment> allAppointments = this.service.getAllAppointments();
		
		if(allAppointments == null) {
			throw new ResourceNotFoundException("NO APPOINTMENTS ARE THERE...!");
		}else {
			return new ResponseEntity<>(allAppointments, HttpStatus.OK);
		}
	}

	@GetMapping(value = "/get-top5-appointments")
	public ResponseEntity<List<Appointment>> getTop5AppointmentsByDate(@RequestParam Date date) {// use limit also sort data into asc 1 to 10
		
		List<Appointment> top5AppointmentsByDate = this.service.getTop5AppointmentsByDate(date);
		
		if(top5AppointmentsByDate!=null) {
			return new ResponseEntity<>(top5AppointmentsByDate, HttpStatus.FOUND);
		}
		else {
			throw new ResourceNotFoundException("NO APPOITMENTS ARE THERE");
		}
		
	}
}
