package com.example.vacationcalculator.util;

import com.example.vacationcalculator.util.HolidayParser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
public class HolidayParserTests {

    @Autowired
    private HolidayParser holidayParser;

    @Test
    public void testHolidaysLoadedSuccessfully() {
        List<LocalDate> holidays = holidayParser.loadHolidays();
        assertFalse(holidays.isEmpty(), "Список праздников не должен быть пустым");
    }
}