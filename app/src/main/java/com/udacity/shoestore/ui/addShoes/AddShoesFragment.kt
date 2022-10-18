package com.udacity.shoestore.ui.addShoes

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.ShoesViewModel
import com.udacity.shoestore.databinding.FragmentAddShoesBinding
import kotlinx.android.synthetic.main.fragment_add_shoes.*
import timber.log.Timber

class AddShoesFragment : Fragment() {
    private lateinit var binding: FragmentAddShoesBinding
    private lateinit var viewModel: ShoesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddShoesBinding.inflate(LayoutInflater.from(context), container, false)
        viewModel = ViewModelProvider(requireActivity()).get(ShoesViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = activity
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpObservers()
    }

    private fun setUpObservers() {
        viewModel.shoeAdded.observe(viewLifecycleOwner) { shoeAdded ->
            if (shoeAdded) {
                navigateBack()
                viewModel.setBackShoeAdded(false)

            }

        }

        viewModel.cancelClicked.observe(viewLifecycleOwner){cancel->
            if(cancel){
                navigateBack()
                viewModel.setBackCancel(false)
            }

        }
        viewModel.formValidity.observe(viewLifecycleOwner){ formValid->
            if(!formValid)
                Toast.makeText(context, "Please Complete The Shoes Information", Toast.LENGTH_SHORT)
                    .show()
        }
    }



    private fun navigateBack() {
        findNavController().navigate(AddShoesFragmentDirections.actionAddShoesFragmentToShoesListFragment())

    }


}