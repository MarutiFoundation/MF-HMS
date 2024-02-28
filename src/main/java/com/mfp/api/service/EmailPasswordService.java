package com.mfp.api.service;

import org.springframework.transaction.annotation.Transactional;

import com.mfp.api.model.EmailDetails;
import com.mfp.api.model.ResetPasswordDetail;

@Transactional
public interface EmailPasswordService {

	boolean sendMail(EmailDetails details);

	String sendOtp(String UserId);

	String resetPasswordByOtp(ResetPasswordDetail detail);

	String resetPasswordByQA(ResetPasswordDetail detail);

}
