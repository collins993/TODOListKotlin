package io.github.collins993.todolistkotlin.fragments.add

import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.text.TextUtils
import android.view.*
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import io.github.collins993.todolistkotlin.R
import io.github.collins993.todolistkotlin.data.models.Priority
import io.github.collins993.todolistkotlin.data.models.ToDoData
import io.github.collins993.todolistkotlin.data.viewmodel.TodoViewModel
import io.github.collins993.todolistkotlin.fragments.SharedViewModel

class AddFragment : Fragment() {

    private val mTodoViewModel: TodoViewModel by viewModels()
    private val mSharedViewModel: SharedViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add, container, false)

        //set menu
        setHasOptionsMenu(true)

        view.findViewById<Spinner>(R.id.priorities_spinner)
                .onItemSelectedListener = mSharedViewModel.listener

        return view
    }



    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.add_fragment_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (item.itemId == R.id.menu_add){
            insertDataToDb()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun insertDataToDb() {

        val mTitle = view?.findViewById<EditText>(R.id.title_et)?.text.toString()
        val mPriority = view?.findViewById<Spinner>(R.id.priorities_spinner)?.selectedItem.toString()
        val mDescription = view?.findViewById<EditText>(R.id.description_et)?.text.toString()

        val validation = mSharedViewModel.verifyDataFromUser(mTitle, mDescription)
        if (validation){
            val newData = ToDoData(
                    0,
                    mTitle,
                    mSharedViewModel.parsePriority(mPriority),
                    mDescription
            )

            mTodoViewModel.insertData(newData)
            Toast.makeText(requireContext(), "Data Added", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        }
        else{
            Toast.makeText(requireContext(), "Error", Toast.LENGTH_SHORT).show()
        }


    }

}