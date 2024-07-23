package com.example.teacher

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView


class TeacherAdapter(private val context: Context, private val dataSource: ArrayList<Teacher>) : BaseAdapter() {

    private val inflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getCount(): Int {
        return dataSource.size
    }

    override fun getItem(position: Int): Any {
        return dataSource[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val rowView = inflater.inflate(R.layout.list_item_teacher, parent, false)

        val textViewName = rowView.findViewById(R.id.textViewName) as TextView
        val textViewAge = rowView.findViewById(R.id.textViewAge) as TextView
        val textViewDept = rowView.findViewById(R.id.textViewDept) as TextView
        val textViewStatus = rowView.findViewById(R.id.textViewStatus) as TextView

        val teacher = getItem(position) as Teacher

        textViewName.text = "Age: ${teacher.name}"
        textViewAge.text = "Age: ${teacher.age}"
        textViewDept.text = "Age: ${teacher.department}"
        textViewStatus.text = "Age: ${teacher.status}"

        return rowView
    }
}
