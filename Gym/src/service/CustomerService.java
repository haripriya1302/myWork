package service;

import model.Customer;
import repositories.CustomerRepository;
import utils.GymUtil;

import java.util.ArrayList;

public class CustomerService {

    private GymUtil gymUtil;
    private CustomerRepository customerRepository;
    public CustomerService(GymUtil gymUtil, CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
        this.gymUtil = gymUtil;
    }

    public String createCustomer(String name) {
        String id = gymUtil.generateUUID();
        while(gymUtil.checkIdExistsInMap(id, customerRepository.customerHashMap.keySet())) {
            id = gymUtil.generateUUID();
        }
        Customer customer = new Customer();
        customer.setName(name);
        customer.setId(id);
        customer.setBookingList(new ArrayList<>());
        customerRepository.addCustomer(id, customer);
        return id;
    }
}
