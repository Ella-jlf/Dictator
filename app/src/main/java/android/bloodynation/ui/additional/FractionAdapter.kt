package android.bloodynation.ui.additional

import android.bloodynation.R
import android.bloodynation.ui.entities.Fraction
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView


class FractionAdapter( fractionAttitude: ArrayList<Fraction>) :
    RecyclerView.Adapter<FractionViewHolder>() {

    private var mFractionAttitude = fractionAttitude


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FractionViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)

        //inflating my row_fraction layout
        val view: View = inflater.inflate(R.layout.row_fraction, parent, false)
        return FractionViewHolder(view)
    }

    override fun onBindViewHolder(holder: FractionViewHolder, position: Int) {
        mFractionAttitude[position].also {
            holder.fractionName.text = it.name
            holder.fractionAttitude.text = it.currentAttitude.toString()
        }
    }

    override fun getItemCount(): Int {
        return 4
    }

}