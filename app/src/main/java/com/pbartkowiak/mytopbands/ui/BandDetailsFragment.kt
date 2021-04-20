package com.pbartkowiak.mytopbands.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pbartkowiak.mytopbands.core.ui.BaseFragment
import com.pbartkowiak.mytopbands.databinding.FragmentBandDetailsBinding

class BandDetailsFragment : BaseFragment() {

    private lateinit var viewModel: BandDetailsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = getViewModel(BandDetailsViewModel::class.java)
        viewModel.setupDetailView(arguments!!.getString(WEBSITE_URL_ID_KEY_EXTRA))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentBandDetailsBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        return binding.root
    }

    companion object {
        fun buildFragment(websiteUrl: String) =
            BandDetailsFragment().apply {
                arguments = Bundle().apply {
                    putString(WEBSITE_URL_ID_KEY_EXTRA, websiteUrl)
                }
            }
    }
}
