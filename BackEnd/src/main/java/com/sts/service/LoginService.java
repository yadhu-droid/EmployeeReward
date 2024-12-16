package com.sts.service;

import com.sts.dto.LoginDto;
import com.sts.model.Student;

public interface LoginService {

	Student validateStudent(LoginDto loginDto);

}
