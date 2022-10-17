package com.udacity.shoestore.ui.addShoes

import android.os.Bundle
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

class AddShoesFragment : Fragment() {
    private lateinit var binding: FragmentAddShoesBinding
    private lateinit var viewModel: ShoesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddShoesBinding.inflate(LayoutInflater.from(context), container, false)
        viewModel = ViewModelProvider(requireActivity()).get(ShoesViewModel::class.java)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpListeners()
    }

    private fun setUpListeners() {
        binding.shoesNameEdit.doAfterTextChanged {

            viewModel.setShoeName(it.toString())
        }
        binding.shoesSizeEdit.doAfterTextChanged {
            viewModel.setShoeSize(it.toString().toDouble())
        }
        binding.companyNameEdit.doAfterTextChanged {
            viewModel.setShoeCompany(it.toString())
        }
        binding.shoesDescriptionEdit.doAfterTextChanged {
            viewModel.setShoeDescription(it.toString())
        }
        binding.shoesImageLinkEdit.doAfterTextChanged {
            viewModel.setShoeImgLink(it.toString())
        }
        binding.addBtn.setOnClickListener {
            if (viewModel.validateForm()) {
                viewModel.addShoe()
                findNavController().navigate(AddShoesFragmentDirections.actionAddShoesFragmentToShoesListFragment())
            } else {
                Toast.makeText(context, "Please Complete The Shoes Information", Toast.LENGTH_SHORT)
                    .show()
            }
        }
        binding.cancelBtn.setOnClickListener {
            findNavController().navigate(AddShoesFragmentDirections.actionAddShoesFragmentToShoesListFragment())
        }
    }


}