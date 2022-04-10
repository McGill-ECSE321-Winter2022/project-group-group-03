package ca.mcgill.ecse321.GroceryStore.service;

import ca.mcgill.ecse321.GroceryStore.dao.HolidayRepository;
import ca.mcgill.ecse321.GroceryStore.dao.StoreRepository;
import ca.mcgill.ecse321.GroceryStore.model.Holiday;
import ca.mcgill.ecse321.GroceryStore.model.Store;
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
    private static final LocalDate LSDATE = LocalDate.of(2015, Month.AUGUST, 1);
    private static final LocalDate LEDATE = LocalDate.of(2015, Month.AUGUST, 2);
    private static final Date START_DATE = Date.valueOf(LSDATE);
    private static final Date END_DATE = Date.valueOf(LEDATE);
    private static final LocalDate LSADATE = LocalDate.of(2015, Month.AUGUST, 3);
    private static final LocalDate LEADATE = LocalDate.of(2015, Month.AUGUST, 4);
    private static final Date START_ADATE = Date.valueOf(LSADATE);
    private static final Date END_ADATE = Date.valueOf(LEADATE);

    @Mock
    private HolidayRepository holidayRepository;
    @Mock
    private StoreRepository storeRepository;
    @Mock
    private StoreService storeService;
    @InjectMocks
    private HolidayService holidayService;

    /**
     * Sets a fake repository in order to test the service methods
     */
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
        lenient().when(storeRepository.findAll()).thenAnswer((InvocationOnMock invocation) -> {
            Store s = storeService.createStore("ADDRESS", 5,5);
            return new ArrayList<Store>(Arrays.asList(s));
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


    /**
     * Creates a holiday that has all the correct inputs
     */
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

    /**
     * Creates a holiday with a name that is null which should give us an error
     */
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

    /**
     * Creates a holiday with a start time that is null which should give us an error
     */
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

    /**
     * Creates a holiday with an end time that is null which should give us an error
     */
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

    /**
     * Creates a holiday with the end date starting before the start date which should give us an error
     */
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

    /**
     * Creates a holiday with a name that already exists in the repo
     */
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

    /**
     * Creates a holiday with the same start and end dates of a holiday that already exists in the repo
     */
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

    /**
     * Gets the holiday that already exists by its name
     */
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


    /**
     * tries invalid id to get a holiday
     */
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

    /**
     * tries empty id to get a holiday
     */
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

    /**
     * tries null id to get a holiday
     */
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

    /**
     * Gets all the holiday from the mock op repository
     */
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

    /**
     * Deletes teh holiday from the repository
     */
    @Test
    public void testDeleteHoliday() {
        String error = null;

        try {
            holidayService.deleteHoliday(HOLIDAY_KEY);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }
        assertNull(error);
    }

    /**
     * tries to delete the holiday with the wrong id
     */
    @Test
    public void testDeleteHolidayInvalidID() {
        String error = null;
        try {
            holidayService.deleteHoliday("Fake");
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }
        assertNotNull(error);
        assertEquals("The holiday does not exist.",error);
    }

    /**
     * tries to delete with the empty id
     */
    @Test
    public void testDeleteHolidayEmptyID() {
        String error = null;
        try {
            holidayService.deleteHoliday(null);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }
        assertNotNull(error);
        assertEquals("Name can't be empty.",error);
    }

    /**
     * updates the holiday's start date
     */
    @Test
    public void testUpdateHolidayStartDate(){
        String error = null;
        Holiday holiday = null;
        Date date = Date.valueOf(LocalDate.of(2015, Month.JULY, 3));
        try{
           holiday = holidayService.updateHolidayDateStart(HOLIDAY_KEY,date);
        }catch (IllegalArgumentException e){
            error = e.getMessage();
        }
        assertNull(error);
        assertNotEquals(START_DATE,START_ADATE);
    }

    /**
     * updates the holiday's start date with an empty name
     */
    @Test
    public void testUpdateHolidayStartDateEmptyName(){
        String error = null;
        Holiday holiday = null;
        Date date = Date.valueOf(LocalDate.of(2015, Month.JULY, 3));
        try{
            holiday = holidayService.updateHolidayDateStart("",date);
        }catch (IllegalArgumentException e){
            error = e.getMessage();
        }
        assertNotNull(error);
        assertEquals("Name can't be empty.",error);
    }

    /**
     * updates the holiday's start date with a null name
     */
    @Test
    public void testUpdateHolidayStartDateNullName(){
        String error = null;
        Holiday holiday = null;
        Date date = Date.valueOf(LocalDate.of(2015, Month.JULY, 3));
        try{
            holiday = holidayService.updateHolidayDateStart(null,date);
        }catch (IllegalArgumentException e){
            error = e.getMessage();
        }
        assertNotNull(error);
        assertEquals("Name can't be empty.",error);
    }

    /**
     * updates the holiday's start date with an invalid id
     */
    @Test
    public void testUpdateHolidayStartDateInvalidId(){
        String error = null;
        Holiday holiday = null;
        Date date = Date.valueOf(LocalDate.of(2015, Month.JULY, 3));
        try{
            holiday = holidayService.updateHolidayDateStart("hello",date);
        }catch (IllegalArgumentException e){
            error = e.getMessage();
        }
        assertNotNull(error);
        assertEquals("Holiday doesn't exist.",error);
    }

    /**
     * updates the holiday's start date with a null date
     */
    @Test
    public void testUpdateHolidayStartDateNullDate(){
        String error = null;
        Holiday holiday = null;
        try{
            holiday = holidayService.updateHolidayDateStart(HOLIDAY_KEY,null);
        }catch (IllegalArgumentException e){
            error = e.getMessage();
        }
        assertNotNull(error);
        assertEquals("Start Date can't be empty.",error);
    }

    /**
     *  updates the holiday's start date with an invert date
     */
    @Test
    public void testUpdateHolidayStartDateInvertDate(){
        String error = null;
        Holiday holiday = null;
        try{
            holiday = holidayService.updateHolidayDateStart(HOLIDAY_KEY,START_ADATE);
        }catch (IllegalArgumentException e){
            error = e.getMessage();
        }
        assertNotNull(error);
        assertEquals("End Date cannot be before Start Date.",error);
    }

    /**
     * updates the holiday's end date
     */
    @Test
    public void testUpdateHolidayEndDate(){
        String error = null;
        Holiday holiday = null;
        Date date = Date.valueOf(LocalDate.of(2015, Month.AUGUST, 31));
        try{
            holiday = holidayService.updateHolidayDateEnd(HOLIDAY_KEY,date);
        }catch (IllegalArgumentException e){
            error = e.getMessage();
        }
        assertNull(error);
        assertNotEquals(END_DATE,date);
    }

    /**
     * updates the holiday's end date with an empty name
     */
    @Test
    public void testUpdateHolidayEndDateEmptyName(){
        String error = null;
        Holiday holiday = null;
        Date date = Date.valueOf(LocalDate.of(2015, Month.AUGUST, 31));
        try{
            holiday = holidayService.updateHolidayDateEnd("",date);
        }catch (IllegalArgumentException e){
            error = e.getMessage();
        }
        assertNotNull(error);
        assertEquals("Name can't be empty.",error);
    }

    /**
     * updates the holiday's end date with a null name
     */
    @Test
    public void testUpdateHolidayEndDateNullName(){
        String error = null;
        Holiday holiday = null;
        Date date = Date.valueOf(LocalDate.of(2015, Month.AUGUST, 31));
        try{
            holiday = holidayService.updateHolidayDateStart(null,date);
        }catch (IllegalArgumentException e){
            error = e.getMessage();
        }
        assertNotNull(error);
        assertEquals("Name can't be empty.",error);
    }

    /**
     * updates the holiday's end date with an invalid id
     */
    @Test
    public void testUpdateHolidayEndDateInvalidId(){
        String error = null;
        Holiday holiday = null;
        Date date = Date.valueOf(LocalDate.of(2015, Month.AUGUST, 31));
        try{
            holiday = holidayService.updateHolidayDateEnd("hello",date);
        }catch (IllegalArgumentException e){
            error = e.getMessage();
        }
        assertNotNull(error);
        assertEquals("Holiday doesn't exist.",error);
    }

    /**
     * updates the holiday's end date with a null date
     */
    @Test
    public void testUpdateHolidayEndDateNullDate(){
        String error = null;
        Holiday holiday = null;
        try{
            holiday = holidayService.updateHolidayDateEnd(HOLIDAY_KEY,null);
        }catch (IllegalArgumentException e){
            error = e.getMessage();
        }
        assertNotNull(error);
        assertEquals("Start Date can't be empty.",error);
    }

    /**
     * updates the holiday's end date with an invert date
     */
    @Test
    public void testUpdateHolidayEndDateInvertDate(){
        String error = null;
        Holiday holiday = null;
        try{
            holiday = holidayService.updateHolidayDateEnd(HOLIDAY_KEY,START_DATE);
        }catch (IllegalArgumentException e){
            error = e.getMessage();
        }
        assertNotNull(error);
        assertEquals("End Date cannot be before Start Date.",error);
    }



}


