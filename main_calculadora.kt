package com.training.calculadora

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.training.calculadora.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*
import net.objecthunter.exp4j.ExpressionBuilder



    class MainActivity : AppCompatActivity() {
        private lateinit var binding : ActivityMainBinding

        override fun onCreate(savedInstanceState: Bundle?) {
            
            super.onCreate(savedInstanceState)

            binding = ActivityMainBinding.inflate(layoutInflater)
            setContentView(binding.root)

            //Numbers
            binding.tvOne.setOnClickListener { appendOnExpresstion("1", true) }
            tvTwo.setOnClickListener { appendOnExpresstion("2", true) }
            tvThree.setOnClickListener { appendOnExpresstion("3", true) }
            tvFour.setOnClickListener { appendOnExpresstion("4", true) }
            tvFive.setOnClickListener { appendOnExpresstion("5", true) }
            tvSix.setOnClickListener { appendOnExpresstion("6", true) }
            tvSeven.setOnClickListener { appendOnExpresstion("7", true) }
            tvEight.setOnClickListener { appendOnExpresstion("8", true) }
            tvNine.setOnClickListener { appendOnExpresstion("9", true) }
            tvZero.setOnClickListener { appendOnExpresstion("0", true) }
            tvDot.setOnClickListener { appendOnExpresstion(".", true) }

            //Operators
            tvPlus.setOnClickListener { appendOnExpresstion("+", false) }
            tvMinus.setOnClickListener { appendOnExpresstion("-", false) }
            tvMul.setOnClickListener { appendOnExpresstion("*", false) }
            tvDivide.setOnClickListener { appendOnExpresstion("/", false) }
            tvOpen.setOnClickListener { appendOnExpresstion("(", false) }
            tvClose.setOnClickListener { appendOnExpresstion(")", false) }

            tvClear.setOnClickListener {
                tvExpression.text = ""
                tvResult.text = ""
            }

            tvBack.setOnClickListener {
                val string = tvExpression.text.toString()
                if(string.isNotEmpty()){
                    tvExpression.text = string.substring(0,string.length-1)
                }
                tvResult.text = ""
            }

            tvEquals.setOnClickListener {
                try {
                    val expression = ExpressionBuilder(tvExpression.text.toString()).build()
                    val result = expression.evaluate()
                    val longResult = result.toLong()
                    if(result == longResult.toDouble())
                        tvResult.text = longResult.toString()
                    else
                        tvResult.text = result.toString()
                }catch (e:Exception){
                    Log.d("Exception"," message : " + e.message )
                }
            }

        }

        fun appendOnExpresstion(string: String, canClear: Boolean) {
            if(tvResult.text.isNotEmpty()){
                tvExpression.text = ""
                println("EMPTY "+string)
            }

            if (canClear) {
                println("pCLEAR "+string)
                tvResult.text = ""
                tvExpression.append(string)
                println("pCLEAR " + tvExpression.text)
            } else {
                println("NOCLEAR "+string)
                tvExpression.append(tvResult.text)
                tvExpression.append(string)
                tvResult.text = ""
            }
        }
    }