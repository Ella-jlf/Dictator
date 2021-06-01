package android.bloodynation.ui.additional

import android.bloodynation.R
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class FractionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val fractionName: TextView = itemView.findViewById(R.id.row_fraction_name)
    val fractionAttitude: ProgressBar = itemView.findViewById(R.id.row_fraction_attitude)

}

