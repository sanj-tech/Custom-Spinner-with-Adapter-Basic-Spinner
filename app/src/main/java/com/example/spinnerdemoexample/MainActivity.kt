package com.example.spinnerdemoexample

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import androidx.databinding.DataBindingUtil
import com.example.spinnerdemoexample.adapters.CustomSpinnerAdapter
import com.example.spinnerdemoexample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var stateList: MutableList<String> = mutableListOf()
    private var stateName: String = ""
    private var currentLeaveStatus: String = ""
    lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding= DataBindingUtil.setContentView(this,R.layout.activity_main)


        getSpinnerData()
        customSpinner()
        getDialog()
    }

    private fun getDialog() {
        binding.btnSubmit.setOnClickListener({
            if ("Casual Leave/".equals(currentLeaveStatus)) {
                val dialog = Dialog(this)
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
                dialog.setCancelable(false)
                dialog.setContentView(R.layout.insufficient_pop_up)
                dialog.setCancelable(true)
                dialog.show()

            } else {
                if ("Loss Of Pay/".equals(currentLeaveStatus)) {
                    var dialogBinding = layoutInflater.inflate(R.layout.confirmation_pop_up, null)
                    val dialog = Dialog(this)
                    dialog.setContentView(dialogBinding)
                    dialog.setCancelable(true)
                    dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                    dialog.show()
                    val btnYes = dialog.findViewById<Button>(R.id.btnYes)
                    btnYes.setOnClickListener {
                       // getSucessfulDialog()
                        dialog.dismiss()


                    }
                    val btnNo = dialog.findViewById<Button>(R.id.btnNo)
                    btnNo.setOnClickListener({
                        dialog.dismiss()
                    })
                    dialog.show()
                }
            }
        })
    }

    private fun getSucessfulDialog() {
        TODO("Not yet implemented")
    }

    //Custom Spinner
    private fun customSpinner() {


        val leaveList = arrayListOf<LeaveModel>()

        leaveList.add(LeaveModel("", "Select Leave Type"))
        leaveList.add(LeaveModel("Birthday Leave/", "0.5 Available"))
        leaveList.add(LeaveModel("Compensatory Off/", "2 Available"))
        leaveList.add(LeaveModel("Loss Of Pay/","0 Available"))
        leaveList.add(LeaveModel("Casual Leave/", "1 Available"))




        val adapter = CustomSpinnerAdapter(
            this,
            leaveList
        )


        binding.customSpinner.adapter = adapter

        binding.customSpinner.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, p1: View?, position: Int, p3: Long) {

                currentLeaveStatus = leaveList[position].leaveType
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
            }
        })

        adapter.notifyDataSetChanged()
    }
//basic Spinner
    private fun getSpinnerData() {
        stateList = mutableListOf("Maharashtra", "Maharashtra", "UP", "Bihar")

        val spinnerAdapter =
            ArrayAdapter(this, R.layout.spinner_item, stateList)

        binding.spinnerState.adapter = spinnerAdapter

        binding.spinnerState.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
                //  itemText = state_spinner.getSelectedItem() as Spinner

                stateName = stateList[position]

//                Toast.makeText(
//                    applicationContext,
//                    "Selected Value is $stateName",
//                    Toast.LENGTH_LONG
//                ).show()

            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }


        }
    }
}