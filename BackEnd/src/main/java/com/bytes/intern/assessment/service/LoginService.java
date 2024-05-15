package com.bytes.intern.assessment.service;

import com.bytes.intern.assessment.dto.LoginDto;
import com.bytes.intern.assessment.model.Employee;

public interface LoginService {

	Employee validateEmployee(LoginDto loginDto);

}
