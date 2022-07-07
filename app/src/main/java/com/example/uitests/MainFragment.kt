package com.example.uitests

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.uitests.databinding.FragmentMainBinding
import kotlinx.coroutines.delay

class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater)


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launchWhenCreated {
            binding.progressBar.isVisible = true

            delay(1_000)

            val defaultText = "Bem vindo! "
            binding.textViewName.text =
                arguments?.getString("TEXT_VIEW_TEXT")?.let { name ->
                    defaultText + name
                } ?: defaultText

            binding.progressBar.isVisible = false
        }
    }
}