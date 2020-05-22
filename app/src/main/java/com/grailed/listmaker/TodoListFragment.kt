package com.grailed.listmaker

import android.content.Context
import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.grailed.listmaker.TaskList.TaskList
import com.grailed.listmaker.ToDoList.TodoListAdapter
import kotlinx.android.synthetic.main.fragment_todo_list.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [TodoListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */

class TodoListFragment : Fragment(), TodoListAdapter.TodoListClickListener {
    private var listener: OnFragmentInteractionListener? = null
    private lateinit var todoListRecyclerView: RecyclerView
    private lateinit var listDataManager: ListDataManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_todo_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       activity?.let{
            listDataManager = ListDataManager(it)
        }
        val lists = listDataManager.readLists()
        todoListRecyclerView = view.findViewById(R.id.list_recyclerview)
        todoListRecyclerView.layoutManager = LinearLayoutManager(activity)
        todoListRecyclerView.adapter =
            TodoListAdapter(lists, this)

        fab.setOnClickListener { _ ->
            showCreateToDoListDialog()
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment TodoListFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(): TodoListFragment {
            return TodoListFragment()
        }
    }

    interface OnFragmentInteractionListener {

        fun onTodoListClicked(list: TaskList)
    }

    override fun listItenClicked(list: TaskList) {

    }

    fun addList(list: TaskList) {
        listDataManager.saveList(list)
        val todoAdapter = todoListRecyclerView.adapter as TodoListAdapter
        todoAdapter.addList(list)
    }

    fun saveList(list: TaskList) {
        listDataManager.saveList(list)
        updateLists()
    }

    private fun updateLists() {
        val lists = listDataManager.readLists()
        todoListRecyclerView.adapter = TodoListAdapter(lists, this)
    }

    private fun showCreateToDoListDialog() {
        activity?.let {
            val dialogTitle = getString(R.string.name_of_list)
            val positiveButtonTitle = getString(R.string.createList)
            val cancelButtonTitle = getString(R.string.cancel)
            val myDialog = AlertDialog.Builder(it)
            val todoTitleEditText = EditText(it)
            todoTitleEditText.inputType =
                InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_FLAG_CAP_WORDS
            myDialog.setTitle(dialogTitle)
            myDialog.setView(todoTitleEditText)

            myDialog.setPositiveButton(positiveButtonTitle) { dialog, _ ->

                val list =
                    TaskList(todoTitleEditText.text.toString())
                addList(list)
                dialog.dismiss()
            }
            myDialog.setNegativeButton(cancelButtonTitle) { dialog, _ ->
                dialog.dismiss()
            }
            myDialog.create().show()
        }
    }
}
