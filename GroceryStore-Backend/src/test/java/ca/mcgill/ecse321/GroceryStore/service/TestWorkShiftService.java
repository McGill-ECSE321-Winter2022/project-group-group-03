package ca.mcgill.ecse321.GroceryStore.service;


import ca.mcgill.ecse321.GroceryStore.dao.WorkShiftRepository;

import ca.mcgill.ecse321.GroceryStore.model.WorkShift;
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
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TestWorkShiftService {
    private static final int WORKSHIFT_KEY = 5000;
    private static final Time START_TIME = Time.valueOf(LocalTime.of(01,00));
    private static final Time END_TIME = Time.valueOf(LocalTime.of(02,00));
    private static final Time LSATIME = Time.valueOf(LocalTime.of(05,00));
    private static final Time LEATIME = Time.valueOf(LocalTime.of(05,00));
    private static final WorkShift.DayOfWeek DAY = WorkShift.DayOfWeek.Wednesday;
    private static final String STRING_DAY = "Monday";

    @Mock
    private WorkShiftRepository workShiftRepository;
    @InjectMocks
    private WorkShiftService workShiftService;

    @BeforeEach
    public void setMockOutput() {

        lenient().when(workShiftRepository.findByShiftID(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
            if (invocation.getArgument(0).equals(WORKSHIFT_KEY)) {
                WorkShift workShift = new WorkShift();
                workShift.setStartTime(START_TIME);
                workShift.setEndTime(END_TIME);
                workShift.setDay(WorkShift.DayOfWeek.Monday);
                return workShift;
            } else {
                return null;
            }
        });
        lenient().when(workShiftRepository.existsById(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
            if (invocation.getArgument(0).equals(WORKSHIFT_KEY)) {
                return Boolean.TRUE;
            } else {
                return Boolean.FALSE;
            }
        });
        Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> {
            return invocation.getArgument(0);
        };
        lenient().when(workShiftRepository.save(any(WorkShift.class))).thenAnswer(returnParameterAsAnswer);

    }

    @Test
    public void testCreateBusinessHour() {

        WorkShift workShift = null;
        try {
            workShift = workShiftService.createWorkShift(START_TIME,END_TIME,STRING_DAY);
        } catch(IllegalArgumentException error) {
            fail();
        }
    }

    @Test
    public void testCreateBusinessHourNullStartTime() {
        WorkShift workShift = null;
        String error = null;

        try {
            workShift = workShiftService.createWorkShift(null,END_TIME,STRING_DAY);
        } catch(IllegalArgumentException error)  {
            errorMessage = error.getMessage();
        }
        assertNull(workShift);
        assertEquals("Start Time can't be empty.",error);
    }

    @Test
    public void testCreateBusinessHourNullEndTime() {

        WorkShift workShift = null;
        String errorMessage = null;

        try {
            workShift = workShiftService.createWorkShift(START_TIME,null,STRING_DAY);
        } catch(IllegalArgumentException error) {
            errorMessage = error.getMessage();
        }
        assertNull(workShift);
        assertEquals("End Time can't be empty.", errorMessage);
    }

    @Test
    public void testCreateBusinessHourNullDay() {

        WorkShift workShift = null;
        String errorMessage = null;

        try {
            workShift = workShiftService.createWorkShift(START_TIME,END_TIME,null);
        } catch(IllegalArgumentException error) {
            errorMessage = error.getMessage();
        }
        assertNull(workShift);
        assertEquals("Day can't be empty.", errorMessage);
    }

    @Test
    public void testCreateBusinessHourEmptyDay() {
        WorkShift workShift= null;
        String errorMessage = null;

        try {
            workShift = workShiftService.createWorkShift(START_TIME,END_TIME,"");
        } catch(IllegalArgumentException error) {
            errorMessage = error.getMessage();
        }
        assertNull(workShift);
        assertEquals("Please enter a valid day of the week.", errorMessage);
    }

    @Test
    public void testCreateBusinessHourInvalidDay() {
        WorkShift workShift = null;
        String errorMessage = null;

        try {
            workShift = workShiftService.createWorkShift(START_TIME,END_TIME,"test");
        } catch(IllegalArgumentException error) {
            errorMessage = error.getMessage();
        }
        assertNull(workShift);
        assertEquals("Please enter a valid day of the week.", errorMessage);
    }

    @Test
    public void testCreateBusinessHourEndTimeBeforeStartTime() {

        WorkShift workShift = null;
        String errorMessage = null;

        try {
            workShift = workShiftService.createWorkShift(END_TIME,START_TIME,STRING_DAY);
        } catch(IllegalArgumentException error) {
            errorMessage = error.getMessage();
        }
        assertNull(workShift);
        assertEquals("End Time cannot be before Start Time.", errorMessage);
    }

//    @Test
//    public void testCreateBusinessHourDuplicate() {
//        WorkShift workShift1 = null;
//        WorkShift workShift2 = null;
//        String errorMessage = null;
//
//
//        try {
//            workShift1 = businessHourService.createBusinessHour(START_TIME,END_TIME,STRING_DAY);
//            when(businessHourRepository.findAll()).thenReturn(Arrays.asList(workShift1));
//            secondBusinessHour = businessHourService.createBusinessHour(START_TIME,END_TIME,STRING_DAY);
//        } catch(IllegalArgumentException error) {
//            errorMessage = error.getMessage();
//        }
//        assertNotNull(firstBusinessHour);
//        assertNull(secondBusinessHour);
//        assertEquals("An identical Business Hour already exists.", errorMessage);
//    }

    @Test
    public void testGetBusinessHourByDay() {
        // TODO
    }

}
