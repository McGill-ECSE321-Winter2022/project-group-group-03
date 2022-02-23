package ca.mcgill.ecse321.GroceryStore.dao;

import ca.mcgill.ecse321.GroceryStore.model.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest

public class EmployeeTest {

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private WorkShiftRepository workShiftRepository;

    //Workshift -> An employee can have many workshifts therefore we use a list
    WorkShift defaultWorkShift = new WorkShift();
    List<WorkShift> workShiftList = new ArrayList<WorkShift>();


    //These methods will initialize and create the references that will test the following associations

    public void initializeDefaultWorkShift(){
        this.defaultWorkShift.setShiftID(123);
        this.defaultWorkShift.setDay(WorkShift.DayOfWeek.Monday);
    }

    public void delete1Workshift(WorkShift workShift2Delete){
        workShiftList.remove(workShift2Delete);
        workShiftRepository.deleteById(workShift2Delete.getShiftID());
    }

    @AfterEach
    public void clearDatabase() {

        // First, we clear registrations to avoid exceptions due to inconsistencies

        employeeRepository.deleteAll();
        workShiftRepository.deleteAll();


    }

    /**
     * Tests read and write capabilities with Employee objects and attributes.
     * Association is tested between Employee and Workshift by creating a temporary object initialized with some attributes,
     * then deleting it, and checking to see from the Employee class that the Workshift instance is deleted.
     */
    @Test
    @Transactional
    public void testPersistAndLoadEmployee() {
        String username = "Seb";
        Employee employee = new Employee();
        employee.setWorkingStatus(Employee.WorkingStatus.Hired);
        employee.setUsername(username);
        employee.setAddress("address");
        employee.setEmail("email");
        employee.setPassword("12345");


        initializeDefaultWorkShift();
        this.workShiftList.add(defaultWorkShift);
        employee.setWorkShift(workShiftList);
        workShiftRepository.saveAll(workShiftList);
        employeeRepository.save(employee);

        employee = employeeRepository.findByUsername(username);
        assertNotNull(employee);
        assertEquals(username, employee.getUsername());

        delete1Workshift(defaultWorkShift);
        assertNull(workShiftRepository.findByShiftID(defaultWorkShift.getShiftID()));
        assertFalse(employeeRepository.findByUsername(username).getWorkShift().contains(defaultWorkShift));
    }

}
