package ca.mcgill.ecse321.GroceryStore.service;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

import ca.mcgill.ecse321.GroceryStore.dao.CustomerRepository;
import ca.mcgill.ecse321.GroceryStore.dao.DeliveryOrderRepository;
import ca.mcgill.ecse321.GroceryStore.dao.PickupOrderRepository;
import ca.mcgill.ecse321.GroceryStore.model.DeliveryOrder;
import ca.mcgill.ecse321.GroceryStore.model.PickupOrder;
import ca.mcgill.ecse321.GroceryStore.model.Customer;

@ExtendWith(MockitoExtension.class)
public class TestCustomerService {


}
