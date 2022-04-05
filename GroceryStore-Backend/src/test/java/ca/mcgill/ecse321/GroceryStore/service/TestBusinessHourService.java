package ca.mcgill.ecse321.GroceryStore.service;

import ca.mcgill.ecse321.GroceryStore.dao.BusinessHourRepository;
import ca.mcgill.ecse321.GroceryStore.dao.StoreRepository;
import ca.mcgill.ecse321.GroceryStore.model.BusinessHour;
import ca.mcgill.ecse321.GroceryStore.model.Store;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;


import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TestBusinessHourService {

    @Mock
    private BusinessHourRepository businessHourRepository;
    @Mock
    private StoreRepository storeRepository;
    @Mock
    private StoreService storeService;
    @InjectMocks
    private BusinessHourService businessHourService;

    private static final int HOURS_ID = 10000;
    private static final Time START_TIME = Time.valueOf(LocalTime.of(8,35,0));
    private static final Time END_TIME = Time.valueOf(LocalTime.of(9,55,0));
    private static final Time NEW_START_TIME = Time.valueOf(LocalTime.of(9,54,0));
    private static final Time BAD_NEW_END_TIME = Time.valueOf(LocalTime.of(8,0,0));
    private static final Time BAD_NEW_START_TIME = Time.valueOf(LocalTime.of(9,56,0));
    private static final Time NEW_END_TIME = Time.valueOf(LocalTime.of(11,30,0));
    private static final BusinessHour.DayOfWeek DAY = BusinessHour.DayOfWeek.Wednesday;
    private static final String STRING_DAY = "Wednesday";
    private static final String BAD_DAY = "Today";

    @BeforeEach
    public void setMockOutput() {
        lenient().when(businessHourRepository.findByHoursID(any(Integer.class))).thenAnswer((InvocationOnMock invocation) -> {
            if (invocation.getArgument(0).equals(HOURS_ID)) {
                BusinessHour businessHour = new BusinessHour();
                businessHour.setHoursID(HOURS_ID);
                businessHour.setStartTime(START_TIME);
                businessHour.setEndTime(END_TIME);
                businessHour.setDay(DAY);
                return businessHour;
            }
            else {
            return null;
            }
        });
        lenient().when(storeRepository.findAll()).thenAnswer((InvocationOnMock invocation) -> {
            Store s = storeService.createStore("ADDRESS", 5,5);
            return new ArrayList<>(Arrays.asList(s));
        });
        lenient().when(businessHourRepository.existsById(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
            if (invocation.getArgument(0).equals(HOURS_ID)) {
                return Boolean.TRUE;
            } else {
                return Boolean.FALSE;
            }
        });
        Answer<?> returnParameterAnswer = (InvocationOnMock invocation) -> invocation.getArgument(0);
        lenient().when(businessHourRepository.save(any(BusinessHour.class))).thenAnswer(returnParameterAnswer);
    }

    @Test
    public void testCreateBusinessHour() {
        assertEquals(0,businessHourService.getAllBusinessHours().size());

        BusinessHour businessHour = null;

        try {
            businessHour = businessHourService.createBusinessHour(START_TIME,END_TIME,STRING_DAY);
        } catch(IllegalArgumentException error) {
            fail();
        }
        assertNotNull(businessHour);
        assertEquals(businessHour.getStartTime(),START_TIME);
        assertEquals(businessHour.getEndTime(),END_TIME);
        assertEquals(businessHour.getDay().name(),STRING_DAY);
    }

    @Test
    public void testCreateBusinessHourNullStartTime() {
        assertEquals(0,businessHourService.getAllBusinessHours().size());

        BusinessHour businessHour = null;
        String errorMessage = null;

        try {
            businessHour = businessHourService.createBusinessHour(null,END_TIME,STRING_DAY);
        } catch(IllegalArgumentException error)  {
            errorMessage = error.getMessage();
        }
        assertNull(businessHour);
        assertEquals("Start Time can't be empty.",errorMessage);
    }

    @Test
    public void testCreateBusinessHourNullEndTime() {
        assertEquals(0,businessHourService.getAllBusinessHours().size());

        BusinessHour businessHour = null;
        String errorMessage = null;

        try {
            businessHour = businessHourService.createBusinessHour(START_TIME,null,STRING_DAY);
        } catch(IllegalArgumentException error) {
            errorMessage = error.getMessage();
        }
        assertNull(businessHour);
        assertEquals("End Time can't be empty.", errorMessage);
    }

    @Test
    public void testCreateBusinessHourNullDay() {
        assertEquals(0,businessHourService.getAllBusinessHours().size());

        BusinessHour businessHour = null;
        String errorMessage = null;

        try {
            businessHour = businessHourService.createBusinessHour(START_TIME,END_TIME,null);
        } catch(IllegalArgumentException error) {
            errorMessage = error.getMessage();
        }
        assertNull(businessHour);
        assertEquals("Day can't be empty.", errorMessage);
    }

    @Test
    public void testCreateBusinessHourEmptyDay() {
        assertEquals(0,businessHourService.getAllBusinessHours().size());

        BusinessHour businessHour = null;
        String errorMessage = null;

        try {
            businessHour = businessHourService.createBusinessHour(START_TIME,END_TIME,"");
        } catch(IllegalArgumentException error) {
            errorMessage = error.getMessage();
        }
        assertNull(businessHour);
        assertEquals("Please enter a valid day of the week.", errorMessage);
    }

    @Test
    public void testCreateBusinessHourInvalidDay() {
            assertEquals(0,businessHourService.getAllBusinessHours().size());

            BusinessHour businessHour = null;
            String errorMessage = null;

            try {
                businessHour = businessHourService.createBusinessHour(START_TIME,END_TIME,BAD_DAY);
            } catch(IllegalArgumentException error) {
                errorMessage = error.getMessage();
            }
            assertNull(businessHour);
            assertEquals("Please enter a valid day of the week.", errorMessage);
    }

    @Test
    public void testCreateBusinessHourEndTimeBeforeStartTime() {
            assertEquals(0,businessHourService.getAllBusinessHours().size());

            BusinessHour businessHour = null;
            String errorMessage = null;

            try {
                businessHour = businessHourService.createBusinessHour(END_TIME,START_TIME,STRING_DAY);
            } catch(IllegalArgumentException error) {
                errorMessage = error.getMessage();
            }
            assertNull(businessHour);
            assertEquals("End Time cannot be before Start Time.", errorMessage);
    }

    @Test
    public void testCreateBusinessHourDuplicate() {
        assertEquals(0,businessHourService.getAllBusinessHours().size());

        BusinessHour firstBusinessHour = null;
        BusinessHour secondBusinessHour = null;
        String errorMessage = null;

        ArrayList<BusinessHour> businessHours = new ArrayList<>();
        when(businessHourRepository.findAll()).thenReturn(businessHours);

        try {
            firstBusinessHour = businessHourService.createBusinessHour(START_TIME,END_TIME,STRING_DAY);
            businessHours.add(firstBusinessHour);
            secondBusinessHour = businessHourService.createBusinessHour(START_TIME,END_TIME,STRING_DAY);
        } catch(IllegalArgumentException error) {
            errorMessage = error.getMessage();
        }
        assertNotNull(firstBusinessHour);
        assertNull(secondBusinessHour);
        assertEquals("The Business Hour for " + STRING_DAY + " has already been set.", errorMessage);
    }

    @Test
    public void testGetBusinessHour() {
        BusinessHour businessHour = null;
        try {
            businessHour = businessHourService.getBusinessHour(HOURS_ID);
        } catch(IllegalArgumentException error) {
            fail();
        }
        assertNotNull(businessHour);
        assertEquals(businessHour.getHoursID(),HOURS_ID);

    }

    @Test
    public void testGetBusinessHourInvalidID() {
        BusinessHour businessHour = null;
        int INVALID_ID = 9000;
        String errorMessage = null;
        try {
            businessHour = businessHourService.getBusinessHour(INVALID_ID);
        } catch(IllegalArgumentException error) {
            errorMessage = error.getMessage();
        }
        assertNull(businessHour);
        assertEquals("The Business Hour with ID: " + INVALID_ID + " was not found in the database.",errorMessage);
    }

    @Test
    public void testGetAllBusinessHours() {
        List<BusinessHour> businessHours = new ArrayList<>();
        when(businessHourRepository.findAll()).thenReturn(businessHours);

        try {
            BusinessHour businessHour = businessHourService.createBusinessHour(START_TIME,END_TIME,STRING_DAY);
            businessHours.add(businessHour);
            businessHours = businessHourService.getAllBusinessHours();
        } catch(IllegalArgumentException error) {
            fail();
        }
        assertNotNull(businessHours);
    }

    @Test
    public void testDeleteBusinessHour() {
        try {
            businessHourService.deleteBusinessHour(HOURS_ID);
        } catch (IllegalArgumentException e) {
            fail();
        }
    }

    @Test
    public void testDeleteBusinessHourInvalidID() {
        int INVALID_ID = 8000;
        String errorMessage = null;
        try {
            businessHourService.deleteBusinessHour(INVALID_ID);
        } catch(IllegalArgumentException error) {
            errorMessage = error.getMessage();
        }
        assertEquals("The business hour with ID: " + INVALID_ID + " does not exist.",errorMessage);
    }

    @Test
    public void testBusinessHourUpdateStartTime() {
        assertEquals(0,businessHourService.getAllBusinessHours().size());

        BusinessHour businessHour = null;

        try {
            businessHour = businessHourService.updateBusinessHourStartTime(HOURS_ID,NEW_START_TIME);
        } catch(IllegalArgumentException error) {
            fail();
        }
        assertNotNull(businessHour);
        assertEquals(businessHour.getStartTime(),NEW_START_TIME);
        assertEquals(businessHour.getEndTime(),END_TIME);
        assertEquals(businessHour.getDay().name(),STRING_DAY);
    }

    @Test
    public void testBusinessHourUpdateEndTime() {
        assertEquals(0,businessHourService.getAllBusinessHours().size());

        BusinessHour businessHour = null;

        try {
            businessHour = businessHourService.updateBusinessHourEndTime(HOURS_ID,NEW_END_TIME);
        } catch(IllegalArgumentException error) {
            fail();
        }
        assertNotNull(businessHour);
        assertEquals(businessHour.getStartTime(),START_TIME);
        assertEquals(businessHour.getEndTime(),NEW_END_TIME);
        assertEquals(businessHour.getDay().name(),STRING_DAY);
    }

    @Test
    public void testBusinessHourUpdateNullStartTime() {
        assertEquals(0,businessHourService.getAllBusinessHours().size());
        String errorMessage = null;
        BusinessHour businessHour = null;

        try {
            businessHour = businessHourService.updateBusinessHourStartTime(HOURS_ID,null);
        } catch(IllegalArgumentException error) {
            errorMessage = error.getMessage();
        }
        assertNull(businessHour);
        assertEquals("A time parameter is needed.",errorMessage);

    }

    @Test
    public void testBusinessHourUpdateNullEndTime() {
        assertEquals(0,businessHourService.getAllBusinessHours().size());

        BusinessHour businessHour = null;
        String errorMessage = null;

        try {
            businessHour = businessHourService.updateBusinessHourEndTime(HOURS_ID,null);
        } catch(IllegalArgumentException error) {
            errorMessage = error.getMessage();
        }
        assertNull(businessHour);
        assertEquals("A time parameter is needed.",errorMessage);
    }

    @Test
    public void testUpdateBusinessHourStartTimeInvalidID() {
        int INVALID_ID = 8000;
        BusinessHour businessHour = null;
        String errorMessage = null;
        try {
            businessHour = businessHourService.updateBusinessHourStartTime(INVALID_ID,NEW_START_TIME);
        } catch(IllegalArgumentException error) {
            errorMessage = error.getMessage();
        }
        assertNull(businessHour);
        assertEquals("The business hour with ID: " + INVALID_ID + " does not exist.",errorMessage);
    }

    @Test
    public void testUpdateBusinessHourEndTimeInvalidID() {
        int INVALID_ID = 8000;
        String errorMessage = null;
        try {
            businessHourService.updateBusinessHourStartTime(INVALID_ID,NEW_END_TIME);
        } catch(IllegalArgumentException error) {
            errorMessage = error.getMessage();
        }
        assertEquals("The business hour with ID: " + INVALID_ID + " does not exist.",errorMessage);
    }


    @Test
    public void testUpdateBusinessHourInvalidID() {
        int INVALID_ID = 8000;
        String errorMessage = null;
        try {
            businessHourService.updateBusinessHour(INVALID_ID,NEW_START_TIME,NEW_END_TIME);
        } catch(IllegalArgumentException error) {
            errorMessage = error.getMessage();
        }
        assertEquals("The business hour with ID: " + INVALID_ID + " does not exist.",errorMessage);
    }

    @Test
    public void testUpdateBusinessHourNullNewStartTime() {
        assertEquals(0,businessHourService.getAllBusinessHours().size());
        String errorMessage = null;
        BusinessHour businessHour = null;

        try {
            businessHour = businessHourService.updateBusinessHour(HOURS_ID,null,NEW_END_TIME);
        } catch(IllegalArgumentException error) {
            errorMessage = error.getMessage();
        }
        assertNull(businessHour);
        assertEquals("A Start Time parameter is needed.",errorMessage);
    }

    @Test
    public void testUpdateBusinessHourNullNewEndTime() {
        assertEquals(0,businessHourService.getAllBusinessHours().size());
        String errorMessage = null;
        BusinessHour businessHour = null;

        try {
            businessHour = businessHourService.updateBusinessHour(HOURS_ID,NEW_START_TIME,null);
        } catch(IllegalArgumentException error) {
            errorMessage = error.getMessage();
        }
        assertNull(businessHour);
        assertEquals("An End Time parameter is needed.",errorMessage);
    }


}
