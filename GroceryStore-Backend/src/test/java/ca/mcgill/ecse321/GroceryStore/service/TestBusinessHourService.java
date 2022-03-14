package ca.mcgill.ecse321.GroceryStore.service;

import ca.mcgill.ecse321.GroceryStore.dao.BusinessHourRepository;
import ca.mcgill.ecse321.GroceryStore.model.BusinessHour;
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
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TestBusinessHourService {

    @Mock
    private BusinessHourRepository businessHourRepository;

    @InjectMocks
    private BusinessHourService businessHourService;

    private static final int HOURS_ID = 10000;
    private static final Time START_TIME = Time.valueOf(LocalTime.of(8,35,0));
    private static final Time END_TIME = Time.valueOf(LocalTime.of(9,55,0));
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
        Answer<?> returnParameterAnswer = (InvocationOnMock invocation) -> {
            return invocation.getArgument(0);
        };
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
        assertEquals("An identical Business Hour already exists.", errorMessage);
    }

    @Test
    public void testGetBusinessHourByDay() {
        // TODO
    }
}
