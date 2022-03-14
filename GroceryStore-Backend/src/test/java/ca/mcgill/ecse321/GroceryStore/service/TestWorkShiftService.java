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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TestWorkShiftService {
    private static final int WORKSHIFT_KEY = 11000;
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
                workShift.setShiftID(WORKSHIFT_KEY);
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
    public void testCreateWorkShift() {

        WorkShift workShift = null;
        try {
            workShift = workShiftService.createWorkShift(START_TIME,END_TIME,STRING_DAY);
        } catch(IllegalArgumentException error) {
            fail();
        }
    }

    @Test
    public void testCreateWorkShiftNullStartTime() {
        WorkShift workShift = null;
        String error = null;

        try {
            workShift = workShiftService.createWorkShift(null,END_TIME,STRING_DAY);
        } catch(IllegalArgumentException e)  {
            error = e.getMessage();
        }
        assertNull(workShift);
        assertEquals("Start Time can't be empty.",error);
    }

    @Test
    public void testCreateWorkShiftNullEndTime() {

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
    public void testCreateWorkShiftNullDay() {

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
    public void testCreateWorkShiftEmptyDay() {
        WorkShift workShift= null;
        String errorMessage = null;

        try {
            workShift = workShiftService.createWorkShift(START_TIME,END_TIME,"");
        } catch(IllegalArgumentException error) {
            errorMessage = error.getMessage();
        }
        assertNull(workShift);
        assertEquals("Day can't be empty.", errorMessage);
    }

    @Test
    public void testCreateWorkShiftInvalidDay() {
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
    public void testCreateWorkShiftEndTimeBeforeStartTime() {

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

    @Test
    public void testCreateWorkShiftDuplicate() {
        WorkShift workShift1 = null;
        WorkShift workShift2 = null;
        String errorMessage = null;


        try {
            workShift1 = workShiftService.createWorkShift(START_TIME,END_TIME,STRING_DAY);
            when(workShiftRepository.findAll()).thenReturn(Arrays.asList(workShift1));
            workShift2 = workShiftService.createWorkShift(START_TIME,END_TIME,STRING_DAY);
        } catch(IllegalArgumentException error) {
            errorMessage = error.getMessage();
        }
        assertNotNull(workShift1);
        assertNull(workShift2);
        assertEquals("An identical Work shift already exists.", errorMessage);
    }

    @Test
    public void testGetWorkShift() {
        WorkShift workShift = null;
        try {
            workShift = workShiftService.getWorkShift(WORKSHIFT_KEY);
        } catch(IllegalArgumentException error) {
            fail();
        }
        assertNotNull(workShift);
        assertEquals(workShift.getShiftID(),WORKSHIFT_KEY);

    }

    @Test
    public void testGetWorkShiftInvalidID() {
        WorkShift workShift = null;
        String errorMessage = null;
        try {
            workShift = workShiftService.getWorkShift(7);
        } catch(IllegalArgumentException error) {
            errorMessage = error.getMessage();
        }
        assertNull(workShift);
        assertEquals("Work shift doesn't exist.",errorMessage);
    }

    @Test
    public void testGetAllWorkShifts() {
        List<WorkShift> workShifts = new ArrayList<>();
        WorkShift workShift = null;
        when(workShiftRepository.findAll()).thenReturn(workShifts);

        try {
            workShift = workShiftService.createWorkShift(START_TIME,END_TIME,STRING_DAY);
            workShifts.add(workShift);
            workShifts = workShiftService.getAllWorkShift();
        } catch(IllegalArgumentException error) {
            fail();
        }
        assertNotNull(workShifts);
    }

    @Test
    public void testDeleteWorkShift() {
        try {
            workShiftService.deleteWorkShift(WORKSHIFT_KEY);
        } catch (IllegalArgumentException e) {
            fail();
        }
    }

    @Test
    public void testDeleteWorkShiftInvalidID() {

        String errorMessage = null;
        try {
            workShiftService.deleteWorkShift(4);
        } catch(IllegalArgumentException error) {
            errorMessage = error.getMessage();
        }
        assertEquals("The work shift does not exist.",errorMessage);
    }

}
