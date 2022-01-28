package com.example.firstapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import com.example.firstapp.MainViewModel
import com.example.firstapp.R
import com.example.firstapp.roomdb.Habit
import com.example.firstapp.roomdb.HabitDatabase
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import kotlin.random.Random

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ExampleFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ExampleFragment : BaseFragment() {

    lateinit var fragemetview: View
    var randomNumber = 0
    private val viewModel: MainViewModel by activityViewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        fragemetview = inflater.inflate(R.layout.layout_for_fragment, container, false)
        return fragemetview
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var textview = fragemetview.findViewById<TextView>(R.id.textViewForFragment)
        var button = fragemetview.findViewById<Button>(R.id.buttonForFragment)
        var habitEditText = fragemetview.findViewById<EditText>(R.id.todoEditText)
        textview.text = viewModel.selectedItem.value.toString()

        viewModel.selectedItem.observe(viewLifecycleOwner, { it ->
            textview.text = it.toString()
        })

        button.setOnClickListener {
            val habitDetails = habitEditText.text.toString().trim()
            val currentDate = textview.text.toString().trim()

            val habit = Habit(currentDate, habitDetails.toString())
            launch {
                context?.let {
                    HabitDatabase(it).getHabitDao().addHabit(habit)
                    println("The currentDate is $currentDate ")
                    println("The habitDetails is $habitDetails")
                }
            }
        }

    }

    private fun getCurrentDate(): String {
        var time = Calendar.getInstance().time
        var dateFormat = SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault())
        return dateFormat.format(time).toString()
    }


    fun Random.nextInt(range: IntRange): Int {

        return range.first + nextInt(range.last - range.first)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ExampleFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ExampleFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}