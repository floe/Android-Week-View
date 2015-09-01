package com.alamkanak.weekview.sample;

import android.graphics.Color;

import com.alamkanak.weekview.WeekViewEvent;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class EventGenerator {

    public EventGenerator() {

    }

    List<WeekViewEvent> getEvents(int year, int month, int user, int fill) {
        if (year != 2015 || month != 10) return new ArrayList<>();

        if (user == 1 && fill == 1) return createListU1_1_3();
        if (user == 1 && fill == 2) return createListU1_2_3();

        if (user == 2 && fill == 1) return createListU2_1_3();
        if (user == 2 && fill == 2) return createListU2_2_3();

        return new ArrayList<>();
    }

    // appointments are Oct 5 - Oct 9, Oct 12 - Oct 16
    // none on weekends, time range is 9:00 - 18:00

    private ArrayList<WeekViewEvent> createListU2_2_3() {

        ArrayList<WeekViewEvent> events = new ArrayList<WeekViewEvent>();

        int color = Color.parseColor("#59dbe0");
        int newMonth = 10; // October
        int newYear = 2015;

        //05.10.2015 9:00-11:00
        Calendar startTime = Calendar.getInstance();
        startTime.set(Calendar.DAY_OF_MONTH, 5);
        startTime.set(Calendar.HOUR_OF_DAY, 9);
        startTime.set(Calendar.MINUTE, 0);
        startTime.set(Calendar.MONTH, newMonth-1);
        startTime.set(Calendar.YEAR, newYear);
        Calendar endTime = (Calendar) startTime.clone();
        endTime.set(Calendar.HOUR_OF_DAY, 11);
        endTime.set(Calendar.MINUTE, 0);
        WeekViewEvent event = new WeekViewEvent(1,MainActivity.getEventTitle(startTime), startTime, endTime);
        event.setColor(color);
        events.add(event);
        
        //05.10.2015 15:30-18:00
        startTime = Calendar.getInstance();
        startTime.set(Calendar.DAY_OF_MONTH, 15);
        startTime.set(Calendar.HOUR_OF_DAY, 30);
        startTime.set(Calendar.MINUTE, 0);
        startTime.set(Calendar.MONTH, newMonth-1);
        startTime.set(Calendar.YEAR, newYear);
        endTime = (Calendar) startTime.clone();
        endTime.set(Calendar.HOUR_OF_DAY, 18);
        endTime.set(Calendar.MINUTE, 0);
        event = new WeekViewEvent(2,MainActivity.getEventTitle(startTime), startTime, endTime);
        event.setColor(color);
        events.add(event);
        
        //06.10.2015 9:00-10:00
        startTime = Calendar.getInstance();
        startTime.set(Calendar.DAY_OF_MONTH, 6);
        startTime.set(Calendar.HOUR_OF_DAY, 9);
        startTime.set(Calendar.MINUTE, 0);
        startTime.set(Calendar.MONTH, newMonth-1);
        startTime.set(Calendar.YEAR, newYear);
        endTime = (Calendar) startTime.clone();
        endTime.set(Calendar.HOUR_OF_DAY, 10);
        endTime.set(Calendar.MINUTE, 0);
        event = new WeekViewEvent(3,MainActivity.getEventTitle(startTime), startTime, endTime);
        event.setColor(color);
        events.add(event);
        
        //06.10.2015 16:30-18:30
        startTime = Calendar.getInstance();
        startTime.set(Calendar.DAY_OF_MONTH, 6);
        startTime.set(Calendar.HOUR_OF_DAY, 16);
        startTime.set(Calendar.MINUTE, 30);
        startTime.set(Calendar.MONTH, newMonth-1);
        startTime.set(Calendar.YEAR, newYear);
        endTime = (Calendar) startTime.clone();
        endTime.set(Calendar.HOUR_OF_DAY, 18);
        endTime.set(Calendar.MINUTE, 30);
        event = new WeekViewEvent(4,MainActivity.getEventTitle(startTime), startTime, endTime);
        event.setColor(color);
        events.add(event);
        
        //07.10.2015 10:00-11:00
        startTime = Calendar.getInstance();
        startTime.set(Calendar.DAY_OF_MONTH, 7);
        startTime.set(Calendar.HOUR_OF_DAY, 10);
        startTime.set(Calendar.MINUTE, 0);
        startTime.set(Calendar.MONTH, newMonth-1);
        startTime.set(Calendar.YEAR, newYear);
        endTime = (Calendar) startTime.clone();
        endTime.set(Calendar.HOUR_OF_DAY, 11);
        endTime.set(Calendar.MINUTE, 0);
        event = new WeekViewEvent(5,MainActivity.getEventTitle(startTime), startTime, endTime);
        event.setColor(color);
        events.add(event);
        
        //07.10.2015 15:00-16:30
        startTime = Calendar.getInstance();
        startTime.set(Calendar.DAY_OF_MONTH, 7);
        startTime.set(Calendar.HOUR_OF_DAY, 15);
        startTime.set(Calendar.MINUTE, 0);
        startTime.set(Calendar.MONTH, newMonth-1);
        startTime.set(Calendar.YEAR, newYear);
        endTime = (Calendar) startTime.clone();
        endTime.set(Calendar.HOUR_OF_DAY, 16);
        endTime.set(Calendar.MINUTE, 30);
        event = new WeekViewEvent(6,MainActivity.getEventTitle(startTime), startTime, endTime);
        event.setColor(color);
        events.add(event);
        
        //08.10.2015 9:00-11:00
        startTime = Calendar.getInstance();
        startTime.set(Calendar.DAY_OF_MONTH, 8);
        startTime.set(Calendar.HOUR_OF_DAY, 9);
        startTime.set(Calendar.MINUTE, 0);
        startTime.set(Calendar.MONTH, newMonth-1);
        startTime.set(Calendar.YEAR, newYear);
        endTime = (Calendar) startTime.clone();
        endTime.set(Calendar.HOUR_OF_DAY, 11);
        endTime.set(Calendar.MINUTE, 0);
        event = new WeekViewEvent(7,MainActivity.getEventTitle(startTime), startTime, endTime);
        event.setColor(color);
        events.add(event);
        
        //08.10.2015 13:30-14:00
        startTime = Calendar.getInstance();
        startTime.set(Calendar.DAY_OF_MONTH, 8);
        startTime.set(Calendar.HOUR_OF_DAY, 13);
        startTime.set(Calendar.MINUTE, 30);
        startTime.set(Calendar.MONTH, newMonth-1);
        startTime.set(Calendar.YEAR, newYear);
        endTime = (Calendar) startTime.clone();
        endTime.set(Calendar.HOUR_OF_DAY, 14);
        endTime.set(Calendar.MINUTE, 0);
        event = new WeekViewEvent(8,MainActivity.getEventTitle(startTime), startTime, endTime);
        event.setColor(color);
        events.add(event);
        
        //08.10.2015 14:30-17:00
        startTime = Calendar.getInstance();
        startTime.set(Calendar.DAY_OF_MONTH, 8);
        startTime.set(Calendar.HOUR_OF_DAY, 14);
        startTime.set(Calendar.MINUTE, 30);
        startTime.set(Calendar.MONTH, newMonth-1);
        startTime.set(Calendar.YEAR, newYear);
        endTime = (Calendar) startTime.clone();
        endTime.set(Calendar.HOUR_OF_DAY, 17);
        endTime.set(Calendar.MINUTE, 0);
        event = new WeekViewEvent(9,MainActivity.getEventTitle(startTime), startTime, endTime);
        event.setColor(color);
        events.add(event);
        
        //09.10.2015 10:00-12:00
        startTime = Calendar.getInstance();
        startTime.set(Calendar.DAY_OF_MONTH, 9);
        startTime.set(Calendar.HOUR_OF_DAY, 10);
        startTime.set(Calendar.MINUTE, 0);
        startTime.set(Calendar.MONTH, newMonth-1);
        startTime.set(Calendar.YEAR, newYear);
        endTime = (Calendar) startTime.clone();
        endTime.set(Calendar.HOUR_OF_DAY, 12);
        endTime.set(Calendar.MINUTE, 0);
        event = new WeekViewEvent(10,MainActivity.getEventTitle(startTime), startTime, endTime);
        event.setColor(color);
        events.add(event);
        
        //09.10.2015 16:00-19:00
        startTime = Calendar.getInstance();
        startTime.set(Calendar.DAY_OF_MONTH, 9);
        startTime.set(Calendar.HOUR_OF_DAY, 16);
        startTime.set(Calendar.MINUTE, 0);
        startTime.set(Calendar.MONTH, newMonth-1);
        startTime.set(Calendar.YEAR, newYear);
        endTime = (Calendar) startTime.clone();
        endTime.set(Calendar.HOUR_OF_DAY, 19);
        endTime.set(Calendar.MINUTE, 0);
        event = new WeekViewEvent(11,MainActivity.getEventTitle(startTime), startTime, endTime);
        event.setColor(color);
        events.add(event);
        
        //12.10.2015 9:00-10:30
        startTime = Calendar.getInstance();
        startTime.set(Calendar.DAY_OF_MONTH, 12);
        startTime.set(Calendar.HOUR_OF_DAY, 9);
        startTime.set(Calendar.MINUTE, 0);
        startTime.set(Calendar.MONTH, newMonth-1);
        startTime.set(Calendar.YEAR, newYear);
        endTime = (Calendar) startTime.clone();
        endTime.set(Calendar.HOUR_OF_DAY, 10);
        endTime.set(Calendar.MINUTE, 30);
        event = new WeekViewEvent(12,MainActivity.getEventTitle(startTime), startTime, endTime);
        event.setColor(color);
        events.add(event);
        
        //12.10.2015 14:00-17:00
        startTime = Calendar.getInstance();
        startTime.set(Calendar.DAY_OF_MONTH, 12);
        startTime.set(Calendar.HOUR_OF_DAY, 14);
        startTime.set(Calendar.MINUTE, 0);
        startTime.set(Calendar.MONTH, newMonth-1);
        startTime.set(Calendar.YEAR, newYear);
        endTime = (Calendar) startTime.clone();
        endTime.set(Calendar.HOUR_OF_DAY, 17);
        endTime.set(Calendar.MINUTE, 0);
        event = new WeekViewEvent(13,MainActivity.getEventTitle(startTime), startTime, endTime);
        event.setColor(color);
        events.add(event);
        
        //13.10.2015 9:00-10:00
        startTime = Calendar.getInstance();
        startTime.set(Calendar.DAY_OF_MONTH, 13);
        startTime.set(Calendar.HOUR_OF_DAY, 9);
        startTime.set(Calendar.MINUTE, 0);
        startTime.set(Calendar.MONTH, newMonth-1);
        startTime.set(Calendar.YEAR, newYear);
        endTime = (Calendar) startTime.clone();
        endTime.set(Calendar.HOUR_OF_DAY, 10);
        endTime.set(Calendar.MINUTE, 0);
        event = new WeekViewEvent(14,MainActivity.getEventTitle(startTime), startTime, endTime);
        event.setColor(color);
        events.add(event);
        
        //13.10.2015 12:30-15:30
        startTime = Calendar.getInstance();
        startTime.set(Calendar.DAY_OF_MONTH, 13);
        startTime.set(Calendar.HOUR_OF_DAY, 12);
        startTime.set(Calendar.MINUTE, 30);
        startTime.set(Calendar.MONTH, newMonth-1);
        startTime.set(Calendar.YEAR, newYear);
        endTime = (Calendar) startTime.clone();
        endTime.set(Calendar.HOUR_OF_DAY, 15);
        endTime.set(Calendar.MINUTE, 30);
        event = new WeekViewEvent(15,MainActivity.getEventTitle(startTime), startTime, endTime);
        event.setColor(color);
        events.add(event);
        
        //14.10.2015 9:30-10:30
        startTime = Calendar.getInstance();
        startTime.set(Calendar.DAY_OF_MONTH, 14);
        startTime.set(Calendar.HOUR_OF_DAY, 9);
        startTime.set(Calendar.MINUTE, 30);
        startTime.set(Calendar.MONTH, newMonth-1);
        startTime.set(Calendar.YEAR, newYear);
        endTime = (Calendar) startTime.clone();
        endTime.set(Calendar.HOUR_OF_DAY, 10);
        endTime.set(Calendar.MINUTE, 30);
        event = new WeekViewEvent(16,MainActivity.getEventTitle(startTime), startTime, endTime);
        event.setColor(color);
        events.add(event);
        
        //14.10.2015 12:00-15:30
        startTime = Calendar.getInstance();
        startTime.set(Calendar.DAY_OF_MONTH, 14);
        startTime.set(Calendar.HOUR_OF_DAY, 12);
        startTime.set(Calendar.MINUTE, 0);
        startTime.set(Calendar.MONTH, newMonth-1);
        startTime.set(Calendar.YEAR, newYear);
        endTime = (Calendar) startTime.clone();
        endTime.set(Calendar.HOUR_OF_DAY, 15);
        endTime.set(Calendar.MINUTE, 30);
        event = new WeekViewEvent(17,MainActivity.getEventTitle(startTime), startTime, endTime);
        event.setColor(color);
        events.add(event);
        
        //15.10.2015 9:00-9:30
        startTime = Calendar.getInstance();
        startTime.set(Calendar.DAY_OF_MONTH, 15);
        startTime.set(Calendar.HOUR_OF_DAY, 9);
        startTime.set(Calendar.MINUTE, 0);
        startTime.set(Calendar.MONTH, newMonth-1);
        startTime.set(Calendar.YEAR, newYear);
        endTime = (Calendar) startTime.clone();
        endTime.set(Calendar.HOUR_OF_DAY, 9);
        endTime.set(Calendar.MINUTE, 30);
        event = new WeekViewEvent(18,MainActivity.getEventTitle(startTime), startTime, endTime);
        event.setColor(color);
        events.add(event);
        
        //15.10.2015 11:00-14:00
        startTime = Calendar.getInstance();
        startTime.set(Calendar.DAY_OF_MONTH, 15);
        startTime.set(Calendar.HOUR_OF_DAY, 11);
        startTime.set(Calendar.MINUTE, 0);
        startTime.set(Calendar.MONTH, newMonth-1);
        startTime.set(Calendar.YEAR, newYear);
        endTime = (Calendar) startTime.clone();
        endTime.set(Calendar.HOUR_OF_DAY, 14);
        endTime.set(Calendar.MINUTE, 0);
        event = new WeekViewEvent(19,MainActivity.getEventTitle(startTime), startTime, endTime);
        event.setColor(color);
        events.add(event);
        
        //16.10.2015 10:30-12:30
        startTime = Calendar.getInstance();
        startTime.set(Calendar.DAY_OF_MONTH, 16);
        startTime.set(Calendar.HOUR_OF_DAY, 10);
        startTime.set(Calendar.MINUTE, 30);
        startTime.set(Calendar.MONTH, newMonth-1);
        startTime.set(Calendar.YEAR, newYear);
        endTime = (Calendar) startTime.clone();
        endTime.set(Calendar.HOUR_OF_DAY, 12);
        endTime.set(Calendar.MINUTE, 30);
        event = new WeekViewEvent(20,MainActivity.getEventTitle(startTime), startTime, endTime);
        event.setColor(color);
        events.add(event);
        
        //16.10.2015 15:00-18:30
        startTime = Calendar.getInstance();
        startTime.set(Calendar.DAY_OF_MONTH, 16);
        startTime.set(Calendar.HOUR_OF_DAY, 15);
        startTime.set(Calendar.MINUTE, 0);
        startTime.set(Calendar.MONTH, newMonth-1);
        startTime.set(Calendar.YEAR, newYear);
        endTime = (Calendar) startTime.clone();
        endTime.set(Calendar.HOUR_OF_DAY, 18);
        endTime.set(Calendar.MINUTE, 30);
        event = new WeekViewEvent(21,MainActivity.getEventTitle(startTime), startTime, endTime);
        event.setColor(color);
        events.add(event);

        return events;
    }

    private ArrayList<WeekViewEvent> createListU2_1_3() {

        ArrayList<WeekViewEvent> events = new ArrayList<WeekViewEvent>();

        int color = Color.parseColor("#f57f68");
        int newMonth = 10; // October
        int newYear = 2015;

        //05.10.2015 9:00-10:00
        Calendar startTime = Calendar.getInstance();
        startTime.set(Calendar.DAY_OF_MONTH, 5);
        startTime.set(Calendar.HOUR_OF_DAY, 9);
        startTime.set(Calendar.MINUTE, 0);
        startTime.set(Calendar.MONTH, newMonth-1);
        startTime.set(Calendar.YEAR, newYear);
        Calendar endTime = (Calendar) startTime.clone();
        endTime.set(Calendar.HOUR_OF_DAY, 10);
        endTime.set(Calendar.MINUTE, 0);
        WeekViewEvent event = new WeekViewEvent(1,MainActivity.getEventTitle(startTime), startTime, endTime);
        event.setColor(color);
        events.add(event);
        
        //05.10.2015 16:30-17:30
        startTime = Calendar.getInstance();
        startTime.set(Calendar.DAY_OF_MONTH, 16);
        startTime.set(Calendar.HOUR_OF_DAY, 30);
        startTime.set(Calendar.MINUTE, 0);
        startTime.set(Calendar.MONTH, newMonth-1);
        startTime.set(Calendar.YEAR, newYear);
        endTime = (Calendar) startTime.clone();
        endTime.set(Calendar.HOUR_OF_DAY, 17);
        endTime.set(Calendar.MINUTE, 30);
        event = new WeekViewEvent(2,MainActivity.getEventTitle(startTime), startTime, endTime);
        event.setColor(color);
        events.add(event);
        
        //06.10.2015 9:30-10:00
        startTime = Calendar.getInstance();
        startTime.set(Calendar.DAY_OF_MONTH, 6);
        startTime.set(Calendar.HOUR_OF_DAY, 9);
        startTime.set(Calendar.MINUTE, 30);
        startTime.set(Calendar.MONTH, newMonth-1);
        startTime.set(Calendar.YEAR, newYear);
        endTime = (Calendar) startTime.clone();
        endTime.set(Calendar.HOUR_OF_DAY, 10);
        endTime.set(Calendar.MINUTE, 0);
        event = new WeekViewEvent(3,MainActivity.getEventTitle(startTime), startTime, endTime);
        event.setColor(color);
        events.add(event);
        
        //06.10.2015 16:30-17:00
        startTime = Calendar.getInstance();
        startTime.set(Calendar.DAY_OF_MONTH, 6);
        startTime.set(Calendar.HOUR_OF_DAY, 16);
        startTime.set(Calendar.MINUTE, 30);
        startTime.set(Calendar.MONTH, newMonth-1);
        startTime.set(Calendar.YEAR, newYear);
        endTime = (Calendar) startTime.clone();
        endTime.set(Calendar.HOUR_OF_DAY, 17);
        endTime.set(Calendar.MINUTE, 0);
        event = new WeekViewEvent(4,MainActivity.getEventTitle(startTime), startTime, endTime);
        event.setColor(color);
        events.add(event);
        
        //07.10.2015 10:30-11:00
        startTime = Calendar.getInstance();
        startTime.set(Calendar.DAY_OF_MONTH, 7);
        startTime.set(Calendar.HOUR_OF_DAY, 10);
        startTime.set(Calendar.MINUTE, 30);
        startTime.set(Calendar.MONTH, newMonth-1);
        startTime.set(Calendar.YEAR, newYear);
        endTime = (Calendar) startTime.clone();
        endTime.set(Calendar.HOUR_OF_DAY, 11);
        endTime.set(Calendar.MINUTE, 0);
        event = new WeekViewEvent(5,MainActivity.getEventTitle(startTime), startTime, endTime);
        event.setColor(color);
        events.add(event);
        
        //07.10.2015 15:30-16:30
        startTime = Calendar.getInstance();
        startTime.set(Calendar.DAY_OF_MONTH, 7);
        startTime.set(Calendar.HOUR_OF_DAY, 15);
        startTime.set(Calendar.MINUTE, 30);
        startTime.set(Calendar.MONTH, newMonth-1);
        startTime.set(Calendar.YEAR, newYear);
        endTime = (Calendar) startTime.clone();
        endTime.set(Calendar.HOUR_OF_DAY, 16);
        endTime.set(Calendar.MINUTE, 30);
        event = new WeekViewEvent(6,MainActivity.getEventTitle(startTime), startTime, endTime);
        event.setColor(color);
        events.add(event);
        
        //08.10.2015 9:30-11:00
        startTime = Calendar.getInstance();
        startTime.set(Calendar.DAY_OF_MONTH, 8);
        startTime.set(Calendar.HOUR_OF_DAY, 9);
        startTime.set(Calendar.MINUTE, 30);
        startTime.set(Calendar.MONTH, newMonth-1);
        startTime.set(Calendar.YEAR, newYear);
        endTime = (Calendar) startTime.clone();
        endTime.set(Calendar.HOUR_OF_DAY, 11);
        endTime.set(Calendar.MINUTE, 0);
        event = new WeekViewEvent(7,MainActivity.getEventTitle(startTime), startTime, endTime);
        event.setColor(color);
        events.add(event);
        
        //08.10.2015 15:00-16:30
        startTime = Calendar.getInstance();
        startTime.set(Calendar.DAY_OF_MONTH, 8);
        startTime.set(Calendar.HOUR_OF_DAY, 15);
        startTime.set(Calendar.MINUTE, 0);
        startTime.set(Calendar.MONTH, newMonth-1);
        startTime.set(Calendar.YEAR, newYear);
        endTime = (Calendar) startTime.clone();
        endTime.set(Calendar.HOUR_OF_DAY, 16);
        endTime.set(Calendar.MINUTE, 30);
        event = new WeekViewEvent(8,MainActivity.getEventTitle(startTime), startTime, endTime);
        event.setColor(color);
        events.add(event);
        
        //09.10.2015 10:00-11:00
        startTime = Calendar.getInstance();
        startTime.set(Calendar.DAY_OF_MONTH, 9);
        startTime.set(Calendar.HOUR_OF_DAY, 10);
        startTime.set(Calendar.MINUTE, 0);
        startTime.set(Calendar.MONTH, newMonth-1);
        startTime.set(Calendar.YEAR, newYear);
        endTime = (Calendar) startTime.clone();
        endTime.set(Calendar.HOUR_OF_DAY, 11);
        endTime.set(Calendar.MINUTE, 0);
        event = new WeekViewEvent(9,MainActivity.getEventTitle(startTime), startTime, endTime);
        event.setColor(color);
        events.add(event);
        
        //09.10.2015 17:30-19:00
        startTime = Calendar.getInstance();
        startTime.set(Calendar.DAY_OF_MONTH, 9);
        startTime.set(Calendar.HOUR_OF_DAY, 17);
        startTime.set(Calendar.MINUTE, 30);
        startTime.set(Calendar.MONTH, newMonth-1);
        startTime.set(Calendar.YEAR, newYear);
        endTime = (Calendar) startTime.clone();
        endTime.set(Calendar.HOUR_OF_DAY, 19);
        endTime.set(Calendar.MINUTE, 0);
        event = new WeekViewEvent(10,MainActivity.getEventTitle(startTime), startTime, endTime);
        event.setColor(color);
        events.add(event);
        
        //12.10.2015 9:00-9:30
        startTime = Calendar.getInstance();
        startTime.set(Calendar.DAY_OF_MONTH, 12);
        startTime.set(Calendar.HOUR_OF_DAY, 9);
        startTime.set(Calendar.MINUTE, 0);
        startTime.set(Calendar.MONTH, newMonth-1);
        startTime.set(Calendar.YEAR, newYear);
        endTime = (Calendar) startTime.clone();
        endTime.set(Calendar.HOUR_OF_DAY, 9);
        endTime.set(Calendar.MINUTE, 30);
        event = new WeekViewEvent(11,MainActivity.getEventTitle(startTime), startTime, endTime);
        event.setColor(color);
        events.add(event);
        
        //12.10.2015 15:00-16:00
        startTime = Calendar.getInstance();
        startTime.set(Calendar.DAY_OF_MONTH, 12);
        startTime.set(Calendar.HOUR_OF_DAY, 15);
        startTime.set(Calendar.MINUTE, 0);
        startTime.set(Calendar.MONTH, newMonth-1);
        startTime.set(Calendar.YEAR, newYear);
        endTime = (Calendar) startTime.clone();
        endTime.set(Calendar.HOUR_OF_DAY, 16);
        endTime.set(Calendar.MINUTE, 0);
        event = new WeekViewEvent(12,MainActivity.getEventTitle(startTime), startTime, endTime);
        event.setColor(color);
        events.add(event);
        
        //13.10.2015 9:00-9:30
        startTime = Calendar.getInstance();
        startTime.set(Calendar.DAY_OF_MONTH, 13);
        startTime.set(Calendar.HOUR_OF_DAY, 9);
        startTime.set(Calendar.MINUTE, 0);
        startTime.set(Calendar.MONTH, newMonth-1);
        startTime.set(Calendar.YEAR, newYear);
        endTime = (Calendar) startTime.clone();
        endTime.set(Calendar.HOUR_OF_DAY, 9);
        endTime.set(Calendar.MINUTE, 30);
        event = new WeekViewEvent(13,MainActivity.getEventTitle(startTime), startTime, endTime);
        event.setColor(color);
        events.add(event);
        
        //13.10.2015 14:00-15:00
        startTime = Calendar.getInstance();
        startTime.set(Calendar.DAY_OF_MONTH, 13);
        startTime.set(Calendar.HOUR_OF_DAY, 14);
        startTime.set(Calendar.MINUTE, 0);
        startTime.set(Calendar.MONTH, newMonth-1);
        startTime.set(Calendar.YEAR, newYear);
        endTime = (Calendar) startTime.clone();
        endTime.set(Calendar.HOUR_OF_DAY, 15);
        endTime.set(Calendar.MINUTE, 0);
        event = new WeekViewEvent(14,MainActivity.getEventTitle(startTime), startTime, endTime);
        event.setColor(color);
        events.add(event);
        
        //14.10.2015 10:00-10:30
        startTime = Calendar.getInstance();
        startTime.set(Calendar.DAY_OF_MONTH, 14);
        startTime.set(Calendar.HOUR_OF_DAY, 10);
        startTime.set(Calendar.MINUTE, 0);
        startTime.set(Calendar.MONTH, newMonth-1);
        startTime.set(Calendar.YEAR, newYear);
        endTime = (Calendar) startTime.clone();
        endTime.set(Calendar.HOUR_OF_DAY, 10);
        endTime.set(Calendar.MINUTE, 30);
        event = new WeekViewEvent(15,MainActivity.getEventTitle(startTime), startTime, endTime);
        event.setColor(color);
        events.add(event);
        
        //14.10.2015 13:00-14:30
        startTime = Calendar.getInstance();
        startTime.set(Calendar.DAY_OF_MONTH, 14);
        startTime.set(Calendar.HOUR_OF_DAY, 13);
        startTime.set(Calendar.MINUTE, 0);
        startTime.set(Calendar.MONTH, newMonth-1);
        startTime.set(Calendar.YEAR, newYear);
        endTime = (Calendar) startTime.clone();
        endTime.set(Calendar.HOUR_OF_DAY, 14);
        endTime.set(Calendar.MINUTE, 30);
        event = new WeekViewEvent(16,MainActivity.getEventTitle(startTime), startTime, endTime);
        event.setColor(color);
        events.add(event);
        
        //15.10.2015 9:00-9:30
        startTime = Calendar.getInstance();
        startTime.set(Calendar.DAY_OF_MONTH, 15);
        startTime.set(Calendar.HOUR_OF_DAY, 9);
        startTime.set(Calendar.MINUTE, 0);
        startTime.set(Calendar.MONTH, newMonth-1);
        startTime.set(Calendar.YEAR, newYear);
        endTime = (Calendar) startTime.clone();
        endTime.set(Calendar.HOUR_OF_DAY, 9);
        endTime.set(Calendar.MINUTE, 30);
        event = new WeekViewEvent(17,MainActivity.getEventTitle(startTime), startTime, endTime);
        event.setColor(color);
        events.add(event);
        
        //15.10.2015 11:00-13:00
        startTime = Calendar.getInstance();
        startTime.set(Calendar.DAY_OF_MONTH, 15);
        startTime.set(Calendar.HOUR_OF_DAY, 11);
        startTime.set(Calendar.MINUTE, 0);
        startTime.set(Calendar.MONTH, newMonth-1);
        startTime.set(Calendar.YEAR, newYear);
        endTime = (Calendar) startTime.clone();
        endTime.set(Calendar.HOUR_OF_DAY, 13);
        endTime.set(Calendar.MINUTE, 0);
        event = new WeekViewEvent(18,MainActivity.getEventTitle(startTime), startTime, endTime);
        event.setColor(color);
        events.add(event);
        
        //16.10.2015 11:30-12:30
        startTime = Calendar.getInstance();
        startTime.set(Calendar.DAY_OF_MONTH, 16);
        startTime.set(Calendar.HOUR_OF_DAY, 11);
        startTime.set(Calendar.MINUTE, 30);
        startTime.set(Calendar.MONTH, newMonth-1);
        startTime.set(Calendar.YEAR, newYear);
        endTime = (Calendar) startTime.clone();
        endTime.set(Calendar.HOUR_OF_DAY, 12);
        endTime.set(Calendar.MINUTE, 30);
        event = new WeekViewEvent(19,MainActivity.getEventTitle(startTime), startTime, endTime);
        event.setColor(color);
        events.add(event);
        
        //16.10.2015 15:00-15:30
        startTime = Calendar.getInstance();
        startTime.set(Calendar.DAY_OF_MONTH, 16);
        startTime.set(Calendar.HOUR_OF_DAY, 15);
        startTime.set(Calendar.MINUTE, 0);
        startTime.set(Calendar.MONTH, newMonth-1);
        startTime.set(Calendar.YEAR, newYear);
        endTime = (Calendar) startTime.clone();
        endTime.set(Calendar.HOUR_OF_DAY, 15);
        endTime.set(Calendar.MINUTE, 30);
        event = new WeekViewEvent(20,MainActivity.getEventTitle(startTime), startTime, endTime);
        event.setColor(color);
        events.add(event);
        
        //16.10.2015 17:30-18:30
        startTime = Calendar.getInstance();
        startTime.set(Calendar.DAY_OF_MONTH, 16);
        startTime.set(Calendar.HOUR_OF_DAY, 17);
        startTime.set(Calendar.MINUTE, 30);
        startTime.set(Calendar.MONTH, newMonth-1);
        startTime.set(Calendar.YEAR, newYear);
        endTime = (Calendar) startTime.clone();
        endTime.set(Calendar.HOUR_OF_DAY, 18);
        endTime.set(Calendar.MINUTE, 30);
        event = new WeekViewEvent(21,MainActivity.getEventTitle(startTime), startTime, endTime);
        event.setColor(color);
        events.add(event);
        
        return events;
    }

    private ArrayList<WeekViewEvent> createListU1_2_3() {

        ArrayList<WeekViewEvent> events = new ArrayList<WeekViewEvent>();

        int color = Color.parseColor("#87d288");
        int newMonth = 10; // October
        int newYear = 2015;

        //05.10.2015 9:00-9:30
        Calendar startTime = Calendar.getInstance();
        startTime.set(Calendar.DAY_OF_MONTH, 5);
        startTime.set(Calendar.HOUR_OF_DAY, 9);
        startTime.set(Calendar.MINUTE, 0);
        startTime.set(Calendar.MONTH, newMonth-1);
        startTime.set(Calendar.YEAR, newYear);
        Calendar endTime = (Calendar) startTime.clone();
        endTime.set(Calendar.HOUR_OF_DAY, 9);
        endTime.set(Calendar.MINUTE, 30);
        WeekViewEvent event = new WeekViewEvent(1,MainActivity.getEventTitle(startTime), startTime, endTime);
        event.setColor(color);
        events.add(event);
        
        //05.10.2015 12:00-13:30
        startTime = Calendar.getInstance();
        startTime.set(Calendar.DAY_OF_MONTH, 5);
        startTime.set(Calendar.HOUR_OF_DAY, 12);
        startTime.set(Calendar.MINUTE, 0);
        startTime.set(Calendar.MONTH, newMonth-1);
        startTime.set(Calendar.YEAR, newYear);
        endTime = (Calendar) startTime.clone();
        endTime.set(Calendar.HOUR_OF_DAY, 13);
        endTime.set(Calendar.MINUTE, 30);
        event = new WeekViewEvent(2,MainActivity.getEventTitle(startTime), startTime, endTime);
        event.setColor(color);
        events.add(event);
        
        //05.10.2015 17:00-19:00
        startTime = Calendar.getInstance();
        startTime.set(Calendar.DAY_OF_MONTH, 5);
        startTime.set(Calendar.HOUR_OF_DAY, 17);
        startTime.set(Calendar.MINUTE, 0);
        startTime.set(Calendar.MONTH, newMonth-1);
        startTime.set(Calendar.YEAR, newYear);
        endTime = (Calendar) startTime.clone();
        endTime.set(Calendar.HOUR_OF_DAY, 19);
        endTime.set(Calendar.MINUTE, 0);
        event = new WeekViewEvent(3,MainActivity.getEventTitle(startTime), startTime, endTime);
        event.setColor(color);
        events.add(event);
        
        //06.10.2015 11:00-13:00
        startTime = Calendar.getInstance();
        startTime.set(Calendar.DAY_OF_MONTH, 6);
        startTime.set(Calendar.HOUR_OF_DAY, 11);
        startTime.set(Calendar.MINUTE, 0);
        startTime.set(Calendar.MONTH, newMonth-1);
        startTime.set(Calendar.YEAR, newYear);
        endTime = (Calendar) startTime.clone();
        endTime.set(Calendar.HOUR_OF_DAY, 13);
        endTime.set(Calendar.MINUTE, 0);
        event = new WeekViewEvent(4,MainActivity.getEventTitle(startTime), startTime, endTime);
        event.setColor(color);
        events.add(event);
        
        //06.10.2015 16:00-19:00
        startTime = Calendar.getInstance();
        startTime.set(Calendar.DAY_OF_MONTH, 6);
        startTime.set(Calendar.HOUR_OF_DAY, 16);
        startTime.set(Calendar.MINUTE, 0);
        startTime.set(Calendar.MONTH, newMonth-1);
        startTime.set(Calendar.YEAR, newYear);
        endTime = (Calendar) startTime.clone();
        endTime.set(Calendar.HOUR_OF_DAY, 19);
        endTime.set(Calendar.MINUTE, 0);
        event = new WeekViewEvent(5,MainActivity.getEventTitle(startTime), startTime, endTime);
        event.setColor(color);
        events.add(event);
        
        //07.10.2015 10:00-10:30
        startTime = Calendar.getInstance();
        startTime.set(Calendar.DAY_OF_MONTH, 7);
        startTime.set(Calendar.HOUR_OF_DAY, 10);
        startTime.set(Calendar.MINUTE, 0);
        startTime.set(Calendar.MONTH, newMonth-1);
        startTime.set(Calendar.YEAR, newYear);
        endTime = (Calendar) startTime.clone();
        endTime.set(Calendar.HOUR_OF_DAY, 10);
        endTime.set(Calendar.MINUTE, 30);
        event = new WeekViewEvent(6,MainActivity.getEventTitle(startTime), startTime, endTime);
        event.setColor(color);
        events.add(event);
        
        //07.10.2015 13:30-14:00
        startTime = Calendar.getInstance();
        startTime.set(Calendar.DAY_OF_MONTH, 7);
        startTime.set(Calendar.HOUR_OF_DAY, 13);
        startTime.set(Calendar.MINUTE, 30);
        startTime.set(Calendar.MONTH, newMonth-1);
        startTime.set(Calendar.YEAR, newYear);
        endTime = (Calendar) startTime.clone();
        endTime.set(Calendar.HOUR_OF_DAY, 14);
        endTime.set(Calendar.MINUTE, 0);
        event = new WeekViewEvent(7,MainActivity.getEventTitle(startTime), startTime, endTime);
        event.setColor(color);
        events.add(event);
        
        //07.10.2015 17:00-19:00
        startTime = Calendar.getInstance();
        startTime.set(Calendar.DAY_OF_MONTH, 7);
        startTime.set(Calendar.HOUR_OF_DAY, 17);
        startTime.set(Calendar.MINUTE, 0);
        startTime.set(Calendar.MONTH, newMonth-1);
        startTime.set(Calendar.YEAR, newYear);
        endTime = (Calendar) startTime.clone();
        endTime.set(Calendar.HOUR_OF_DAY, 19);
        endTime.set(Calendar.MINUTE, 0);
        event = new WeekViewEvent(8,MainActivity.getEventTitle(startTime), startTime, endTime);
        event.setColor(color);
        events.add(event);
        
        //08.10.2015 9:00-10:00
        startTime = Calendar.getInstance();
        startTime.set(Calendar.DAY_OF_MONTH, 8);
        startTime.set(Calendar.HOUR_OF_DAY, 9);
        startTime.set(Calendar.MINUTE, 0);
        startTime.set(Calendar.MONTH, newMonth-1);
        startTime.set(Calendar.YEAR, newYear);
        endTime = (Calendar) startTime.clone();
        endTime.set(Calendar.HOUR_OF_DAY, 10);
        endTime.set(Calendar.MINUTE, 0);
        event = new WeekViewEvent(9,MainActivity.getEventTitle(startTime), startTime, endTime);
        event.setColor(color);
        events.add(event);
        
        //08.10.2015 13:00-14:00
        startTime = Calendar.getInstance();
        startTime.set(Calendar.DAY_OF_MONTH, 8);
        startTime.set(Calendar.HOUR_OF_DAY, 13);
        startTime.set(Calendar.MINUTE, 0);
        startTime.set(Calendar.MONTH, newMonth-1);
        startTime.set(Calendar.YEAR, newYear);
        endTime = (Calendar) startTime.clone();
        endTime.set(Calendar.HOUR_OF_DAY, 14);
        endTime.set(Calendar.MINUTE, 0);
        event = new WeekViewEvent(10,MainActivity.getEventTitle(startTime), startTime, endTime);
        event.setColor(color);
        events.add(event);
        
        //08.10.2015 14:30-16:00
        startTime = Calendar.getInstance();
        startTime.set(Calendar.DAY_OF_MONTH, 8);
        startTime.set(Calendar.HOUR_OF_DAY, 14);
        startTime.set(Calendar.MINUTE, 30);
        startTime.set(Calendar.MONTH, newMonth-1);
        startTime.set(Calendar.YEAR, newYear);
        endTime = (Calendar) startTime.clone();
        endTime.set(Calendar.HOUR_OF_DAY, 16);
        endTime.set(Calendar.MINUTE, 0);
        event = new WeekViewEvent(11,MainActivity.getEventTitle(startTime), startTime, endTime);
        event.setColor(color);
        events.add(event);
        
        //08.10.2015 18:30-19:00
        startTime = Calendar.getInstance();
        startTime.set(Calendar.DAY_OF_MONTH, 8);
        startTime.set(Calendar.HOUR_OF_DAY, 18);
        startTime.set(Calendar.MINUTE, 30);
        startTime.set(Calendar.MONTH, newMonth-1);
        startTime.set(Calendar.YEAR, newYear);
        endTime = (Calendar) startTime.clone();
        endTime.set(Calendar.HOUR_OF_DAY, 19);
        endTime.set(Calendar.MINUTE, 0);
        event = new WeekViewEvent(12,MainActivity.getEventTitle(startTime), startTime, endTime);
        event.setColor(color);
        events.add(event);
        
        //09.10.2015 9:00-10:30
        startTime = Calendar.getInstance();
        startTime.set(Calendar.DAY_OF_MONTH, 9);
        startTime.set(Calendar.HOUR_OF_DAY, 9);
        startTime.set(Calendar.MINUTE, 0);
        startTime.set(Calendar.MONTH, newMonth-1);
        startTime.set(Calendar.YEAR, newYear);
        endTime = (Calendar) startTime.clone();
        endTime.set(Calendar.HOUR_OF_DAY, 10);
        endTime.set(Calendar.MINUTE, 30);
        event = new WeekViewEvent(13,MainActivity.getEventTitle(startTime), startTime, endTime);
        event.setColor(color);
        events.add(event);
        
        //09.10.2015 12:30-16:00
        startTime = Calendar.getInstance();
        startTime.set(Calendar.DAY_OF_MONTH, 9);
        startTime.set(Calendar.HOUR_OF_DAY, 12);
        startTime.set(Calendar.MINUTE, 30);
        startTime.set(Calendar.MONTH, newMonth-1);
        startTime.set(Calendar.YEAR, newYear);
        endTime = (Calendar) startTime.clone();
        endTime.set(Calendar.HOUR_OF_DAY, 16);
        endTime.set(Calendar.MINUTE, 0);
        event = new WeekViewEvent(14,MainActivity.getEventTitle(startTime), startTime, endTime);
        event.setColor(color);
        events.add(event);
        
        //12.10.2015 9:00-10:30
        startTime = Calendar.getInstance();
        startTime.set(Calendar.DAY_OF_MONTH, 12);
        startTime.set(Calendar.HOUR_OF_DAY, 9);
        startTime.set(Calendar.MINUTE, 0);
        startTime.set(Calendar.MONTH, newMonth-1);
        startTime.set(Calendar.YEAR, newYear);
        endTime = (Calendar) startTime.clone();
        endTime.set(Calendar.HOUR_OF_DAY, 10);
        endTime.set(Calendar.MINUTE, 30);
        event = new WeekViewEvent(15,MainActivity.getEventTitle(startTime), startTime, endTime);
        event.setColor(color);
        events.add(event);
        
        //12.10.2015 18:30-19:00
        startTime = Calendar.getInstance();
        startTime.set(Calendar.DAY_OF_MONTH, 12);
        startTime.set(Calendar.HOUR_OF_DAY, 18);
        startTime.set(Calendar.MINUTE, 30);
        startTime.set(Calendar.MONTH, newMonth-1);
        startTime.set(Calendar.YEAR, newYear);
        endTime = (Calendar) startTime.clone();
        endTime.set(Calendar.HOUR_OF_DAY, 19);
        endTime.set(Calendar.MINUTE, 0);
        event = new WeekViewEvent(16,MainActivity.getEventTitle(startTime), startTime, endTime);
        event.setColor(color);
        events.add(event);
        
        //13.10.2015 9:00-10:00
        startTime = Calendar.getInstance();
        startTime.set(Calendar.DAY_OF_MONTH, 13);
        startTime.set(Calendar.HOUR_OF_DAY, 9);
        startTime.set(Calendar.MINUTE, 0);
        startTime.set(Calendar.MONTH, newMonth-1);
        startTime.set(Calendar.YEAR, newYear);
        endTime = (Calendar) startTime.clone();
        endTime.set(Calendar.HOUR_OF_DAY, 10);
        endTime.set(Calendar.MINUTE, 0);
        event = new WeekViewEvent(17,MainActivity.getEventTitle(startTime), startTime, endTime);
        event.setColor(color);
        events.add(event);
        
        //13.10.2015 15:00-18:00
        startTime = Calendar.getInstance();
        startTime.set(Calendar.DAY_OF_MONTH, 13);
        startTime.set(Calendar.HOUR_OF_DAY, 15);
        startTime.set(Calendar.MINUTE, 0);
        startTime.set(Calendar.MONTH, newMonth-1);
        startTime.set(Calendar.YEAR, newYear);
        endTime = (Calendar) startTime.clone();
        endTime.set(Calendar.HOUR_OF_DAY, 18);
        endTime.set(Calendar.MINUTE, 0);
        event = new WeekViewEvent(18,MainActivity.getEventTitle(startTime), startTime, endTime);
        event.setColor(color);
        events.add(event);
        
        //14.10.2015 9:00-11:30
        startTime = Calendar.getInstance();
        startTime.set(Calendar.DAY_OF_MONTH, 14);
        startTime.set(Calendar.HOUR_OF_DAY, 9);
        startTime.set(Calendar.MINUTE, 0);
        startTime.set(Calendar.MONTH, newMonth-1);
        startTime.set(Calendar.YEAR, newYear);
        endTime = (Calendar) startTime.clone();
        endTime.set(Calendar.HOUR_OF_DAY, 11);
        endTime.set(Calendar.MINUTE, 30);
        event = new WeekViewEvent(19,MainActivity.getEventTitle(startTime), startTime, endTime);
        event.setColor(color);
        events.add(event);
        
        //14.10.2015 18:00-19:00
        startTime = Calendar.getInstance();
        startTime.set(Calendar.DAY_OF_MONTH, 14);
        startTime.set(Calendar.HOUR_OF_DAY, 18);
        startTime.set(Calendar.MINUTE, 0);
        startTime.set(Calendar.MONTH, newMonth-1);
        startTime.set(Calendar.YEAR, newYear);
        endTime = (Calendar) startTime.clone();
        endTime.set(Calendar.HOUR_OF_DAY, 19);
        endTime.set(Calendar.MINUTE, 0);
        event = new WeekViewEvent(20,MainActivity.getEventTitle(startTime), startTime, endTime);
        event.setColor(color);
        events.add(event);
        
        //15.10.2015 10:00-11:00
        startTime = Calendar.getInstance();
        startTime.set(Calendar.DAY_OF_MONTH, 15);
        startTime.set(Calendar.HOUR_OF_DAY, 10);
        startTime.set(Calendar.MINUTE, 0);
        startTime.set(Calendar.MONTH, newMonth-1);
        startTime.set(Calendar.YEAR, newYear);
        endTime = (Calendar) startTime.clone();
        endTime.set(Calendar.HOUR_OF_DAY, 11);
        endTime.set(Calendar.MINUTE, 0);
        event = new WeekViewEvent(21,MainActivity.getEventTitle(startTime), startTime, endTime);
        event.setColor(color);
        events.add(event);
        
        //15.10.2015 12:00-13:00
        startTime = Calendar.getInstance();
        startTime.set(Calendar.DAY_OF_MONTH, 15);
        startTime.set(Calendar.HOUR_OF_DAY, 12);
        startTime.set(Calendar.MINUTE, 0);
        startTime.set(Calendar.MONTH, newMonth-1);
        startTime.set(Calendar.YEAR, newYear);
        endTime = (Calendar) startTime.clone();
        endTime.set(Calendar.HOUR_OF_DAY, 13);
        endTime.set(Calendar.MINUTE, 0);
        event = new WeekViewEvent(22,MainActivity.getEventTitle(startTime), startTime, endTime);
        event.setColor(color);
        events.add(event);
        
        //15.10.2015 14:30-15:00
        startTime = Calendar.getInstance();
        startTime.set(Calendar.DAY_OF_MONTH, 15);
        startTime.set(Calendar.HOUR_OF_DAY, 14);
        startTime.set(Calendar.MINUTE, 30);
        startTime.set(Calendar.MONTH, newMonth-1);
        startTime.set(Calendar.YEAR, newYear);
        endTime = (Calendar) startTime.clone();
        endTime.set(Calendar.HOUR_OF_DAY, 15);
        endTime.set(Calendar.MINUTE, 0);
        event = new WeekViewEvent(23,MainActivity.getEventTitle(startTime), startTime, endTime);
        event.setColor(color);
        events.add(event);
        
        //15.10.2015 17:00-19:00
        startTime = Calendar.getInstance();
        startTime.set(Calendar.DAY_OF_MONTH, 15);
        startTime.set(Calendar.HOUR_OF_DAY, 17);
        startTime.set(Calendar.MINUTE, 0);
        startTime.set(Calendar.MONTH, newMonth-1);
        startTime.set(Calendar.YEAR, newYear);
        endTime = (Calendar) startTime.clone();
        endTime.set(Calendar.HOUR_OF_DAY, 19);
        endTime.set(Calendar.MINUTE, 0);
        event = new WeekViewEvent(24,MainActivity.getEventTitle(startTime), startTime, endTime);
        event.setColor(color);
        events.add(event);
        
        //16.10.2015 9:30-11:00
        startTime = Calendar.getInstance();
        startTime.set(Calendar.DAY_OF_MONTH, 16);
        startTime.set(Calendar.HOUR_OF_DAY, 9);
        startTime.set(Calendar.MINUTE, 30);
        startTime.set(Calendar.MONTH, newMonth-1);
        startTime.set(Calendar.YEAR, newYear);
        endTime = (Calendar) startTime.clone();
        endTime.set(Calendar.HOUR_OF_DAY, 11);
        endTime.set(Calendar.MINUTE, 0);
        event = new WeekViewEvent(25,MainActivity.getEventTitle(startTime), startTime, endTime);
        event.setColor(color);
        events.add(event);
        
        //16.10.2015 15:30-19:00
        startTime = Calendar.getInstance();
        startTime.set(Calendar.DAY_OF_MONTH, 16);
        startTime.set(Calendar.HOUR_OF_DAY, 15);
        startTime.set(Calendar.MINUTE, 30);
        startTime.set(Calendar.MONTH, newMonth-1);
        startTime.set(Calendar.YEAR, newYear);
        endTime = (Calendar) startTime.clone();
        endTime.set(Calendar.HOUR_OF_DAY, 19);
        endTime.set(Calendar.MINUTE, 0);
        event = new WeekViewEvent(26,MainActivity.getEventTitle(startTime), startTime, endTime);
        event.setColor(color);
        events.add(event);

        return events;
    }

    private ArrayList<WeekViewEvent> createListU1_1_3() {

        ArrayList<WeekViewEvent> events = new ArrayList<WeekViewEvent>();

        int color = Color.parseColor("#f8b552");
        int newMonth = 10; // October
        int newYear = 2015;

        //05.10.2015 9:00-9:30
        Calendar startTime = Calendar.getInstance();
        startTime.set(Calendar.DAY_OF_MONTH, 5);
        startTime.set(Calendar.HOUR_OF_DAY, 9);
        startTime.set(Calendar.MINUTE, 0);
        startTime.set(Calendar.MONTH, newMonth-1);
        startTime.set(Calendar.YEAR, newYear);
        Calendar endTime = (Calendar) startTime.clone();
        endTime.set(Calendar.HOUR_OF_DAY, 9);
        endTime.set(Calendar.MINUTE, 30);
        WeekViewEvent event = new WeekViewEvent(1, MainActivity.getEventTitle(startTime), startTime, endTime);
        event.setColor(color);
        events.add(event);

        //05.10.2015 13:00-13:30
        startTime = Calendar.getInstance();
        startTime.set(Calendar.DAY_OF_MONTH, 5);
        startTime.set(Calendar.HOUR_OF_DAY, 13);
        startTime.set(Calendar.MINUTE, 0);
        startTime.set(Calendar.MONTH, newMonth-1);
        startTime.set(Calendar.YEAR, newYear);
        endTime = (Calendar) startTime.clone();
        endTime.set(Calendar.HOUR_OF_DAY, 13);
        endTime.set(Calendar.MINUTE, 30);
        event = new WeekViewEvent(2, MainActivity.getEventTitle(startTime), startTime, endTime);
        event.setColor(color);
        events.add(event);

        //05.10.2015 17:00-18:30
        startTime = Calendar.getInstance();
        startTime.set(Calendar.DAY_OF_MONTH, 5);
        startTime.set(Calendar.HOUR_OF_DAY, 17);
        startTime.set(Calendar.MINUTE, 0);
        startTime.set(Calendar.MONTH, newMonth-1);
        startTime.set(Calendar.YEAR, newYear);
        endTime = (Calendar) startTime.clone();
        endTime.set(Calendar.HOUR_OF_DAY, 18);
        endTime.set(Calendar.MINUTE, 30);
        event = new WeekViewEvent(3, MainActivity.getEventTitle(startTime), startTime, endTime);
        event.setColor(color);
        events.add(event);

        //06.10.2015 11:30-12:00
        startTime = Calendar.getInstance();
        startTime.set(Calendar.DAY_OF_MONTH, 6);
        startTime.set(Calendar.HOUR_OF_DAY, 11);
        startTime.set(Calendar.MINUTE, 30);
        startTime.set(Calendar.MONTH, newMonth-1);
        startTime.set(Calendar.YEAR, newYear);
        endTime = (Calendar) startTime.clone();
        endTime.set(Calendar.HOUR_OF_DAY, 12);
        endTime.set(Calendar.MINUTE, 0);
        event = new WeekViewEvent(4, MainActivity.getEventTitle(startTime), startTime, endTime);
        event.setColor(color);
        events.add(event);

        //06.10.2015 16:00-17:00
        startTime = Calendar.getInstance();
        startTime.set(Calendar.DAY_OF_MONTH, 6);
        startTime.set(Calendar.HOUR_OF_DAY, 16);
        startTime.set(Calendar.MINUTE, 0);
        startTime.set(Calendar.MONTH, newMonth-1);
        startTime.set(Calendar.YEAR, newYear);
        endTime = (Calendar) startTime.clone();
        endTime.set(Calendar.HOUR_OF_DAY, 17);
        endTime.set(Calendar.MINUTE, 0);
        event = new WeekViewEvent(5, MainActivity.getEventTitle(startTime), startTime, endTime);
        event.setColor(color);
        events.add(event);

        //06.10.2015 18:30-19:00
        startTime = Calendar.getInstance();
        startTime.set(Calendar.DAY_OF_MONTH, 6);
        startTime.set(Calendar.HOUR_OF_DAY, 18);
        startTime.set(Calendar.MINUTE, 30);
        startTime.set(Calendar.MONTH, newMonth-1);
        startTime.set(Calendar.YEAR, newYear);
        endTime = (Calendar) startTime.clone();
        endTime.set(Calendar.HOUR_OF_DAY, 19);
        endTime.set(Calendar.MINUTE, 0);
        event = new WeekViewEvent(6, MainActivity.getEventTitle(startTime), startTime, endTime);
        event.setColor(color);
        events.add(event);

        //07.10.2015 13:30-14:00
        startTime = Calendar.getInstance();
        startTime.set(Calendar.DAY_OF_MONTH, 7);
        startTime.set(Calendar.HOUR_OF_DAY, 13);
        startTime.set(Calendar.MINUTE, 30);
        startTime.set(Calendar.MONTH, newMonth-1);
        startTime.set(Calendar.YEAR, newYear);
        endTime = (Calendar) startTime.clone();
        endTime.set(Calendar.HOUR_OF_DAY, 14);
        endTime.set(Calendar.MINUTE, 0);
        event = new WeekViewEvent(7, MainActivity.getEventTitle(startTime), startTime, endTime);
        event.setColor(color);
        events.add(event);

        //07.10.2015 17:30-19:00
        startTime = Calendar.getInstance();
        startTime.set(Calendar.DAY_OF_MONTH, 7);
        startTime.set(Calendar.HOUR_OF_DAY, 17);
        startTime.set(Calendar.MINUTE, 30);
        startTime.set(Calendar.MONTH, newMonth-1);
        startTime.set(Calendar.YEAR, newYear);
        endTime = (Calendar) startTime.clone();
        endTime.set(Calendar.HOUR_OF_DAY, 19);
        endTime.set(Calendar.MINUTE, 0);
        event = new WeekViewEvent(8, MainActivity.getEventTitle(startTime), startTime, endTime);
        event.setColor(color);
        events.add(event);

        //08.10.2015 9:30-10:00
        startTime = Calendar.getInstance();
        startTime.set(Calendar.DAY_OF_MONTH, 8);
        startTime.set(Calendar.HOUR_OF_DAY, 9);
        startTime.set(Calendar.MINUTE, 30);
        startTime.set(Calendar.MONTH, newMonth-1);
        startTime.set(Calendar.YEAR, newYear);
        endTime = (Calendar) startTime.clone();
        endTime.set(Calendar.HOUR_OF_DAY, 10);
        endTime.set(Calendar.MINUTE, 0);
        event = new WeekViewEvent(9, MainActivity.getEventTitle(startTime), startTime, endTime);
        event.setColor(color);
        events.add(event);

        //08.10.2015 15:00-16:00
        startTime = Calendar.getInstance();
        startTime.set(Calendar.DAY_OF_MONTH, 8);
        startTime.set(Calendar.HOUR_OF_DAY, 15);
        startTime.set(Calendar.MINUTE, 0);
        startTime.set(Calendar.MONTH, newMonth-1);
        startTime.set(Calendar.YEAR, newYear);
        endTime = (Calendar) startTime.clone();
        endTime.set(Calendar.HOUR_OF_DAY, 16);
        endTime.set(Calendar.MINUTE, 0);
        event = new WeekViewEvent(10, MainActivity.getEventTitle(startTime), startTime, endTime);
        event.setColor(color);
        events.add(event);

        //08.10.2015 18:30-19:00
        startTime = Calendar.getInstance();
        startTime.set(Calendar.DAY_OF_MONTH, 8);
        startTime.set(Calendar.HOUR_OF_DAY, 18);
        startTime.set(Calendar.MINUTE, 30);
        startTime.set(Calendar.MONTH, newMonth-1);
        startTime.set(Calendar.YEAR, newYear);
        endTime = (Calendar) startTime.clone();
        endTime.set(Calendar.HOUR_OF_DAY, 19);
        endTime.set(Calendar.MINUTE, 0);
        event = new WeekViewEvent(11, MainActivity.getEventTitle(startTime), startTime, endTime);
        event.setColor(color);
        events.add(event);

        //09.10.2015 9:30-10:30
        startTime = Calendar.getInstance();
        startTime.set(Calendar.DAY_OF_MONTH, 9);
        startTime.set(Calendar.HOUR_OF_DAY, 9);
        startTime.set(Calendar.MINUTE, 30);
        startTime.set(Calendar.MONTH, newMonth-1);
        startTime.set(Calendar.YEAR, newYear);
        endTime = (Calendar) startTime.clone();
        endTime.set(Calendar.HOUR_OF_DAY, 10);
        endTime.set(Calendar.MINUTE, 30);
        event = new WeekViewEvent(12, MainActivity.getEventTitle(startTime), startTime, endTime);
        event.setColor(color);
        events.add(event);

        //09.10.2015 13:30-14:30
        startTime = Calendar.getInstance();
        startTime.set(Calendar.DAY_OF_MONTH, 9);
        startTime.set(Calendar.HOUR_OF_DAY, 13);
        startTime.set(Calendar.MINUTE, 30);
        startTime.set(Calendar.MONTH, newMonth-1);
        startTime.set(Calendar.YEAR, newYear);
        endTime = (Calendar) startTime.clone();
        endTime.set(Calendar.HOUR_OF_DAY, 14);
        endTime.set(Calendar.MINUTE, 30);
        event = new WeekViewEvent(13, MainActivity.getEventTitle(startTime), startTime, endTime);
        event.setColor(color);
        events.add(event);

        //12.10.2015 9:00-10:00
        startTime = Calendar.getInstance();
        startTime.set(Calendar.DAY_OF_MONTH, 12);
        startTime.set(Calendar.HOUR_OF_DAY, 9);
        startTime.set(Calendar.MINUTE, 0);
        startTime.set(Calendar.MONTH, newMonth-1);
        startTime.set(Calendar.YEAR, newYear);
        endTime = (Calendar) startTime.clone();
        endTime.set(Calendar.HOUR_OF_DAY, 10);
        endTime.set(Calendar.MINUTE, 0);
        event = new WeekViewEvent(14, MainActivity.getEventTitle(startTime), startTime, endTime);
        event.setColor(color);
        events.add(event);

        //12.10.2015 18:30-19:00
        startTime = Calendar.getInstance();
        startTime.set(Calendar.DAY_OF_MONTH, 12);
        startTime.set(Calendar.HOUR_OF_DAY, 18);
        startTime.set(Calendar.MINUTE, 30);
        startTime.set(Calendar.MONTH, newMonth-1);
        startTime.set(Calendar.YEAR, newYear);
        endTime = (Calendar) startTime.clone();
        endTime.set(Calendar.HOUR_OF_DAY, 19);
        endTime.set(Calendar.MINUTE, 0);
        event = new WeekViewEvent(15, MainActivity.getEventTitle(startTime), startTime, endTime);
        event.setColor(color);
        events.add(event);

        //13.10.2015 9:00-9:30
        startTime = Calendar.getInstance();
        startTime.set(Calendar.DAY_OF_MONTH, 13);
        startTime.set(Calendar.HOUR_OF_DAY, 9);
        startTime.set(Calendar.MINUTE, 0);
        startTime.set(Calendar.MONTH, newMonth-1);
        startTime.set(Calendar.YEAR, newYear);
        endTime = (Calendar) startTime.clone();
        endTime.set(Calendar.HOUR_OF_DAY, 9);
        endTime.set(Calendar.MINUTE, 30);
        event = new WeekViewEvent(16, MainActivity.getEventTitle(startTime), startTime, endTime);
        event.setColor(color);
        events.add(event);

        //13.10.2015 16:00-17:00
        startTime = Calendar.getInstance();
        startTime.set(Calendar.DAY_OF_MONTH, 13);
        startTime.set(Calendar.HOUR_OF_DAY, 16);
        startTime.set(Calendar.MINUTE, 0);
        startTime.set(Calendar.MONTH, newMonth-1);
        startTime.set(Calendar.YEAR, newYear);
        endTime = (Calendar) startTime.clone();
        endTime.set(Calendar.HOUR_OF_DAY, 17);
        endTime.set(Calendar.MINUTE, 0);
        event = new WeekViewEvent(17, MainActivity.getEventTitle(startTime), startTime, endTime);
        event.setColor(color);
        events.add(event);

        //14.10.2015 10:00-11:00
        startTime = Calendar.getInstance();
        startTime.set(Calendar.DAY_OF_MONTH, 14);
        startTime.set(Calendar.HOUR_OF_DAY, 10);
        startTime.set(Calendar.MINUTE, 0);
        startTime.set(Calendar.MONTH, newMonth-1);
        startTime.set(Calendar.YEAR, newYear);
        endTime = (Calendar) startTime.clone();
        endTime.set(Calendar.HOUR_OF_DAY, 11);
        endTime.set(Calendar.MINUTE, 0);
        event = new WeekViewEvent(18, MainActivity.getEventTitle(startTime), startTime, endTime);
        event.setColor(color);
        events.add(event);

        //15.10.2015 10:30-11:30
        startTime = Calendar.getInstance();
        startTime.set(Calendar.DAY_OF_MONTH, 15);
        startTime.set(Calendar.HOUR_OF_DAY, 10);
        startTime.set(Calendar.MINUTE, 30);
        startTime.set(Calendar.MONTH, newMonth-1);
        startTime.set(Calendar.YEAR, newYear);
        endTime = (Calendar) startTime.clone();
        endTime.set(Calendar.HOUR_OF_DAY, 11);
        endTime.set(Calendar.MINUTE, 30);
        event = new WeekViewEvent(19, MainActivity.getEventTitle(startTime), startTime, endTime);
        event.setColor(color);
        events.add(event);

        //15.10.2015 14:30-15:00
        startTime = Calendar.getInstance();
        startTime.set(Calendar.DAY_OF_MONTH, 15);
        startTime.set(Calendar.HOUR_OF_DAY, 14);
        startTime.set(Calendar.MINUTE, 30);
        startTime.set(Calendar.MONTH, newMonth-1);
        startTime.set(Calendar.YEAR, newYear);
        endTime = (Calendar) startTime.clone();
        endTime.set(Calendar.HOUR_OF_DAY, 15);
        endTime.set(Calendar.MINUTE, 0);
        event = new WeekViewEvent(20, MainActivity.getEventTitle(startTime), startTime, endTime);
        event.setColor(color);
        events.add(event);

        //15.10.2015 18:00-19:00
        startTime = Calendar.getInstance();
        startTime.set(Calendar.DAY_OF_MONTH, 15);
        startTime.set(Calendar.HOUR_OF_DAY, 18);
        startTime.set(Calendar.MINUTE, 0);
        startTime.set(Calendar.MONTH, newMonth-1);
        startTime.set(Calendar.YEAR, newYear);
        endTime = (Calendar) startTime.clone();
        endTime.set(Calendar.HOUR_OF_DAY, 19);
        endTime.set(Calendar.MINUTE, 0);
        event = new WeekViewEvent(21, MainActivity.getEventTitle(startTime), startTime, endTime);
        event.setColor(color);
        events.add(event);

        //16.10.2015 9:30-10:30
        startTime = Calendar.getInstance();
        startTime.set(Calendar.DAY_OF_MONTH, 16);
        startTime.set(Calendar.HOUR_OF_DAY, 9);
        startTime.set(Calendar.MINUTE, 30);
        startTime.set(Calendar.MONTH, newMonth-1);
        startTime.set(Calendar.YEAR, newYear);
        endTime = (Calendar) startTime.clone();
        endTime.set(Calendar.HOUR_OF_DAY, 10);
        endTime.set(Calendar.MINUTE, 30);
        event = new WeekViewEvent(22, MainActivity.getEventTitle(startTime), startTime, endTime);
        event.setColor(color);
        events.add(event);

        //16.10.2015 17:30-19:00
        startTime = Calendar.getInstance();
        startTime.set(Calendar.DAY_OF_MONTH, 16);
        startTime.set(Calendar.HOUR_OF_DAY, 17);
        startTime.set(Calendar.MINUTE, 30);
        startTime.set(Calendar.MONTH, newMonth-1);
        startTime.set(Calendar.YEAR, newYear);
        endTime = (Calendar) startTime.clone();
        endTime.set(Calendar.HOUR_OF_DAY, 19);
        endTime.set(Calendar.MINUTE, 0);
        event = new WeekViewEvent(23, MainActivity.getEventTitle(startTime), startTime, endTime);
        event.setColor(color);
        events.add(event);

        return events;
    }

}
