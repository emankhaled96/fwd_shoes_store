package com.udacity.shoestore.ui.shoesList

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.core.view.marginTop
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.fragment.findNavController
import com.squareup.picasso.Picasso
import com.udacity.shoestore.R
import com.udacity.shoestore.ShoesViewModel
import com.udacity.shoestore.databinding.FragmentShoesListBinding
import com.udacity.shoestore.models.Shoe
import timber.log.Timber


/**
 * A simple [Fragment] subclass.
 * Use the [ShoesListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ShoesListFragment : Fragment() {
    private lateinit var binding: FragmentShoesListBinding
    private lateinit var viewModel: ShoesViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentShoesListBinding.inflate(LayoutInflater.from(context), container, false)
        viewModel = ViewModelProvider(requireActivity()).get(ShoesViewModel::class.java)
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpListeners()
        setUpObservers()
    }

    private fun setUpObservers() {
        viewModel.shoeList.observe(viewLifecycleOwner) {
            Timber.tag("Shoes").d(it.toString())
            updateUI(it)

        }
    }

    private fun updateUI(list: List<Shoe>?) {
        val layoutParams1: LinearLayout.LayoutParams = LinearLayout.LayoutParams(
            LinearLayoutCompat.LayoutParams.MATCH_PARENT,
            LinearLayoutCompat.LayoutParams.WRAP_CONTENT
        )
        list?.forEach { shoe ->
            val linearLayoutVertical = LinearLayout(context).apply {
                layoutParams = layoutParams1
                orientation = LinearLayout.VERTICAL
//                setPadding(10, 5, 10, 5)
                layoutParams1.setMargins(10, 10, 10, 10)

            }
            val linearLayoutHorizontal = LinearLayout(context).apply {
                layoutParams = layoutParams1
                orientation = LinearLayout.HORIZONTAL
                layoutParams1.setMargins(25, 10, 10, 10)
//                setPadding(10, 5, 10, 5)


            }
            val shoeImage = ImageView(context).apply {
                layoutParams = LinearLayout.LayoutParams(250, 250)

            }
            val shoeName = TextView(context).apply {
                text = "Name: ${shoe.name}"
                textSize = 18f
                setTextColor(resources.getColor(R.color.colorAccent))
            }
            val companyName = TextView(context).apply {
                text = "Company Name: ${shoe.company}"
                textSize = 16f
                setTextColor(resources.getColor(R.color.colorAccent))


            }
            val shoeSize = TextView(context).apply {
                text = "Size: ${shoe.size}"
                textSize = 16f
                setTextColor(resources.getColor(R.color.colorAccent))


            }
            val description = TextView(context).apply {
                text = "Description: ${shoe.description}"
                textSize = 16f
                setTextColor(resources.getColor(R.color.colorAccent))


            }
            Picasso.get().load(shoe.images.get(0)).into(shoeImage)
            linearLayoutHorizontal.addView(shoeImage)
            linearLayoutHorizontal.addView(linearLayoutVertical)
            linearLayoutVertical.addView(shoeName)
            linearLayoutVertical.addView(companyName)
            linearLayoutVertical.addView(shoeSize)
            linearLayoutVertical.addView(description)

            binding.shoesLinearLayout.addView(linearLayoutHorizontal)
        }

    }

    private fun setUpListeners() {
        binding.floatingActionButton.setOnClickListener {
            findNavController().navigate(ShoesListFragmentDirections.actionShoesListFragmentToAddShoesFragment())
        }

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.overflow_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.loginFragment -> findNavController().navigate(R.id.loginFragment)
        }
        return super.onOptionsItemSelected(item)
    }


}