package com.example.aston_intensiv_5

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider

class ListFragment : Fragment() {

    companion object{
        const val ARG_PARAM2 = "param2"
    }
    private var param2: Array<String>? = arrayOf()
    private var itemClicked: Int? = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param2 = it.getStringArray(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewModel = ViewModelProvider(requireActivity())[ViewModel::class.java]
        clickListener(view, viewModel)
        getViewModelData(viewModel, view)
        saveData(view)
    }

    private fun setViewModelData(
        firstNameView:TextView?,
        lastNAmeView:TextView?,
        phoneView:TextView?,
        viewModel: ViewModel
    ){
        viewModel.setFirstName(
            firstNameView?.text.toString()

        )
        viewModel.setLastName(
            lastNAmeView?.text.toString()
        )
        viewModel.setPhone(
            phoneView?.text.toString()
        )
    }

    private fun getViewModelData(viewModel: ViewModel, view: View){
        viewModel.clickLiveData.observe(viewLifecycleOwner){
            itemClicked = it
        }
        when(itemClicked){
            1 -> {
                observeData(
                    viewModel,
                    view.findViewById(R.id.first_item_first_name),
                    view.findViewById(R.id.first_item_second_name),
                    view.findViewById(R.id.first_item_phone)
                )
            }
            2 -> {
                observeData(
                    viewModel,
                    view.findViewById(R.id.second_item_first_name),
                    view.findViewById(R.id.second_item_second_name),
                    view.findViewById(R.id.second_item_phone)
                )
            }
            3 -> {
                observeData(
                    viewModel,
                    view.findViewById(R.id.third_item_first_name),
                    view.findViewById(R.id.third_item_second_name),
                    view.findViewById(R.id.third_item_phone)
                )
            }
            4 -> {
                observeData(
                    viewModel,
                    view.findViewById(R.id.fourth_item_first_name),
                    view.findViewById(R.id.fourth_item_second_name),
                    view.findViewById(R.id.fourth_item_phone)
                )
            }
            else -> {}
        }
    }

    private fun observeData(
        viewModel: ViewModel,
        firstName: TextView,
        lastName: TextView,
        phone: TextView
    ){
        viewModel.firstNameLiveData.observe(viewLifecycleOwner){
            firstName.text = it
            saveData(requireView())
        }
        viewModel.lastNameLiveData.observe(viewLifecycleOwner){
            lastName.text = it
            saveData(requireView())
        }
        viewModel.phoneLiveData.observe(viewLifecycleOwner){
            phone.text = it
            saveData(requireView())
        }
    }

    private fun clickListener(view: View, viewModel: ViewModel){
        view.findViewById<LinearLayout>(R.id.first_item).setOnClickListener {
            setViewModelData(
                view.findViewById(R.id.first_item_first_name),
                view.findViewById(R.id.first_item_second_name),
                view.findViewById(R.id.first_item_phone),
                viewModel
            )
            viewModel.setClick(1)
            saveData(view)
            infoFragmentReplace()
        }
        view.findViewById<LinearLayout>(R.id.second_item).setOnClickListener {
            setViewModelData(
                view.findViewById(R.id.second_item_first_name),
                view.findViewById(R.id.second_item_second_name),
                view.findViewById(R.id.second_item_phone),
                viewModel
            )
            viewModel.setClick(2)
            saveData(view)
            infoFragmentReplace()
        }
        view.findViewById<LinearLayout>(R.id.third_item).setOnClickListener {
            setViewModelData(
                view.findViewById(R.id.third_item_first_name),
                view.findViewById(R.id.third_item_second_name),
                view.findViewById(R.id.third_item_phone),
                viewModel
            )
            viewModel.setClick(3)
            saveData(view)
            infoFragmentReplace()
        }
        view.findViewById<LinearLayout>(R.id.fourth_item).setOnClickListener {
            setViewModelData(
                view.findViewById(R.id.fourth_item_first_name),
                view.findViewById(R.id.fourth_item_second_name),
                view.findViewById(R.id.fourth_item_phone),
                viewModel
            )
            viewModel.setClick(4)
            saveData(view)
            infoFragmentReplace()
        }
    }

    private fun infoFragmentReplace(){
        parentFragmentManager
            .beginTransaction()
            .addToBackStack(ListFragment::class.java.simpleName)
            .replace(R.id.fragment_container_view, InfoFragment())
            .commit()
    }

    private fun saveData(view: View){
        param2 = arrayOf(
            view.findViewById<TextView>(R.id.first_item_first_name).text.toString(),
            view.findViewById<TextView>(R.id.first_item_second_name).text.toString(),
            view.findViewById<TextView>(R.id.first_item_phone).text.toString(),
            view.findViewById<TextView>(R.id.second_item_first_name).text.toString(),
            view.findViewById<TextView>(R.id.second_item_second_name).text.toString(),
            view.findViewById<TextView>(R.id.second_item_phone).text.toString(),
            view.findViewById<TextView>(R.id.third_item_first_name).text.toString(),
            view.findViewById<TextView>(R.id.third_item_second_name).text.toString(),
            view.findViewById<TextView>(R.id.third_item_phone).text.toString(),
            view.findViewById<TextView>(R.id.fourth_item_first_name).text.toString(),
            view.findViewById<TextView>(R.id.fourth_item_second_name).text.toString(),
            view.findViewById<TextView>(R.id.fourth_item_phone).text.toString()
        )
    }

    private fun setData(view: View){
            view.findViewById<TextView>(R.id.first_item_first_name).text = param2?.get(0)
            view.findViewById<TextView>(R.id.first_item_second_name).text= param2?.get(1)
            view.findViewById<TextView>(R.id.first_item_phone).text= param2?.get(2)
            view.findViewById<TextView>(R.id.second_item_first_name).text= param2?.get(3)
            view.findViewById<TextView>(R.id.second_item_second_name).text= param2?.get(4)
            view.findViewById<TextView>(R.id.second_item_phone).text= param2?.get(5)
            view.findViewById<TextView>(R.id.third_item_first_name).text= param2?.get(6)
            view.findViewById<TextView>(R.id.third_item_second_name).text= param2?.get(7)
            view.findViewById<TextView>(R.id.third_item_phone).text= param2?.get(8)
            view.findViewById<TextView>(R.id.fourth_item_first_name).text= param2?.get(9)
            view.findViewById<TextView>(R.id.fourth_item_second_name).text= param2?.get(10)
            view.findViewById<TextView>(R.id.fourth_item_phone).text= param2?.get(11)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putStringArray(ARG_PARAM2, param2)
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        if (savedInstanceState != null) {
            param2 = savedInstanceState.getStringArray(ARG_PARAM2)
            if (param2?.isEmpty() != true) {
                itemClicked = 0
                setData(requireView())
            }
        }
    }
}