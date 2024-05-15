package com.bytes.intern.assessment.serviceimpl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.bytes.intern.assessment.dao.EmployeeDao;
import com.bytes.intern.assessment.dao.TransactionDao;
import com.bytes.intern.assessment.dto.EmployeeDto;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeServiceImplTest {

	@Mock
    private EmployeeDao employeeDao;

    @Mock
    private TransactionDao transactionDao;

    @InjectMocks
    private EmployeeServiceImpl employeeService;
    
    @Test
    public void testAddEmployee_Success() {
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setEmail("test@example.com");
        // Mock behavior
        when(employeeDao.findIfBytestroneEmployee("test@example.com")).thenReturn(1);
        when(employeeDao.existsByEmployeeEmail("test@example.com")).thenReturn(false);
        when(employeeDao.existsByEmployeeEmailAndStatus("test@example.com", "active")).thenReturn(false);

        int result = employeeService.addEmployee(employeeDto);
        assertEquals(1, result);
    }
}
