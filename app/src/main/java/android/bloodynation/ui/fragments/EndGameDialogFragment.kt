package android.bloodynation.ui.fragments

import android.app.Dialog
import android.bloodynation.R
import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import java.lang.IllegalStateException

class EndGameDialogFragment : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        var close: View? = null
        val alertDialog = activity?.let {

            val builder = AlertDialog.Builder(it)
            val inflatedView = layoutInflater.inflate(R.layout.dialog_fragment_end_game, null)
            builder.setView(inflatedView)
            close = inflatedView.findViewById(R.id.dialog_view)
            builder.create()

        } ?: throw IllegalStateException("Activity  is null")

        close?.let {
            it.setOnClickListener {
                alertDialog.dismiss()
            }
        }

        return alertDialog
    }

}