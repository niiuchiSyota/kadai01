package com.niiuchi.memo

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

private const val DB_NAME = "MemoDatabase"
private const val DB_VERSION = 1

class DBHelper(context: Context)
    : SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION){

    override fun onCreate(db: SQLiteDatabase?){
        //テーブルの作成
        db?.execSQL("""
            CREATE TABLE User(
            _id INTEGER PRIMARY KEY AUTOINCREMENT,
            title text not null,
            contents text not null);
        """)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int,
                           newVersion: Int){
        //バージョン更新時のSQL発行
    }

}


fun queryTexts(context: Context) : List<String> {
    //読み込み用のデータベースを開く
    val database = DBHelper(context).readableDatabase
    //データベースから全件検索する
    val cursor = database.query(
            "User", null, null, null, null, null, null)

    val texts = mutableListOf<String>()
    cursor.use{
        //カーソルで順次処理していく
        while(cursor.moveToNext()){
            //保存されているテキストを得る
            val text = cursor.getString(cursor.getColumnIndex("title"))
            texts.add(text)
        }
    }
    database.close()
    return texts
}
