package com.example.aston_intensiv_5

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider

const val ARG_PARAM ="ARG_PARAM"

class InfoFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewModel = ViewModelProvider(requireActivity())[ViewModel::class.java]
        if (savedInstanceState == null){
            setText(view, viewModel)
        }
        clickListener(view, viewModel)
    }
    private fun clickListener(view: View, viewModel: ViewModel){
        view.findViewById<Button>(R.id.save_button).setOnClickListener{
            viewModel.setFirstName(
                view.findViewById<EditText>(R.id.info_first_name).text.toString()
            )
            viewModel.setLastName(
                view.findViewById<EditText>(R.id.info_second_name).text.toString()
            )
            viewModel.setPhone(
                view.findViewById<EditText>(R.id.info_phone).text.toString()
            )
            parentFragmentManager.popBackStack(
                ListFragment::class.java.simpleName,
                FragmentManager.POP_BACK_STACK_INCLUSIVE
            )
        }
    }
    private fun setText(view: View, viewModel: ViewModel){
        viewModel.firstNameLiveData.observe(viewLifecycleOwner){
            view.findViewById<EditText>(R.id.info_first_name).setText(it)
        }
        viewModel.lastNameLiveData.observe(viewLifecycleOwner){
            view.findViewById<EditText>(R.id.info_second_name).setText(it)
        }
        viewModel.phoneLiveData.observe(viewLifecycleOwner){
            view.findViewById<EditText>(R.id.info_phone).setText(it)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val array: Array<String> = arrayOf(
            requireView().findViewById<EditText>(R.id.info_first_name).text.toString(),
            requireView().findViewById<EditText>(R.id.info_second_name).text.toString(),
            requireView().findViewById<EditText>(R.id.info_phone).text.toString()
        )
        outState.putStringArray(ARG_PARAM, array)
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        if (savedInstanceState != null) {
            val param = savedInstanceState.getStringArray(ARG_PARAM)
            if (param?.isEmpty() != true) {
                getText(param!!)
            }
        }
    }

    private fun getText(param: Array<String>){
        requireView().findViewById<EditText>(R.id.info_first_name).setText(param[0])
        requireView().findViewById<EditText>(R.id.info_second_name).setText(param[1])
        requireView().findViewById<EditText>(R.id.info_phone).setText(param[2])
    }
}

