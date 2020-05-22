package com.grailed.listmaker.TaskList

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.grailed.listmaker.R
import com.grailed.listmaker.ToDoList.MainActivity

class DetailActivity : AppCompatActivity() {

    lateinit var list: TaskList
    lateinit var detailFragment: DetailFragment
    lateinit var addTaskButton: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        list = intent.getParcelableExtra(MainActivity.Intent_List_Key) as TaskList
        title = list.name
        detailFragment = DetailFragment.newInstance(list)
        supportFragmentManager.beginTransaction()
            .add(R.id.detail_fragment_container, detailFragment)
            .commit()

        addTaskButton = findViewById(R.id.add_task_button)
        addTaskButton.setOnClickListener {
            showCreateTaskDialog()
        }
    }

    override fun onBackPressed() {
        val bundle = Bundle()
        bundle.putParcelable(MainActivity.Intent_List_Key, list)
        val intent = Intent()
        intent.putExtras(bundle)
        setResult(Activity.RESULT_OK, intent)
        super.onBackPressed() // super goes last for this method
    }

    private fun showCreateTaskDialog() {
        val taskEditText = EditText(this)
        taskEditText.inputType = InputType.TYPE_CLASS_TEXT
        AlertDialog.Builder(this)
            .setTitle(R.string.task_to_add)
            .setView(taskEditText)
            .setPositiveButton(R.string.add_task) { dialog, _ ->
                val task = taskEditText.text.toString()

                list.tasks.add(task)
                dialog.dismiss()
            }
            .create()
            .show()
    }
}
