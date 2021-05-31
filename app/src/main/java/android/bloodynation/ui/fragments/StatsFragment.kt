package android.bloodynation.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.bloodynation.databinding.FragmentStatsBinding
import android.bloodynation.ui.views.FractionAdapter
import android.widget.ListView

class StatsFragment : Fragment() {

    lateinit var mBinding: FragmentStatsBinding
    lateinit var listFractionAttitude: ListView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        mBinding = FragmentStatsBinding.inflate(inflater,container,false)
       // listFractionAttitude = mBinding.listViewFractionAttitude
       // listFractionAttitude.adapter = FractionAdapter(context)
        return mBinding.root
    }


}