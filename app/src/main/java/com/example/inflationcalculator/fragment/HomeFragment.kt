package com.example.inflationcalculator.fragment

import android.graphics.Color
import android.os.Bundle
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.util.Log
import android.view.*
import android.view.ViewTreeObserver.OnGlobalLayoutListener
import android.widget.Button
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.inflationcalculator.R
import com.example.inflationcalculator.databinding.FragmentHomeBinding
import com.example.inflationcalculator.utill.Utill
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog

class HomeFragment : Fragment() {
    lateinit var binding: FragmentHomeBinding
    lateinit var menu: Menu
    private var number = ""

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
            Log.d("Home Number", number.length.toString())
            if (number.isNotEmpty()) {
                number = number.substring(0, number.length - 1)
                binding.initialAmountEditText.setText(number)
            }
            binding.initialAmountEditText.setSelection(number.length)
        }

        binding.initialAmountEditText.setOnClickListener {
            Utill.hideKeyboardFrom(context!!, binding.initialAmountEditText) }
        bottomSheetGestureDetection()

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_main, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.about_menu -> {
                binding.bottomSheetLayout.visibility = View.VISIBLE
                val dialog = BottomSheetDialog(activity!!)
                dialog.show()
                Toast.makeText(activity, "about button clicked", Toast.LENGTH_LONG).show()
                binding.bottomSheetLayout
                return true
            }
            R.id.currency_menu -> {
                menu.getItem(0).icon = ContextCompat.getDrawable(context!!, R.drawable.ic_forward);
                Toast.makeText(activity, "about button clicked", Toast.LENGTH_LONG).show()

               return true
            }
            else -> super.onOptionsItemSelected(item)

        }
    }

    private fun bottomSheetGestureDetection() {
        val sheetBehavior = BottomSheetBehavior.from(binding.bottomSheetLayout)
        val vto = binding.bottomSheetLayout.viewTreeObserver
        vto.addOnGlobalLayoutListener(
            object : OnGlobalLayoutListener {
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
                            binding.bottomSheetArrow.setImageResource(R.drawable.icn_chevron_down)
                        }
                        BottomSheetBehavior.STATE_HIDDEN -> {
                        }
                        BottomSheetBehavior.STATE_COLLAPSED -> {
                            binding.bottomSheetArrow.setImageResource(R.drawable.icn_chevron_up)
                        }
                        BottomSheetBehavior.STATE_DRAGGING -> {
                        }
                        BottomSheetBehavior.STATE_SETTLING -> binding.bottomSheetArrow.setImageResource(
                            R.drawable.icn_chevron_up
                        )
                    }
                }
            })
    }
}
