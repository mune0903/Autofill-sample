package com.mune0903.autofill

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import com.mune0903.autofill.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy {
        DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            setupAutofill()
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun setupAutofill() {
        binding.apply {
            activityMainCreditcardNumberEditText.setAutofillHints(View.AUTOFILL_HINT_CREDIT_CARD_NUMBER)
            activityMainCreditcardMonthExpire.setAutofillHints(View.AUTOFILL_HINT_CREDIT_CARD_EXPIRATION_MONTH)
            activityMainCreditcardYearExpire.setAutofillHints(View.AUTOFILL_HINT_CREDIT_CARD_EXPIRATION_YEAR)
            activityMainCreditcardSecurityCodeEditText.setAutofillHints(View.AUTOFILL_HINT_CREDIT_CARD_SECURITY_CODE)
        }

        val month = arrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12)
        val months = (0..11)
            .map { month[it].toString() }
            .toTypedArray<CharSequence>()

        val year = Calendar.getInstance().get(Calendar.YEAR)
        val years = (0..10)
            .map { (year + it).toString() }
            .toTypedArray<CharSequence>()

        binding.activityMainCreditcardYearExpire.adapter = object : ArrayAdapter<CharSequence?>(this,
            android.R.layout.simple_spinner_item, years) {
            override fun getAutofillOptions() = years
        }

        binding.activityMainCreditcardMonthExpire.adapter = object : ArrayAdapter<CharSequence?>(this,
            android.R.layout.simple_spinner_item, months) {
            override fun getAutofillOptions() = months
        }
    }
}