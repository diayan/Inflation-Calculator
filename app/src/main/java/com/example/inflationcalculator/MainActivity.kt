package com.example.inflationcalculator

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.inflationcalculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var  binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setSupportActionBar(binding.toolbar)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.about_menu -> {
                Toast.makeText(this, "about button clicked", Toast.LENGTH_LONG).show()
                //binding.bottomSheetLayout
                return true
            }
            R.id.currency_menu -> {
                Toast.makeText(this, "about button clicked", Toast.LENGTH_LONG).show()
                return true
            }
            else -> super.onOptionsItemSelected(item)

        }
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
