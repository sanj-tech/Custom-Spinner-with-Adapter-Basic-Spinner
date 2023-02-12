package com.example.spinnerdemoexample.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.spinnerdemoexample.LeaveModel
import com.example.spinnerdemoexample.R

class CustomSpinnerAdapter(ctx: Context, leaveList: ArrayList<LeaveModel>) : ArrayAdapter<LeaveModel>(ctx, 0, leaveList) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return createItemView(position, convertView, parent);
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return createItemView(position, convertView, parent);
    }

    fun createItemView(position: Int, recycledView: View?, parent: ViewGroup): View {
        val list = getItem(position)

        val view = recycledView ?: LayoutInflater.from(context).inflate(
            R.layout.custom_leave_spinner,
            parent,
            false
        )
        list?.let {
            view.findViewById<TextView>(R.id.tvLeaveType).text = (list.leaveType)
            view.findViewById<TextView>(R.id.tvDuration).text = list.leaveDuration
        }
        return view
    }
}