package com.sampam.myapplication

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.text.isDigitsOnly
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var number:String="0"
        if(intent.action==Intent.ACTION_PROCESS_TEXT){
            number=intent.getCharSequenceExtra(Intent.EXTRA_PROCESS_TEXT).toString()
        }
        if (number.isDigitsOnly()){
            startwhatsapp(number)
        }
        else{
            Toast.makeText(this, "please check your number", Toast.LENGTH_SHORT).show()
        }



        b1.setOnClickListener {
            val a=etext.text.toString()
            var i = Intent().apply {
                action = Intent.ACTION_SENDTO
                data = Uri.parse("mailto:$a")
            }
            if(intent.resolveActivity(packageManager)!=null){
                startActivity(i)
            }
            else{
                Toast.makeText(this, "downlaod gmail app for sending emails", Toast.LENGTH_SHORT).show()
            }
        }
        b2.setOnClickListener {
            val a=etext.text.toString()
            var i = Intent().apply {
                action = Intent.ACTION_DIAL
                data = Uri.parse("tel:$a")
            }
            if(intent.resolveActivity(packageManager)!=null){
                startActivity(i)
            }
            else{
                Toast.makeText(this, "download phone call app", Toast.LENGTH_SHORT).show()
            }
        }
        b3.setOnClickListener {
            val a=etext.text.toString()
            var i = Intent().apply {
                action = Intent.ACTION_VIEW
                data = Uri.parse("https://$a")
            }
            if(intent.resolveActivity(packageManager)!=null){
                startActivity(i)
            }
            else{
                Toast.makeText(this, "downlaod browsable app ", Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun startwhatsapp(number: String) {
        if(number=="0"){
            return
        }
        val intent=Intent(Intent.ACTION_VIEW)
        intent.setPackage("com.whatsapp")
        val data:String=if(number[0]=='+'){
            number.substring(1)
        }else if (number.length==10){
            "91"+ number
        }else{
            number
        }
        intent.data= Uri.parse("https://wa.me/$data")
        if(intent.resolveActivity(packageManager)!=null){
            startActivity(intent)
        }
        else{
            Toast.makeText(this, "please install whatsapp", Toast.LENGTH_SHORT).show()
        }
     finish()
    }
}