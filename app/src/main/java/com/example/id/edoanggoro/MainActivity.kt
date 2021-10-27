package com.example.id.edoanggoro

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.*


class MainActivity : AppCompatActivity() {
    var etAmount: EditText? = null
    var tvPercent: TextView? = null
    var sbPercent: SeekBar? = null
    var tvBunga: TextView? = null
    var tvLayanan: TextView? = null
    var tvCair: TextView? = null
    var tvTotal: TextView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        etAmount = findViewById(R.id.et_amount)
        tvPercent = findViewById(R.id.tv_percent)
        sbPercent = findViewById(R.id.sb_percent)
        tvBunga = findViewById(R.id.tv_bunga)
        tvLayanan = findViewById(R.id.tvLayananOut)
        tvCair = findViewById(R.id.TVCairOut)
        tvTotal = findViewById(R.id.tv_total)

        sbPercent!!.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, i: Int, b: Boolean) {
                tvPercent!!.text = "$i"
                hitung()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {}
            override fun onStopTrackingTouch(seekBar: SeekBar) {}
        })
        etAmount!!.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
            override fun afterTextChanged(editable: Editable) {
                hitung()
            }
        })
    }

    private fun hitung() {
        if (etAmount!!.length() == 0) {
            etAmount!!.requestFocus()
            etAmount!!.error = "Masukkan Pinjaman"
        } else {
            val amount = etAmount!!.text.toString().toInt()
            val percent = sbPercent!!.progress
            val bunga = 3.75/100 * amount
            val layanan = amount * 5/100
            val cair = amount - layanan
            val total = amount + (bunga * percent)

            tvBunga!!.text = bunga.toInt().toString()
            tvLayanan!!.text = layanan.toString()
            tvCair!!.text = cair.toString()
            tvTotal!!.text = total.toInt().toString()
        }
    }
}
