package com.example.pumpapp;

import android.annotation.SuppressLint
import android.os.CountDownTimer;
import android.widget.TextView;

class Stoper(
    var millisInFuture: Long, var countDownInterval: Long, val textTimer: TextView
) : CountDownTimer(millisInFuture, countDownInterval) {
    override fun onTick(millisUntilFinished: Long) {


        updateUi(millisUntilFinished)


    }

    @SuppressLint("SetTextI18n")
    override fun onFinish() {
        textTimer.text = "Start to workout"


    }

    @SuppressLint("SetTextI18n")
    private fun updateUi(time: Long) {
        var seconds = (time / 1000)


        textTimer.text = " $seconds Seconds"


    }


}