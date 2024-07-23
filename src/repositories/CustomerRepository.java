package repositories;

import model.Customer;

import java.util.HashMap;

public class CustomerRepository {

    private CustomerRepository customerRepository = null;

    public CustomerRepository getInstance() {
        if(customerRepository == null) {
            customerRepository = new CustomerRepository();
        }
        return customerRepository;
    }

    public HashMap<String, Customer> customerHashMap = new HashMap<>();

    public void addCustomer(String id, Customer customer) {
        customerHashMap.put(id, customer);
    }

    public Customer getCustomer(String customerId) {
        return customerHashMap.get(customerId);
    }

    public boolean isValidCustomer(String customerId) {
        if(customerHashMap.containsKey(customerId)) {
            return true;
        }
        return false;
    }

    public void updateCustomer(String customerId, Customer customer) {
        customerHashMap.put(customerId, customer);
    }
}
