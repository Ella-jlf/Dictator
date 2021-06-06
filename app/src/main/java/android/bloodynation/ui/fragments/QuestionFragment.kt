package android.bloodynation.ui.fragments

import android.annotation.SuppressLint
import android.bloodynation.R
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.bloodynation.databinding.FragmentQuestionBinding
import android.bloodynation.ui.entities.GameLogic
import android.bloodynation.ui.additional.FractionAdapter
import android.util.Log
import android.widget.GridLayout
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext


class QuestionFragment : Fragment(), View.OnClickListener {
    lateinit var mBinding: FragmentQuestionBinding
    private val gameLogic: GameLogic by activityViewModels()


    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentQuestionBinding.inflate(inflater, container, false)

        //setting up the adapter
        mBinding.listViewFractionAttitude.apply {
            adapter = FractionAdapter(gameLogic.getRawFractions())
            layoutManager = GridLayoutManager(requireContext(), 1)
        }

        mBinding.buttonYes.setOnClickListener(this)
        mBinding.buttonNo.setOnClickListener(this)


        gameLogic.getCurQuestionLiveData().observe(viewLifecycleOwner, {
            mBinding.questionViewMain.text = it.questionInstance.question
        })

        gameLogic.getScoreLiveData().observe(viewLifecycleOwner, {
            mBinding.questionsScore.text = getString(R.string.score) + ":" + it.toString()
        })

        gameLogic.getAliveLiveData().observe(viewLifecycleOwner, {
            if (!it) {
                if (gameLogic.getScoreLiveData().value != 0) {
                    val dialog = EndGameDialogFragment()
                    dialog.show(parentFragmentManager, null)
                }
                gameLogic.initGame()
            }
        })

        //TODO: Delete
        val dbManager = gameLogic.getDatabaseManager(requireContext())
        CoroutineScope(Dispatchers.IO).launch {
            //dbManager.getQuestions()
        }

        return mBinding.root
    }


    override fun onClick(v: View?) {
        when (v) {
            mBinding.buttonYes -> {
                gameLogic.nextRound(true)
            }
            mBinding.buttonNo -> {
                gameLogic.nextRound(false)
            }
        }
        mBinding.listViewFractionAttitude.adapter!!.notifyDataSetChanged()
    }
}