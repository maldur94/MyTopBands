package com.pbartkowiak.mytopbands.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.pbartkowiak.mytopbands.R
import com.pbartkowiak.mytopbands.core.ui.BaseActivity
import com.pbartkowiak.mytopbands.databinding.ActivityBandDetailsBinding
import kotlinx.android.synthetic.main.toolbar.*

const val WEBSITE_URL_ID_KEY_EXTRA = "website_url_id_key_extra"

class BandDetailsActivity : BaseActivity() {

    private lateinit var viewModel: BandDetailsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = getViewModel(BandDetailsViewModel::class.java)
        viewModel.setupDetailView(intent.extras!!.getString(WEBSITE_URL_ID_KEY_EXTRA))
        ActivityBandDetailsBinding.inflate(layoutInflater).apply {
            lifecycleOwner = this@BandDetailsActivity
            viewModel = this@BandDetailsActivity.viewModel
            setContentView(root)
            setSupportActionBar(findViewById(R.id.toolbar))
            supportActionBar?.run { setDisplayHomeAsUpEnabled(true) }
        }
        attachFragment(savedInstanceState)
    }

    private fun attachFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            val fragment = BandDetailsFragment.buildFragment(viewModel.websiteUrl.get())
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.itemDetailContainer, fragment)
                .commit()
        }
    }

    companion object {
        fun buildIntent(context: Context, websiteUrl: String) =
            Intent(context, BandDetailsActivity::class.java).apply {
                val bundle = Bundle()
                bundle.putString(WEBSITE_URL_ID_KEY_EXTRA, websiteUrl)
                putExtras(bundle)
            }
    }
}
