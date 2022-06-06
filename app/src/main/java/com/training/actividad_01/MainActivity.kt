package com.training.actividad_01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.training.actividad_01.databinding.ActivityMainBinding
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Numbers
        binding.tvOne.setOnClickListener { appendOnExpresstion("1", true) }
        binding.tvTwo.setOnClickListener { appendOnExpresstion("2", true) }
        binding.tvThree.setOnClickListener { appendOnExpresstion("3", true) }
        binding.tvFour.setOnClickListener { appendOnExpresstion("4", true) }
        binding.tvFive.setOnClickListener { appendOnExpresstion("5", true) }
        binding.tvSix.setOnClickListener { appendOnExpresstion("6", true) }
        binding.tvSeven.setOnClickListener { appendOnExpresstion("7", true) }
        binding.tvEight.setOnClickListener { appendOnExpresstion("8", true) }
        binding.tvNine.setOnClickListener { appendOnExpresstion("9", true) }
        binding.tvZero.setOnClickListener { appendOnExpresstion("0", true) }
        binding.tvDot.setOnClickListener { appendOnExpresstion(".", true) }

        //Operators
        binding.tvPlus.setOnClickListener { appendOnExpresstion("+", false) }
        binding.tvMinus.setOnClickListener { appendOnExpresstion("-", false) }
        binding.tvMul.setOnClickListener { appendOnExpresstion("*", false) }
        binding.tvDivide.setOnClickListener { appendOnExpresstion("/", false) }
        binding.tvOpen.setOnClickListener { appendOnExpresstion("(", false) }
        binding.tvClose.setOnClickListener { appendOnExpresstion(")", false) }
        binding.tvClear.setOnClickListener {
            binding.tvExpression.text = ""
            binding.tvResult.text = ""
        }
        binding.tvBack.setOnClickListener {
            val string = binding.tvExpression.text.toString()
            if(string.isNotEmpty()){
                binding.tvExpression.text = string.substring(0,string.length-1)
            }
            binding.tvResult.text = ""
        }
        binding.tvEquals.setOnClickListener {
            try {
                val expression = ExpressionBuilder(binding.tvExpression.text.toString()).build()
                val result = expression.evaluate()
                val longResult = result.toLong()
                if(result == longResult.toDouble())
                    binding.tvResult.text = longResult.toString()
                else
                    binding.tvResult.text = result.toString()
            }catch (e:Exception){
                Log.d("Exception"," message : " + e.message )
            }
        }
    }

    private fun appendOnExpresstion(string: String, canClear: Boolean) {
        if(binding.tvResult.text.isNotEmpty()){
            binding.tvExpression.text = ""
        }
        if (canClear) {
            binding.tvResult.text = ""
            binding.tvExpression.append(string)
        } else {
            binding.tvExpression.append(binding.tvResult.text)
            binding.tvExpression.append(string)
            binding.tvResult.text = ""
        }
    }
}