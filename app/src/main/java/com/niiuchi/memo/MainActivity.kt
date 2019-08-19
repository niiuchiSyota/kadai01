package com.niiuchi.memo

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import android.widget.Toast.LENGTH_SHORT
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val button = findViewById<Button>(R.id.button)

        button.setOnClickListener{
            //追加ボタンの画面遷移
            val intent = Intent(this, memo_add::class.java)
            startActivity(intent)
            //メモタイトルの画面遷移
            //val intent2 = Intent(this,)
            //startActivity(intent)

        }
        show()

    }

    override fun onRestart() {
        super.onRestart()
        show()
    }
    private fun show(){
        //データベースに登録されている文字列の一覧を得る p425

        val texts = queryTexts(this)


        val listView = findViewById<ListView>(R.id.memoTitleList)
       listView.adapter = ArrayAdapter<String>(this,
                R.layout.list_text_row,R.id.txtrow, texts)
        listView.setOnItemClickListener { parent, view, position, id ->
            val txtrow = view.findViewById<TextView>(R.id.txtrow)
            val intent = Intent(this,memo_browse::class.java)
            intent.putExtra("title",txtrow.text.toString())
            startActivity(intent)
        }
    }
}
