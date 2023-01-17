package com.example.dailydroppings;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.dailydroppings.data.AppDatabase;

import java.util.List;

public class EventAdapter extends ArrayAdapter<Event> {

    private Context context;
    private List<Event> eventList;
    private EventAdapter eventAdapter;



    public EventAdapter(@NonNull Context context, List<Event> events) {
        super(context, 0 , events);
    }


    public void setEventList(List<Event> eventList) {
        this.eventList = eventList;
        notifyDataSetChanged();
    }

//    @Override
    public int getItemCount() {
        return this.eventList.size();
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Event event = getItem(position);

        if (convertView == null)
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.event_cell, parent, false);

        TextView eventCellTime = convertView.findViewById(R.id.eventCellTime);
        String eventTime = event.name;
        eventCellTime.setText(eventTime);

        TextView eventCellPoopType = convertView.findViewById(R.id.eventCellType);
        String eventType = event.poopType;
        eventCellPoopType.setText(eventType);


        TextView eventCellPoopColor = convertView.findViewById(R.id.eventCellColor);
        int eventColor = event.poopColor;
        eventCellPoopColor.setText("");
        eventCellPoopColor.setCompoundDrawablesWithIntrinsicBounds(eventColor, 0, 0, 0);

        TextView eventCellPoopDuration = convertView.findViewById(R.id.eventCellToiletTime);
        String eventDuration = event.poopDuration;
        eventCellPoopDuration.setText(eventDuration + " Min");

        TextView eventCellPoopDFeeling = convertView.findViewById(R.id.eventCellFeeling);
        String eventFeeling = event.poopFeeling;
        eventCellPoopDFeeling.setText(eventFeeling);

        ImageButton eventDelete = convertView.findViewById(R.id.cancel_button);


        eventDelete.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                AppDatabase db = AppDatabase.getDbInstance(context);
                db.eventDao().delete(event);


                eventList.remove(position) ;
                notifyDataSetChanged() ;
            }
        });

        return convertView;
    }

    public EventAdapter getEventAdapter() {
        return eventAdapter;
    }

    public void setEventAdapter(EventAdapter eventAdapter) {
        this.eventAdapter = eventAdapter;
    }
}