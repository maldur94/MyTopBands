package com.pbartkowiak.mytopbands.ui

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.pbartkowiak.mytopbands.R
import com.pbartkowiak.mytopbands.core.AppExecutors
import com.pbartkowiak.mytopbands.core.NetworkManager
import com.pbartkowiak.mytopbands.core.data.DatabaseBuilder
import com.pbartkowiak.mytopbands.core.ui.BaseActivity
import com.pbartkowiak.mytopbands.data.repository.BandRepository
import com.pbartkowiak.mytopbands.databinding.ActivityBandListBinding
import kotlinx.android.synthetic.main.item_list.*

class BandListActivity : BaseActivity() {

    private lateinit var viewModel: BandListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val database = DatabaseBuilder.build(this)
        val bandDao = database.bandsDao()
        val repository =
            BandRepository(AppExecutors, NetworkManager(this).provideBandService(), bandDao)
        val microService = BandMicroService(repository)
        viewModel = getViewModel(BandListViewModel::class.java, resources, microService)
        ActivityBandListBinding.inflate(layoutInflater).apply {
            lifecycleOwner = this@BandListActivity
            viewModel = this@BandListActivity.viewModel
            setContentView(root)
            setSupportActionBar(findViewById(R.id.toolbar))
            supportActionBar?.run { setDisplayHomeAsUpEnabled(true) }
            attachObservers(this, this@BandListActivity.viewModel)
        }
    }

    private fun attachObservers(binding: ActivityBandListBinding, viewModel: BandListViewModel) {
        viewModel.run {
            binding.swipeRefresh.setOnRefreshListener {
                binding.swipeRefresh.isRefreshing = false
                refreshBands()
            }
            refreshBands.observe(this@BandListActivity, {
                callForBands()
            })
            microService.bands.observe(this@BandListActivity) { bands ->
                onBandsDownloaded(bands)
            }
            proceedBandChosen.observe(this@BandListActivity, {
                it.getContentIfNotHandled()?.let { event ->
                    validateMachineTypeAndProceedToBandDetails(event.peek())
                }
            })
            showInternetConnectionErrorDialog.observe(this@BandListActivity, {
                it.getContentIfNotHandled()?.let { event ->
                    AlertDialog.Builder(this@BandListActivity)
                        .setIcon(
                            ContextCompat.getDrawable(
                                this@BandListActivity,
                                R.drawable.ic_fa_exclamation_triangle
                            )
                        )
                        .setTitle(getString(event.peek1()))
                        .setMessage(event.peek2())
                        .setPositiveButton(android.R.string.ok) { _: DialogInterface, _: Int -> }
                        .show()
                }
            })
        }
    }

    private fun validateMachineTypeAndProceedToBandDetails(websiteUrl: String) {
        if (itemDetailContainer != null) {
            val fragment = BandDetailsFragment.buildFragment(websiteUrl)
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.itemDetailContainer, fragment)
                .commit()
        } else {
            startActivity(BandDetailsActivity.buildIntent(this, websiteUrl))
        }
    }
}
