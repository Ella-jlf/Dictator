package android.bloodynation.ui.entities

class Question (val question: String,val inflns: ArrayList<Influence> = ArrayList() ) {

    fun addInfluence(influence: Influence){
        inflns.add(influence)
    }

}