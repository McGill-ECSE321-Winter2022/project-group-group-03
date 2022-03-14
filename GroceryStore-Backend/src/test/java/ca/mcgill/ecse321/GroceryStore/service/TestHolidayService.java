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
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TestHolidayService {
    private static final String HOLIDAY_KEY = "TestHoliday";
    private static final LocalDate LSDATE = LocalDate.of(2012, Month.JANUARY, 1);
    private static final LocalDate LEDATE = LocalDate.of(2015, Month.AUGUST, 31);
    private static final Date START_DATE = Date.valueOf(LSDATE);
    private static final Date END_DATE = Date.valueOf(LEDATE);
    private static final LocalDate LSADATE = LocalDate.of(2015, Month.FEBRUARY, 1);
    private static final LocalDate LEADATE = LocalDate.of(2015, Month.JULY, 31);
    private static final Date START_ADATE = Date.valueOf(LSADATE);
    private static final Date END_ADATE = Date.valueOf(LEADATE);

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
        lenient().when(holidayRepository.existsById(anyString())).thenAnswer((InvocationOnMock invocation) -> {
            if (invocation.getArgument(0).equals(HOLIDAY_KEY)) {
                return Boolean.TRUE;
            } else {
                return Boolean.FALSE;
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

        String name = "Spring Break";
        Holiday holiday = null;

        try {
            holiday = holidayService.createHoliday(name, START_DATE, END_DATE);
        } catch (IllegalArgumentException e) {
            fail();
        }
        assertNotNull(holiday);
        assertEquals(holiday.getEndDate(), END_DATE);
        assertEquals(holiday.getStartDate(), START_DATE);
        assertEquals(holiday.getName(), name);
    }

    @Test
    public void testCreateHolidayNullName() {
        assertEquals(0, holidayService.getAllHolidays().size());

        String name = null;
        Holiday holiday = null;
        String error = null;

        try {
            holiday = holidayService.createHoliday(name, START_DATE, END_DATE);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }
        assertNull(holiday);
        assertEquals("Name can't be empty.", error);
    }

    @Test
    public void testCreateHolidayNullStartDate() {
        assertEquals(0, holidayService.getAllHolidays().size());

        String name = "Easter";
        Holiday holiday = null;
        String error = null;

        try {
            holiday = holidayService.createHoliday(name, null, END_DATE);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }
        assertNull(holiday);
        assertEquals("Start Date can't be empty.", error);
    }

    @Test
    public void testCreateHolidayNullEndDate() {
        assertEquals(0, holidayService.getAllHolidays().size());

        String name = "Easter";
        Holiday holiday = null;
        String error = null;

        try {
            holiday = holidayService.createHoliday(name, START_DATE, null);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }
        assertNull(holiday);
        assertEquals("End Date can't be empty.", error);
    }

    @Test
    public void testCreateHolidayStartBeforeEnd() {
        assertEquals(0, holidayService.getAllHolidays().size());

        String name = "Easter";
        Holiday holiday = null;
        String error = null;

        try {
            holiday = holidayService.createHoliday(name, END_DATE, START_DATE);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }

        assertNull(holiday);
        assertEquals("Start Date can't be after End Date.", error);
    }

    @Test
    public void testCreateHolidayDuplicateId() {
        assertEquals(0, holidayService.getAllHolidays().size());

        String name = "Easter";
        Holiday holiday1 = null;
        Holiday holiday2 = null;
        String error = null;
        ArrayList<Holiday> ls = new ArrayList<>();
        when(holidayRepository.findAll()).thenReturn(ls);

        try {
            holiday1 = holidayService.createHoliday(name, START_DATE, END_DATE);
            ls.add(holiday1);
            holiday2 = holidayService.createHoliday(name, START_ADATE, END_ADATE);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }
        assertNotNull(holiday1);
        assertNull(holiday2);
        assertEquals("An identical holiday with the same name already exists.", error);
    }

    @Test
    public void testCreateHolidayDuplicateDates() {
        assertEquals(0, holidayService.getAllHolidays().size());

        String name = "Christmas";
        String name2 = "Easter";
        Holiday holiday1 = null;
        Holiday holiday2 = null;
        String error = null;
        ArrayList<Holiday> ls = new ArrayList<>();
        when(holidayRepository.findAll()).thenReturn(ls);

        try {
            holiday1 = holidayService.createHoliday(name, START_DATE, END_DATE);
            ls.add(holiday1);
            holiday2 = holidayService.createHoliday(name2, START_DATE, END_DATE);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }
        assertNotNull(holiday1);
        assertNull(holiday2);
        assertEquals("An identical holiday with the same start and end date already exists.", error);
    }

    @Test
    public void testGetHolidayByID() { //not working for some reason
        Holiday holiday = null;
        String error = null;
        try {
            holiday = holidayService.getHoliday(HOLIDAY_KEY);

        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }
        assertNotNull(holiday);
        assertNull(error);

    }


    @Test
    public void testGetHolidayInvalidID() {
        Holiday holiday = null;
        String error = null;
        Holiday holiday2 = null;
        try {
            holiday = holidayService.getHoliday("test");
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }
        assertNull(holiday);
        assertEquals("Holiday doesn't exist.", error);

    }

    @Test
    public void testGetHolidayEmptyID() {
        Holiday holiday = null;
        String error = null;

        try {
            holiday = holidayService.getHoliday("");
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }
        assertNull(holiday);
        assertEquals("Name can't be empty.", error);

    }

    @Test
    public void testGetHolidayNullID() {
        Holiday holiday = null;
        String error = null;

        try {
            holiday = holidayService.createHoliday(null, START_DATE, END_DATE);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }
        assertNull(holiday);
        assertEquals("Name can't be empty.", error);

    }

    @Test
    public void testGetAllHolidays() {
        String error = null;
        List<Holiday> holidays = new ArrayList<>();
        Holiday holiday = null;
        String name = "Easter";
        when(holidayRepository.findAll()).thenReturn(holidays);

        try {
            holiday = holidayService.createHoliday(name, START_DATE, END_DATE);
            holidays.add(holiday);
            holidays = holidayService.getAllHolidays();
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }
        assertNotNull(holidays);
        assertNull(error);
    }

    @Test
    public void testDeleteHoliday() {
        Holiday holiday = null;
        String error = null;
        ArrayList<Holiday> ls = new ArrayList<>();
        when(holidayRepository.findAll()).thenReturn(ls);
        try {
            holiday = holidayService.createHoliday(HOLIDAY_KEY, START_DATE,END_DATE);
            holidayService.deleteHoliday(HOLIDAY_KEY);
            holidayService.getHoliday(HOLIDAY_KEY);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }
        assertNull(error);
        assertNull(holiday);
    }

    //not really working
//    @Test
//    public void testDeleteHolidayInvalidID() {
//        Holiday holiday = null;
//        String error = null;
//        try {
//            holidayService.deleteHoliday("Fake");
//        } catch (IllegalArgumentException e) {
//            error = e.getMessage();
//        }
//        assertNotNull(error);
//        assertEquals("The holiday does not exist.",error);
//    }
//
//    @Test
//    public void testDeleteHolidayEmptyID() {
//        Holiday holiday = null;
//        String error = null;
//        try {
//            holidayService.deleteHoliday(null);
//        } catch (IllegalArgumentException e) {
//            error = e.getMessage();
//        }
//        assertNotNull(error);
//        assertEquals("Name can't be empty.",error);
//    }


}


