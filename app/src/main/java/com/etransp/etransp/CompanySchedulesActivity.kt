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
import kotlinx.android.synthetic.main.company_schedule_item.view.*

class CompanySchedulesActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager


    companion object {

        fun newIntent(context: Context): Intent = Intent(context, CompanySchedulesActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_company_schedules)

        val dataset = listOf(
            ItemData("08:00", "11:00", "De Segunda a Sexta"),
            ItemData("13:00", "15:00", "De Segunda a Sexta"),
            ItemData("16:00", "18:00", "De Segunda a Sexta"),
            ItemData("18:00", "20:00", "De Segunda a Sexta")
        )

        viewManager = LinearLayoutManager(this)
        viewAdapter = MyAdapter(dataset)

        recyclerView = findViewById<RecyclerView>(R.id.recyclerSchedules).apply {
            // use this setting to improve performance if you know that changes
            // in content do not change the layout size of the RecyclerView
            setHasFixedSize(true)

            // use a linear layout manager
            layoutManager = viewManager

            // specify an viewAdapter (see also next example)
            adapter = viewAdapter

        }
    }


    class MyAdapter(private val items: List<ItemData>) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

        class MyViewHolder(val view: View) : RecyclerView.ViewHolder(view)

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

            // create a new view
            val layout = LayoutInflater.from(parent.context)
                .inflate(R.layout.company_schedule_item, parent, false)

            return MyViewHolder(layout)
        }


        override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
            val itemData: ItemData = items.get(position)

            holder.view.textViewDepartureAndArrival.text = "Saída às ${itemData.departureTime}, Chegada às ${itemData.arrivalTime}"
            holder.view.textViewOperatingDays.text = itemData.operatingDays
        }

        // Return the size of your dataset (invoked by the layout manager)
        override fun getItemCount() = items.size
    }

    class ItemData(
        val departureTime: String,
        val arrivalTime: String,
        val operatingDays: String) {

    }
}
