package com.grailed.listmaker.TaskList

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.grailed.listmaker.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [DetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DetailFragment : Fragment() {

    lateinit var taskListRecyclerView: RecyclerView
    lateinit var list: TaskList
    override fun onAttach(context: Context) {
        super.onAttach(context)
        arguments?.let {
            list = it.getParcelable(ARG_LIST)!!
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        taskListRecyclerView = view.findViewById(R.id.taskListRecyclerView)
        taskListRecyclerView.layoutManager = LinearLayoutManager(activity)
        taskListRecyclerView.adapter = TaskListAdapter(list)

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment DetailFragment.
         */
        private val ARG_LIST = "arg_list"

        @JvmStatic
        fun newInstance(list: TaskList): DetailFragment {

            val bundle = Bundle()
            bundle.putParcelable(ARG_LIST, list)
            val fragment = DetailFragment()
            fragment.arguments = bundle
            return fragment
        }
    }


}
