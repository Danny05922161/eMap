package com.esunbank.emap;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.esunbank.emap.DTO.Appointment;
import com.esunbank.emap.canlendar.DrawableCalendarEvent;
import com.esunbank.emap.canlendar.DrawableEventRenderer;
import com.esunbank.emap.database.DataStore;
import com.github.tibolte.agendacalendarview.AgendaCalendarView;
import com.github.tibolte.agendacalendarview.CalendarManager;
import com.github.tibolte.agendacalendarview.CalendarPickerController;
import com.github.tibolte.agendacalendarview.models.BaseCalendarEvent;
import com.github.tibolte.agendacalendarview.models.CalendarEvent;
import com.github.tibolte.agendacalendarview.models.DayItem;
import com.esunbank.emap.canlendar.IDayItem;
import com.esunbank.emap.canlendar.IWeekItem;
import com.github.tibolte.agendacalendarview.models.WeekItem;
import com.google.android.gms.maps.SupportMapFragment;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

public class AppointFragment extends Fragment implements CalendarPickerController {

    // endregion
    private View view;
    private Activity activity;
    private AgendaCalendarView mAgendaCalendarView;
    private DataStore dataStore = DataStore.getInstance();
    // region Interface - CalendarPickerController

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_appoint, container, false);
        mAgendaCalendarView = (AgendaCalendarView) view.findViewById(R.id.agenda_calendar_view);
//        setContentView(R.layout.activity_main);
//        ButterKnife.bind(this);

//        ((AppCompatActivity)getActivity()).setSupportActionBar(mToolbar);

        // minimum and maximum date of our calendar
        // 2 month behind, one year ahead, example: March 2015 <-> May 2015 <-> May 2016
        Calendar minDate = Calendar.getInstance();
        Calendar maxDate = Calendar.getInstance();

        minDate.add(Calendar.MONTH, -2);
        minDate.set(Calendar.DAY_OF_MONTH, 1);
        maxDate.add(Calendar.YEAR, 1);

        List<CalendarEvent> eventList = new ArrayList<>();
        mockList(eventList);

        mAgendaCalendarView.init(eventList, minDate, maxDate, Locale.getDefault(), this);
        mAgendaCalendarView.addEventRenderer(new DrawableEventRenderer());
        return view;
    }

    @Override
    public void onDaySelected(DayItem dayItem) {
        System.out.println("Selected day: "+ dayItem);
    }

    @Override
    public void onEventSelected(CalendarEvent event) {
        System.out.println("Selected event: "+ event);
    }

    @Override
    public void onScrollToDate(Calendar calendar) {

    }

    // endregion

    // region Private Methods

    private void mockList(List<CalendarEvent> eventList) {

        for (Appointment appointment: dataStore.getAppointmentList()){
            Calendar startTime = Calendar.getInstance();
            Calendar endTime = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd h:m a", Locale.ENGLISH);
            try {
                String date =appointment.getDate()+" "+appointment.getTime();
                startTime.setTime(sdf.parse(date));
                endTime.setTime(sdf.parse(date));
                endTime.add(Calendar.DAY_OF_WEEK, 1);

                BaseCalendarEvent event = new BaseCalendarEvent(appointment.getMember(), "顧客預約",appointment.getLocation(),
                        ContextCompat.getColor(getContext(), R.color.blue_dark), startTime, endTime, true);
                eventList.add(event);
            } catch (ParseException e) {
                e.printStackTrace();
            }

        }


//
//        Calendar startTime2 = Calendar.getInstance();
//        startTime2.add(Calendar.DAY_OF_YEAR, 1);
//        Calendar endTime2 = Calendar.getInstance();
//        endTime2.add(Calendar.DAY_OF_YEAR, 3);
//        BaseCalendarEvent event2 = new BaseCalendarEvent("Visit to Dalvík", "A beautiful small town", "Dalvík",
//                ContextCompat.getColor(getContext(), R.color.yellow), startTime2, endTime2, true);
//        eventList.add(event2);
//
//        Calendar startTime3 = Calendar.getInstance();
//        Calendar endTime3 = Calendar.getInstance();
//        startTime3.set(Calendar.HOUR_OF_DAY, 14);
//        startTime3.set(Calendar.MINUTE, 0);
//        endTime3.set(Calendar.HOUR_OF_DAY, 15);
//        endTime3.set(Calendar.MINUTE, 0);
//        DrawableCalendarEvent event3 = new DrawableCalendarEvent("Visit of Harpa", "", "Dalvík",
//                ContextCompat.getColor(getContext(), R.color.blue_dark), startTime3, endTime3, false, android.R.drawable.ic_dialog_info);
//        eventList.add(event3);
    }
}
