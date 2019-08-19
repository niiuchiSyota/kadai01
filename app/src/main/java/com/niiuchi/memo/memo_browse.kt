package com.niiuchi.memo

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT


class memo_browse : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.memo_browse)
        val title_t = intent.getStringExtra("title") //もともとのタイトル名を変数:title_tに保存する
        val button5 = findViewById<Button>(R.id.button5)
        val editText = findViewById<EditText>(R.id.editText)
        val plainTextBrowse = findViewById<EditText>(R.id.plainTextBrowse)
        editText.setText(title_t)

        val database:SQLiteDatabase? = DBHelper(this).writableDatabase
        val sql = "select *  from User where title = ? "
        val cur = database?.rawQuery(sql,arrayOf(title_t))
        cur?.moveToFirst()

        val contents = cur?.getString(cur?.getColumnIndex("contents"))


        //削除
        plainTextBrowse.setText(contents)
        button5.setOnClickListener {

            database?.delete("User","title = ?", arrayOf(title_t))
        }


        //更新
        val btn4 = findViewById<Button>(R.id.button4)
        btn4.setOnClickListener {
            val txt = findViewById<EditText>(R.id.editText).text.toString()
            val contentsvalue = findViewById<EditText>(R.id.plainTextBrowse).text.toString()
            val update = ContentValues().apply {
                put("title",txt)
                put("contents",contentsvalue)
            }
            //Toast.makeText(this,txt,Toast.LENGTH_SHORT).show()
            database?.update("User", update, "title = ?", arrayOf(title_t))



        }

    }
}
