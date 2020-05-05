package com.example.inflationcalculator.fragment

import android.os.Bundle
import android.util.Log
import android.view.*
import android.view.ViewTreeObserver.OnGlobalLayoutListener
import android.widget.Button
import android.widget.Toast
import android.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.inflationcalculator.R
import com.example.inflationcalculator.databinding.FragmentHomeBinding
import com.example.inflationcalculator.utill.Utill
import com.google.android.material.bottomsheet.BottomSheetBehavior

class HomeFragment : Fragment() {
    lateinit var binding: FragmentHomeBinding
    private var number = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
        val listener = View.OnClickListener { view ->
            val button = view as Button
            binding.initialAmountEditText.append(button.text.toString())
            number += button.text.toString()
            Log.d("Home Fragment", number)
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
            Utill.hideKeyboardFrom(context!!, binding.initialAmountEditText) }
        //bottomSheetGestureDetection()

        return binding.root
    }


/*    private fun bottomSheetGestureDetection() {
     val sheetBehavior = BottomSheetBehavior.from(binding.bottomSheetLayout)
     val vto = binding.bottomSheetLayout.viewTreeObserver

     vto.addOnGlobalLayoutListener(
         object : ViewTreeObserver.OnGlobalLayoutListener {
             override fun onGlobalLayout() {
                 binding.bottomSheetLayout.viewTreeObserver.removeOnGlobalLayoutListener(this)
                 //                int width = bottomSheetLayout.getMeasuredWidth();
                 val height = binding.bottomSheetLayout.measuredHeight
                 sheetBehavior.peekHeight =
                     height
             }
         })
     sheetBehavior.isHideable = true
     sheetBehavior.setBottomSheetCallback(
         object : BottomSheetBehavior.BottomSheetCallback() {
             override fun onSlide(p0: View, p1: Float) {}

             override fun onStateChanged(bottomSheet: View, newState: Int) {
                 when (newState) {
                     BottomSheetBehavior.STATE_EXPANDED -> {
                         binding.bottomSheetLayout.bottomSheetArrow.setImageResource(R.drawable.icn_chevron_down)
                     }
                     BottomSheetBehavior.STATE_HIDDEN -> {
                     }
                     BottomSheetBehavior.STATE_COLLAPSED -> {
                         binding.bottomSheetLayout.bottomSheetArrow.setImageResource(R.drawable.icn_chevron_up)
                     }
                     BottomSheetBehavior.STATE_DRAGGING -> {
                     }
                     BottomSheetBehavior.STATE_SETTLING -> binding.bottomSheetLayout.bottomSheetArrow.setImageResource(
                         R.drawable.icn_chevron_up
                     )
                 }
             }
         })
 }*/
}
