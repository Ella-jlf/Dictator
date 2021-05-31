package android.bloodynation.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.bloodynation.databinding.FragmentQuestionBinding
import android.bloodynation.ui.entities.GameLogic
import android.bloodynation.ui.views.FractionAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import androidx.fragment.app.viewModels


class  QuestionFragment : Fragment() {
    lateinit var mBinding: FragmentQuestionBinding
    private val gameLogic : GameLogic by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentQuestionBinding.inflate(inflater, container, false)

        mBinding.listViewFractionAttitude.adapter = FractionAdapter(context,gameLogic.fractionsLiveData.value)

        gameLogic.fractionsLiveData.observe(viewLifecycleOwner,{
            mAdapter.updateFractionAttitude(gameLogic.fractionsLiveData.value)
        })

        //fractionList = mBinding.listViewFractionAttitude
        //fractionList.adapter = FractionAdapter(context,gameLogic.fractionsLiveData.value)

       mBinding.buttonYes.setOnClickListener {
            gameLogic.nextRound(true)
            mBinding.questionViewMain.text = gameLogic.curQuestion.question
        }
        mBinding.buttonNo.setOnClickListener {
            gameLogic.nextRound(false)
            mBinding.questionViewMain.text = gameLogic.curQuestion.question
        }


        return mBinding.root
    }

    override fun onStart() {
        super.onStart()
        gameLogic.initGame()
        textViewQuestion.text = gameLogic.curQuestion.question
    }

    fun onClickButton(view: View) {
        when (view) {
            buttonYes -> gameLogic.nextRound(true)
            buttonNo -> gameLogic.nextRound(false)
        }
        textViewQuestion.text = gameLogic.curQuestion.question

    }
}