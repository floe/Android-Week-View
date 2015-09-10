package com.alamkanak.weekview.sample;

import android.graphics.Color;
import android.util.Log;

import com.alamkanak.weekview.WeekViewEvent;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

public class EventGenerator {

    public static int counter = 1;

    public EventGenerator() {
        eventsU1_1_3 = createListU1_1_3();
        eventsU1_2_3 = createListU1_2_3();
        eventsU2_1_3 = createListU2_1_3();
        eventsU2_2_3 = createListU2_2_3();

        eventsF1_1_3 = flip(eventsU1_1_3);
        eventsF1_2_3 = flip(eventsU1_2_3);
        eventsF2_1_3 = flip(eventsU2_1_3);
        eventsF2_2_3 = flip(eventsU2_2_3);
    }

    List<WeekViewEvent> getEvents(int year, int month, int user, int fill, boolean flip) {
        if (year != 2015 || month != 10) return new ArrayList<>();

        if (!flip) {
            if (user == 1 && fill == 1) return eventsU1_1_3;
            if (user == 1 && fill == 2) return eventsU1_2_3;

            if (user == 2 && fill == 1) return eventsU2_1_3;
            if (user == 2 && fill == 2) return eventsU2_2_3;
        } else {
            if (user == 1 && fill == 1) return eventsF1_1_3;
            if (user == 1 && fill == 2) return eventsF1_2_3;

            if (user == 2 && fill == 1) return eventsF2_1_3;
            if (user == 2 && fill == 2) return eventsF2_2_3;
        }

        return new ArrayList<>();
    }

    private void createEvent(ArrayList<WeekViewEvent> list, int color, int day, int newMonth, int newYear, int sh, int sm, int eh, int em) {
        Calendar startTime = Calendar.getInstance();
        startTime.set(Calendar.DAY_OF_MONTH,day);
        startTime.set(Calendar.HOUR_OF_DAY,sh);
        startTime.set(Calendar.MINUTE,sm);
        startTime.set(Calendar.MONTH,newMonth-1);
        startTime.set(Calendar.YEAR,newYear);
        Calendar endTime = (Calendar) startTime.clone();
        endTime.set(Calendar.HOUR_OF_DAY,eh);
        endTime.set(Calendar.MINUTE,em);
        WeekViewEvent event = new WeekViewEvent(counter++, MainActivity.getEventTitle(startTime), startTime, endTime);
        event.setColor(color);
        list.add(event);
    }

    private ArrayList<WeekViewEvent> flip(ArrayList<WeekViewEvent> list) {

        Calendar startpoint = Calendar.getInstance();
        startpoint.set(2015,9,5,9,0);
        Calendar endpoint = Calendar.getInstance();
        endpoint.set(2015, 9, 16, 19, 0);

        ArrayList<WeekViewEvent> result = new ArrayList<WeekViewEvent>();

        for (WeekViewEvent event: list) {

            Calendar newEnd = (Calendar) event.getStartTime().clone();
            Calendar newStart = (Calendar) event.getEndTime().clone();

            long diff = endpoint.getTimeInMillis() - newEnd.getTimeInMillis();
            newEnd.setTimeInMillis(startpoint.getTimeInMillis()+diff);

            diff = endpoint.getTimeInMillis() - newStart.getTimeInMillis();
            newStart.setTimeInMillis(startpoint.getTimeInMillis()+diff);

            WeekViewEvent newEvent = new WeekViewEvent(event.getId(),MainActivity.getEventTitle(newStart),newStart,newEnd);
            result.add(0,newEvent);
        }

        return result;
    }

    // appointments are Oct 5 - Oct 9, Oct 12 - Oct 16
    // none on weekends, time range is 9:00 - 19:00

    // checked
    private ArrayList<WeekViewEvent> createListU2_2_3() {

        ArrayList<WeekViewEvent> events = new ArrayList<WeekViewEvent>();

        int color = Color.parseColor("#59dbe0");

        createEvent(events,color, 5,10,2015, 9,00,11,00);
        createEvent(events,color, 5,10,2015,15,30,17,30);
        createEvent(events,color, 6,10,2015, 9,00,10,00);
        createEvent(events,color, 6,10,2015,16,30,18,30);
        createEvent(events,color, 7,10,2015,10,00,11,00);
        createEvent(events,color, 7,10,2015,15,00,16,30);
        createEvent(events,color, 8,10,2015, 9,00,11,00);
        createEvent(events,color, 8,10,2015,13,00,15,00);
        createEvent(events,color, 9,10,2015,10,00,12,30);
        createEvent(events,color, 9,10,2015,16,00,19,00);
        createEvent(events,color,12,10,2015, 9,30,11,00);
        createEvent(events,color,12,10,2015,14,00,17,00);
        createEvent(events,color,13,10,2015, 9,30,10,30);
        createEvent(events,color,13,10,2015,11,30,13,00);
        createEvent(events,color,13,10,2015,15,00,16,00);
        createEvent(events,color,14,10,2015, 9,30,10,30);
        createEvent(events,color,14,10,2015,13,30,17,00);
        createEvent(events,color,15,10,2015, 9,00, 9,30);
        createEvent(events,color,15,10,2015,11,00,14,00);
        createEvent(events,color,16,10,2015,10,30,12,30);
        createEvent(events,color,16,10,2015,15,00,18,00);

        return events;
    }

    // checked
    private ArrayList<WeekViewEvent> createListU2_1_3() {

        ArrayList<WeekViewEvent> events = new ArrayList<WeekViewEvent>();

        int color = Color.parseColor("#f57f68");

        createEvent(events,color, 5,10,2015, 9,00,10,00);
        createEvent(events,color, 5,10,2015,16,30,17,30);
        createEvent(events,color, 6,10,2015, 9,30,10,00);
        createEvent(events,color, 6,10,2015,16,30,17,00);
        createEvent(events,color, 7,10,2015,10,30,11,00);
        createEvent(events,color, 7,10,2015,15,30,16,30);
        createEvent(events,color, 8,10,2015, 9,30,11,00);
        createEvent(events,color, 8,10,2015,13,00,14,30);
        createEvent(events,color, 9,10,2015,10,00,11,00);
        createEvent(events,color, 9,10,2015,17,30,19,00);
        createEvent(events,color,12,10,2015, 9,30,10,00);
        createEvent(events,color,12,10,2015,15,00,16,00);
        createEvent(events,color,13,10,2015, 9,30,10,00);
        createEvent(events,color,13,10,2015,14,00,15,00);
        createEvent(events,color,14,10,2015,10,00,10,30);
        createEvent(events,color,14,10,2015,13,00,14,30);
        createEvent(events,color,15,10,2015, 9,00, 9,30);
        createEvent(events,color,15,10,2015,11,00,13,00);
        createEvent(events,color,16,10,2015,11,30,12,30);
        createEvent(events,color,16,10,2015,15,00,15,30);
        createEvent(events,color,16,10,2015,17,30,18,30);
        
        return events;
    }

    // checked
    private ArrayList<WeekViewEvent> createListU1_2_3() {

        ArrayList<WeekViewEvent> events = new ArrayList<WeekViewEvent>();

        int color = Color.parseColor("#87d288");

        createEvent(events,color, 5,10,2015, 9,00, 9,30);
        createEvent(events,color, 5,10,2015,12,00,13,30);
        createEvent(events,color, 5,10,2015,17,00,19,00);
        createEvent(events,color, 6,10,2015,11,00,13,00);
        createEvent(events,color, 6,10,2015,16,00,19,00);
        createEvent(events,color, 7,10,2015,10,00,10,30);
        createEvent(events,color, 7,10,2015,13,30,14,00);
        createEvent(events,color, 7,10,2015,17,00,19,00);
        createEvent(events,color, 8,10,2015, 9,30,12,00);
        createEvent(events,color, 8,10,2015,13,00,14,00);
        createEvent(events,color, 8,10,2015,18,00,18,30);
        createEvent(events,color, 9,10,2015, 9,00,10,30);
        createEvent(events,color, 9,10,2015,12,30,16,00);
        createEvent(events,color,12,10,2015, 9,30,11,00);
        createEvent(events,color,12,10,2015,18,30,19,00);
        createEvent(events,color,13,10,2015, 9,30,10,30);
        createEvent(events,color,13,10,2015,16,00,18,30);
        createEvent(events,color,14,10,2015, 9,00,11,30);
        createEvent(events,color,14,10,2015,18,00,19,00);
        createEvent(events,color,15,10,2015,10,00,11,00);
        createEvent(events,color,15,10,2015,12,00,13,00);
        createEvent(events,color,15,10,2015,14,30,15,00);
        createEvent(events,color,15,10,2015,17,00,19,00);
        createEvent(events,color,16,10,2015, 9,30,11,00);
        createEvent(events,color,16,10,2015,15,30,19,00);

        return events;
    }

    // checked
    private ArrayList<WeekViewEvent> createListU1_1_3() {

        ArrayList<WeekViewEvent> events = new ArrayList<WeekViewEvent>();

        int color = Color.parseColor("#f8b552");

        createEvent(events,color, 5,10,2015, 9,00, 9,30);
        createEvent(events,color, 5,10,2015,13,00,13,30);
        createEvent(events,color, 5,10,2015,17,00,18,30);
        createEvent(events,color, 6,10,2015,11,00,12,00);
        createEvent(events,color, 6,10,2015,16,00,17,00);
        createEvent(events,color, 6,10,2015,18,30,19,00);
        createEvent(events,color, 7,10,2015,13,30,14,00);
        createEvent(events,color, 7,10,2015,17,30,19,00);
        createEvent(events,color, 8,10,2015, 9,30,10,00);
        createEvent(events,color, 8,10,2015,13,00,14,00);
        createEvent(events,color, 8,10,2015,18,00,18,30);
        createEvent(events,color, 9,10,2015, 9,30,10,30);
        createEvent(events,color, 9,10,2015,13,30,14,30);
        createEvent(events,color,12,10,2015, 9,30,10,30);
        createEvent(events,color,12,10,2015,18,30,19,00);
        createEvent(events,color,13,10,2015, 9,30,10,00);
        createEvent(events,color,13,10,2015,16,00,17,00);
        createEvent(events,color,14,10,2015,10,00,11,00);
        createEvent(events,color,15,10,2015,10,30,11,30);
        createEvent(events,color,15,10,2015,14,30,15,00);
        createEvent(events,color,15,10,2015,18,00,19,00);
        createEvent(events,color,16,10,2015, 9,30,10,30);
        createEvent(events,color,16,10,2015,17,30,19,00);

        return events;
    }

    private ArrayList<WeekViewEvent> eventsU1_1_3;
    private ArrayList<WeekViewEvent> eventsU1_2_3;
    private ArrayList<WeekViewEvent> eventsU2_1_3;
    private ArrayList<WeekViewEvent> eventsU2_2_3;

    private ArrayList<WeekViewEvent> eventsF1_1_3;
    private ArrayList<WeekViewEvent> eventsF1_2_3;
    private ArrayList<WeekViewEvent> eventsF2_1_3;
    private ArrayList<WeekViewEvent> eventsF2_2_3;
}
