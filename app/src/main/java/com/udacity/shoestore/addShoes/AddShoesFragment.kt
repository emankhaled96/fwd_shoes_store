package com.udacity.shoestore.addShoes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentAddShoesBinding

class AddShoesFragment : Fragment() {
    private lateinit var binding: FragmentAddShoesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddShoesBinding.inflate(LayoutInflater.from(context), container, false)
        return binding.root
    }


}