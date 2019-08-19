package com.niiuchi.memo

import android.content.ContentValues
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class memo_add : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.memo_add_to_list)

        val button2 = findViewById<Button>(R.id.button2)
        val plainTextAddTo = findViewById<EditText>(R.id.plainTextAddTo)
        val editText2 = findViewById<EditText>(R.id.editText2)
        val editText = findViewById<EditText>(R.id.editText)
        val plainTextBrowse = findViewById<EditText>(R.id.plainTextBrowse)

        button2.setOnClickListener {
            //保存ボタンがクリックされた時の処理
            val database = DBHelper(this).writableDatabase
            //レコードni挿入したい内容
            val record = ContentValues().apply{
                put("title",editText2.text.toString())
                put("contents",plainTextAddTo.text.toString())
            }
            val sql = "select count(*) as cnt from User where title = ? "
            val cur = database.rawQuery(sql,arrayOf(editText2.text.toString()))
            cur.moveToFirst()

            val cnt = cur.getInt(cur.getColumnIndex("cnt"))




            if(0 == cnt){
                //重複しなかった際の処理
                //上の情報をデータベースに挿入
                database.insert("User",null,record)
            }else{
                //重複した際の処理
                editText2.error = "既に存在する名前です"
            }

            cur.close()

        }
    }
}