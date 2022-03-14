package ca.mcgill.ecse321.GroceryStore.service;

import ca.mcgill.ecse321.GroceryStore.dao.HolidayRepository;
import ca.mcgill.ecse321.GroceryStore.model.Holiday;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TestHolidayService {
    private static final String HOLIDAY_KEY = "TestHoliday";
    private static final LocalDate LSDATE = LocalDate.of(2000, Month.JANUARY, 1);
    private static final LocalDate LEDATE = LocalDate.of(2015, Month.JULY, 31);
    private static final Date START_DATE = Date.valueOf(LSDATE);
    private static final Date END_DATE = Date.valueOf(LEDATE);
    @Mock
    private HolidayRepository holidayRepository;
    @InjectMocks
    private HolidayService holidayService;

    @BeforeEach
    public void setMockOutput() {

        lenient().when(holidayRepository.findByName(anyString())).thenAnswer((InvocationOnMock invocation) -> {
            if (invocation.getArgument(0).equals(HOLIDAY_KEY)) {
                Holiday holiday = new Holiday();
                holiday.setName(HOLIDAY_KEY);
                holiday.setStartDate(START_DATE);
                holiday.setEndDate(END_DATE);
                return holiday;
            } else {
                return null;
            }
        });
        Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> {
            return invocation.getArgument(0);
        };
        lenient().when(holidayRepository.save(any(Holiday.class))).thenAnswer(returnParameterAsAnswer);
    }

    @Test
    public void testCreateHoliday() {
        assertEquals(0, holidayService.getAllHolidays().size());

        String name = "Easter";
        Holiday holiday = null;

        try{
           holiday= holidayService.createHoliday(name,START_DATE,END_DATE);
        }catch(IllegalArgumentException e){
            fail();
        }
    }
    @Test
    public void testCreateHolidayNullName() {
        assertEquals(0, holidayService.getAllHolidays().size());

        String name = null;
        Holiday holiday = null;
        String error = null;

        try{
            holiday= holidayService.createHoliday(name,START_DATE,END_DATE);
        }catch(IllegalArgumentException e){
            error = e.getMessage();
        }
        assertNull(holiday);
        assertEquals("Name can't be empty.",error);
    }
    @Test
    public void testCreateHolidayNullStartDate() {
        assertEquals(0, holidayService.getAllHolidays().size());

        String name = "Easter";
        Holiday holiday = null;
        String error = null;

        try{
            holiday= holidayService.createHoliday(name,null,END_DATE);
        }catch(IllegalArgumentException e){
            error = e.getMessage();
        }
        assertNull(holiday);
        assertEquals("Start Date can't be empty.",error);
    }
    @Test
    public void testCreateHolidayNullEndDate() {
        assertEquals(0, holidayService.getAllHolidays().size());

        String name = "Easter";
        Holiday holiday = null;
        String error = null;

        try{
            holiday= holidayService.createHoliday(name,START_DATE,null);
        }catch(IllegalArgumentException e){
            error = e.getMessage();
        }
        assertNull(holiday);
        assertEquals("End Date can't be empty.",error);
    }

    @Test
    public void testCreateHolidayStartBeforeEnd() {
        assertEquals(0, holidayService.getAllHolidays().size());

        String name = "Easter";
        Holiday holiday = null;
        String error = null;

        try{
            holiday= holidayService.createHoliday(name,END_DATE,START_DATE);
        }catch(IllegalArgumentException e){
            error = e.getMessage();
        }

        assertNull(holiday);
        assertEquals("Start Date can't be after End Date.",error);
    }

    @Test
    public void testCreateHolidayDuplicate() {
        assertEquals(0, holidayService.getAllHolidays().size());

        String name = "Easter";
        Holiday holiday1 = null;
        Holiday holiday2 = null;
        String error = null;

        try{
            holiday1= holidayService.createHoliday(name,START_DATE,END_DATE);
            holiday2= holidayService.createHoliday(name,START_DATE,END_DATE);
        }catch(IllegalArgumentException e){
            error = e.getMessage();
        }
        verify(holidayRepository,atLeastOnce()).save(any(Holiday.class));
        System.out.println(holidayService.getAllHolidays());
//        assertNotNull(holiday1);
//        assertNull(holiday2);
//        assertEquals("An identical holiday already exists.",error);
    }


}

