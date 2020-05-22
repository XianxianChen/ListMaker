package com.grailed.listmaker.ToDoList

import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.grailed.listmaker.R
import com.grailed.listmaker.TaskList.DetailActivity
import com.grailed.listmaker.TaskList.TaskList
import com.grailed.listmaker.TodoListFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object {
        const val Intent_List_Key = "list"
        const val LIST_DETAIL_REQUEST_CODE = 123
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

    }

}
