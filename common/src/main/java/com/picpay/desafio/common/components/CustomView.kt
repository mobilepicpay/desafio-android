package com.picpay.desafio.common.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatImageView
import androidx.constraintlayout.widget.ConstraintLayout
import com.picpay.desafio.common.R

class CustomView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val tvMessage: TextView
    private val ivIcon: AppCompatImageView
    private val btTryAgain: AppCompatButton
    private val root: View =
        LayoutInflater.from(context).inflate(R.layout.layout_custom_view, this, true)

    init {
        tvMessage = root.findViewById(R.id.tv_view_message)
        ivIcon = root.findViewById(R.id.iv_view_layout)
        btTryAgain = root.findViewById(R.id.bt_try_again)
        loadAttr(attrs, defStyleAttr)
    }

    private fun loadAttr(attrs: AttributeSet?, defStyleAttr: Int) {
        val arr = context.obtainStyledAttributes(
            attrs,
            R.styleable.CustomView,
            defStyleAttr,
            0
        )

        val message = arr.getString(R.styleable.CustomView_viewMessage)
        val icon = arr.getResourceId(R.styleable.CustomView_viewIcon, android.R.drawable.stat_sys_warning)
        val showTryAgain = arr.getBoolean(R.styleable.CustomView_showTryAgain, false)

        setupView(message,icon,showTryAgain)

        arr.recycle()
    }

    private fun setupView(message:String?, icon:Int, showTryAgain:Boolean){
        tvMessage.text = message
        ivIcon.setImageResource(icon)
        btTryAgain.visibility = if(showTryAgain) View.VISIBLE else View.GONE
    }

    fun setOnTryAgain(action: () -> Unit){
        btTryAgain.setOnClickListener { action.invoke() }
    }
}