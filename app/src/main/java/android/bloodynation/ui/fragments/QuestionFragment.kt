package android.bloodynation.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.bloodynation.databinding.FragmentQuestionBinding
import android.bloodynation.ui.entities.GameLogic
import android.bloodynation.ui.additional.FractionAdapter
import android.widget.GridLayout
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager


class QuestionFragment : Fragment(), View.OnClickListener {
    lateinit var mBinding: FragmentQuestionBinding
    private val gameLogic: GameLogic by viewModels()
    private var alive = false


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

        if (!alive)
        gameLogic.initGame()

        return mBinding.root
    }



    override fun onStart() {
        super.onStart()
        mBinding.questionViewMain.text = gameLogic.curQuestion.question
    }


    override fun onClick(v: View?) {
        when (v) {
            mBinding.buttonYes -> {
                alive = gameLogic.nextRound(true)
            }
            mBinding.buttonNo -> {
                alive = gameLogic.nextRound(false)
            }
        }
        mBinding.questionViewMain.text = gameLogic.curQuestion.question
        mBinding.listViewFractionAttitude.adapter!!.notifyDataSetChanged()
        if (!alive){
            Toast.makeText(requireContext(),"You Died",Toast.LENGTH_LONG).show()
            gameLogic.initGame()
            mBinding.questionViewMain.text = gameLogic.curQuestion.question
        }
    }
}