package com.etransp.etransp

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.next_trips_item.view.*

class NextTripsActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager


    companion object {

        fun newIntent(context: Context): Intent = Intent(context, NextTripsActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_next_trips)

        val dataset = listOf(
            ItemData(true, "08:00", "11:00", "Segunda-Feira 10/10/2010"),
            ItemData(true, "20:00", "22:00", "Segunda-Feira 10/10/2010"),
            ItemData(false, "08:00", "11:00", "Terça-Feira 11/10/2010")
        )

        viewManager = LinearLayoutManager(this)
        viewAdapter = NextTripsActivity.MyAdapter(dataset)

        recyclerView = findViewById<RecyclerView>(R.id.recyclerNextTrips).apply {
            // use this setting to improve performance if you know that changes
            // in content do not change the layout size of the RecyclerView
            setHasFixedSize(true)

            // use a linear layout manager
            layoutManager = viewManager

            // specify an viewAdapter (see also next example)
            adapter = viewAdapter

        }
    }


    class MyAdapter(private val items: List<NextTripsActivity.ItemData>) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

        class MyViewHolder(val view: View) : RecyclerView.ViewHolder(view)

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

            // create a new view
            val layout = LayoutInflater.from(parent.context)
                .inflate(R.layout.next_trips_item, parent, false)

            return MyViewHolder(layout)
        }


        override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
            val itemData: NextTripsActivity.ItemData = items.get(position)

            holder.view.textViewNextDepartureAndArrival.text = "Saída às ${itemData.departureTime}, Chegada às ${itemData.arrivalTime}"
            holder.view.textViewDate.text = itemData.date
            holder.view.checkboxWasPresent.isChecked = itemData.wasPresent
        }

        // Return the size of your dataset (invoked by the layout manager)
        override fun getItemCount() = items.size
    }

    class ItemData(
        val wasPresent: Boolean,
        val departureTime: String,
        val arrivalTime: String,
        val date: String) {

    }
}
