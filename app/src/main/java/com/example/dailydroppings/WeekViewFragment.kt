package com.example.dailydroppings

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.ListView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dailydroppings.CalenderUtils.monthYearFromDate
import com.example.dailydroppings.data.AppDatabase
import kotlinx.android.synthetic.main.event_cell.*
import java.time.LocalDate


class WeekViewFragment : Fragment(), CalenderAdapter.OnItemListener {

    private var monthYearText: TextView? = null
    private var calendarRecyclerView: RecyclerView? = null
    private var eventListView: ListView? = null
    private lateinit var eventAdapter: EventAdapter



    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var v: View = inflater.inflate(R.layout.activity_week_view, container, false)

        var backButton : Button = v.findViewById(R.id.previousWeek)
        var forwardButton : Button = v.findViewById(R.id.nextWeek)

        var addPoop : LinearLayout = v.findViewById(R.id.addPoop)

        var newEvent : Button = v.findViewById(R.id.newEvent)

//        var delete : Button = v.findViewById(R.id.delete)
//
//        val db = AppDatabase.getDbInstance(this.context)
//        db.eventDao().nukeTable()

        monthYearText = v.findViewById(R.id.monthYearTV)
        calendarRecyclerView = v.findViewById(R.id.calendarRecyclerVieww)
        eventListView = v.findViewById(R.id.eventListView)

        CalenderUtils.selectedDate = LocalDate.now()

        setWeekView()

        backButton.setOnClickListener { view ->
            CalenderUtils.selectedDate = CalenderUtils.selectedDate.minusWeeks(1)
            setWeekView()

        }

        forwardButton.setOnClickListener { view ->
            CalenderUtils.selectedDate = CalenderUtils.selectedDate.plusWeeks(1)
            setWeekView()
        }

//        delete.setOnClickListener { view ->
//
////            var v: View = inflater.inflate(R.layout.event_cell, container, false)
////
////            if (delete.text.equals("Done")) {
////                delete.text = "Edit"
////
//////                for(i in 0 until Event.eventsList.size) {
//////                    cancel_button.visibility = View.INVISIBLE
//////                }
////
////            } else {
////                delete.text = "Done"
////
//////                for(i in 0 until Event.eventsList.size) {
//////                    cancel_button.visibility = View.VISIBLE
//////                }
////            }
//        }

        addPoop.setOnClickListener {
            activity?.let {
                val intent = Intent(it, EventEditActivity::class.java)
                startActivity(intent)
            }
        }

        return v
    }

    override fun onResume() {
        super.onResume()
        setEventAdpater()
    }

    private fun setEventAdpater() {
        val db = AppDatabase.getDbInstance(requireContext())
        val events = db.eventDao().getAll(CalenderUtils.selectedDate)
        var eventAdapter = this.context?.let { EventAdapter(it, events) }
        eventListView?.adapter = eventAdapter
        eventAdapter?.setEventList(events)
    }


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onItemClick(position: Int, date: LocalDate?) {
        CalenderUtils.selectedDate = date
        setWeekView()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun setWeekView() {
        monthYearText?.text = monthYearFromDate(CalenderUtils.selectedDate)
        val days: ArrayList<LocalDate> = CalenderUtils.daysInWeekArray(CalenderUtils.selectedDate)
        val calendarAdapter = CalenderAdapter(days, this)
        val layoutManager: RecyclerView.LayoutManager = GridLayoutManager(this.context, 7)
        calendarRecyclerView?.layoutManager = layoutManager
        calendarRecyclerView?.adapter = calendarAdapter

        setEventAdpater()
    }

}
