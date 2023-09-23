package com.senemyalin.sencoffee.common

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide

fun ImageView.loadImage(url: String?) {
    Glide.with(this.context).load(url).into(this)
}

fun View.click(func:() -> Unit){
    this.setOnClickListener{
        func()
    }
}

fun Context.toastMessage(message:String) =
    Toast.makeText(this,message, Toast.LENGTH_SHORT).show()

