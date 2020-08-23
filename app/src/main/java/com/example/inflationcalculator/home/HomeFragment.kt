package com.example.inflationcalculator.home

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Button
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.inflationcalculator.R
import com.example.inflationcalculator.databinding.FragmentHomeBinding
import com.example.inflationcalculator.utill.Utill

class HomeFragment : Fragment() {
    lateinit var binding: FragmentHomeBinding
    private var number = ""
    private lateinit var viewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    @ExperimentalStdlibApi
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_home,
            container, false
        )

        viewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)

        viewModel.historicalInflationData.observe(viewLifecycleOwner, Observer {
            Log.d("HistoricalData", it.toString())
        })

        val listener = View.OnClickListener { view ->
            val button = view as Button
            binding.initialAmountEditText.append(button.text.toString())
            number += button.text.toString()
            Log.d("Home Fragment", number)
        }
        
        binding.currencyNameTextView.setOnClickListener {

        }
        binding.apply {
            invalidateAll()
            button0.setOnClickListener(listener)
            button1.setOnClickListener(listener)
            button2.setOnClickListener(listener)
            button3.setOnClickListener(listener)
            button4.setOnClickListener(listener)
            button5.setOnClickListener(listener)
            button6.setOnClickListener(listener)
            button7.setOnClickListener(listener)
            button8.setOnClickListener(listener)
            button9.setOnClickListener(listener)
            buttonDot.setOnClickListener(listener)
        }

        binding.deleteButton.setOnClickListener {
            if (number.isNotEmpty()) {
                number = number.substring(0, number.length - 1)
                binding.initialAmountEditText.setText(number)
            }
            binding.initialAmountEditText.setSelection(number.length)
        }

        binding.initialAmountEditText.setOnClickListener {
            Utill.hideKeyboardFrom(context!!, binding.initialAmountEditText)
        }
        //bottomSheetGestureDetection()
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_main, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(
            item,
            view!!.findNavController()
        )
                || super.onOptionsItemSelected(item)
    }

}
