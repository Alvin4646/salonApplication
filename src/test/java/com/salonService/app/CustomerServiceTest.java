package com.salonService.app;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.salonService.app.entity.Appointment;
import com.salonService.app.entity.Customer;
import com.salonService.app.exception.CustomerNotFoundException;
import com.salonService.app.repository.ICustomerRepository;
import com.salonService.app.services.ICustomerServicceImpl;

@SpringBootTest
class CustomerServiceTest {
	@Mock
	private ICustomerRepository customerRepository;
	@InjectMocks
	private ICustomerServicceImpl customerService;

	@Test
	@DisplayName("Test to add")
	public void addCustomerService() {
		Customer customerService1 = new Customer();
		customerService1.setName("Arun");
		customerService1.setEmail("abc@gmail.com");
		customerService1.setContactNo("34857");
		customerService1.setDob(LocalDate.of(2021, 4, 15));
		customerService1.setAddress("Gandhinagar");
		customerService1.setAppointments(null);
		customerService1.setCart(null);
		customerService1.setPassword("Password");
		customerService1.setUserId(null);

		Mockito.when(customerRepository.save(customerService1)).thenReturn(customerService1);

		assertEquals(customerService1, customerService.addCustomer(customerService1));
	}

	@Test
	public void deleteCustomerservice() throws CustomerNotFoundException {

		Customer customer = new Customer();
		customer.setName("Arun");
		customer.setEmail("abc@gmail.com");
		customer.setContactNo("34857");
		customer.setDob(LocalDate.of(2021, 4, 15));
		customer.setAddress("Gandhinagar");
		customer.setAppointments(null);
		customer.setCart(null);
		customer.setPassword("Password");
		customer.setUserId(null);
		Mockito.when(customerRepository.findById(customer.getUserId())).thenReturn(Optional.of(customer));
		assertNotEquals(customer, customerService.deleteCustomer(customer.getUserId()));
	}

	@Test
	public void updateCustomerservice() {
		Customer customer = new Customer();
		customer.setName("Arun");
		customer.setEmail("abc@gmail.com");
		customer.setContactNo("34857");
		customer.setDob(LocalDate.of(2021, 4, 15));
		customer.setAddress("Gandhinagar");
		customer.setAppointments(null);
		customer.setCart(null);
		customer.setPassword("Password");
		customer.setUserId(null);
		Mockito.when(customerRepository.save(customer)).thenReturn(customer);
		assertNotEquals(customer, customerRepository.save(customer));
	}

	@Test
	public void getAllCustomerservice() {
		Mockito.when(customerRepository.findAll())
				.thenReturn(java.util.stream.Stream.of(new Customer(), new Customer()).collect(Collectors.toList()));

		assertEquals(2, customerService.getAllCustomers().size());
	}

	@Test
	public void getServiceById() {
		Customer customer = new Customer();
		customer.setName("Arun");
		customer.setEmail("abc@gmail.com");
		customer.setContactNo("34857");
		customer.setDob(LocalDate.of(2021, 4, 15));
		customer.setAddress("Gandhinagar");
		customer.setAppointments(null);
		customer.setCart(null);
		customer.setPassword("Password");
		customer.setUserId(1);

		Mockito.when(customerRepository.save(customer)).thenReturn(customer);
		assertNotEquals(customer, customerRepository.findById(customer.getUserId()));
	}

	@Test
	public void testGetCustomer_whenCustomerNotFound_shouldThrowException() {
		Customer customer = new Customer();
		customer.setName("Arun");
		customer.setEmail("abc@gmail.com");
		customer.setContactNo("34857");
		customer.setDob(LocalDate.of(2021, 4, 15));
		customer.setAddress("Gandhinagar");
		customer.setAppointments(null);
		customer.setCart(null);
		customer.setPassword("Password");
		customer.setUserId(1);
		when(customerRepository.findById(1)).thenReturn(Optional.empty());

		Exception exception = assertThrows(CustomerNotFoundException.class, () -> {
			customerService.getCustomer(1);
		});

		assertEquals("Customer not found by 1 id", exception.getMessage());
	}

@Test
public void testDeleteCustomer_whenCustomerNotFound_shouldThrowException() {
	

  when(customerRepository.findById(1)).thenReturn(Optional.empty());
  
  Exception exception = assertThrows(CustomerNotFoundException.class, () -> {
    customerService.deleteCustomer(1);
  });
  
  assertEquals("Customer not found by 1 id", exception.getMessage());
}
}
