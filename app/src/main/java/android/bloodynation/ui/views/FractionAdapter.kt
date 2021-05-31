package android.bloodynation.ui.views

import android.annotation.SuppressLint
import android.bloodynation.R
import android.bloodynation.databinding.RowFractionBinding
import android.bloodynation.ui.entities.Fraction
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import org.w3c.dom.Text

class FractionAdapter(context: Context?, fractionAttitude: ArrayList<Fraction>?) : BaseAdapter() {

    private val mContext = context
    private var mFractionAttitude = fractionAttitude
    lateinit var name : TextView
    lateinit var attitude: TextView

    override fun getCount(): Int {
        return 4
    }

    override fun getItem(position: Int): Any {
        return "Can't understand"
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val layoutInflater = LayoutInflater.from(mContext)
        val row: View = layoutInflater.inflate(R.layout.row_fraction, parent,false)
        name = row.findViewById(R.id.row_fraction_name)
        attitude = row.findViewById(R.id.row_fraction_attitude)
        name.text = mFractionAttitude?.get(position-1)?.name ?: "Fraction"
        attitude.text = mFractionAttitude?.get(position-1)?.currentAttitude.toString()
        return row
    }

    override fun notifyDataSetChanged() {
        super.notifyDataSetChanged()
    }

    fun updateFractionAttitude(fractionAttitude: ArrayList<Fraction>?){
        mFractionAttitude = fractionAttitude
        notifyDataSetChanged()
    }

}