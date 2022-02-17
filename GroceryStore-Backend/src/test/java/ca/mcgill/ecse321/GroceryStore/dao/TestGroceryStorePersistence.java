package ca.mcgill.ecse321.GroceryStore.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ca.mcgill.ecse321.GroceryStore.model.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestGroceryStorePersistence {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
	private StoreRepository storeRepository;


    @AfterEach
	public void clearDatabase() {

        // Fisrt, we clear registrations to avoid exceptions due to inconsistencies
        storeRepository.deleteAll();
        employeeRepository.deleteAll();

    }

    @Test
	public void testPersistAndLoadStore() {
		int storeID = 1;
        String address = "215 avenue Kenaston";
        int currentActivePickup = 5;
        int currentActiveDelivery = 6;
		// First example for object save/load
		Store store = new Store();
		//store.addEmployee(new Employee("edward", "1234", "asdasd@asdj", "randomaddress"));
        store.setAddress(address);
        store.setStoreID(storeID);
        store.setCurrentActiveDelivery(currentActiveDelivery);
        store.setCurrentActivePickup(currentActivePickup);
		// First example for attribute save/load
        System.out.println(store.getStoreID());
		storeRepository.save(store);
        store = null;
        store = storeRepository.findById(storeID);
        //Iterable<Store> Stores = storeRepository.findAll();
        assertNotNull(store);
//        for(Store x: Stores){
//            System.out.println(x.getStoreID() +" " + x.getAddress());
//        }

		assertEquals(storeID, store.getStoreID());
	}
    @Test
    public void testPersistAndLoadEmployee() {
        String username = "Seb";
        int id = 2;
        // First example for object save/load
        Employee employee = new Employee();
        employee.setWorkingStatus(Employee.WorkingStatus.Hired);
        // First example for attribute save/load
        employee.setUsername(username);
        //employee.setEmployeeID(id);
        employee.setAddress("address");
        employee.setEmail("email");
        employee.setPassword("12345");

        employeeRepository.save(employee);

        employee = null;

        employee = employeeRepository.findEmployeeByUsername(username);
        //Iterable<Employee> employees = employeeRepository.findAll();
//       for(Employee y: employees){
//            System.out.println(y.getUsername() + " "+ y.getAddress()+ " " +y.getEmail()+ " "+ y.getPassword() + " " + y.getWorkingStatusFullName());
//       }
        assertNotNull(employee);
        assertEquals(username, employee.getUsername());
        
    }
}