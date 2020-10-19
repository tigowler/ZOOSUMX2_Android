package com.example.zoosumx2

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

//class RankingAdapter(val context: Context, val rankingList: ArrayList<RankingData>) :
//    RecyclerView.Adapter<RankingAdapter.Holder>() {
//
//    var fbAuth: FirebaseAuth? = null
//    var fbFirestore: FirebaseFirestore? = null
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
//        val view = LayoutInflater.from(context).inflate(R.layout.ranking_list_item, parent, false)
//
//        fbAuth = FirebaseAuth.getInstance()
//        fbFirestore = FirebaseFirestore.getInstance()
//
//        return Holder(view)
//    }
//
//    override fun getItemCount(): Int {
//        return rankingList.size
//    }
//
//    override fun onBindViewHolder(holder: Holder, position: Int) {
//        holder.bind(rankingList[position], context)
//    }
//
//    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//
//        // RankingData 클래서 ArrayList 생성
//        val townRanking : ArrayList<RankingData> = arrayListOf()
//
//        // townRanking의 문서를 불러온 뒤 RankingData로 변환해서 ArrayList에 담음
//        init {
//            fbFirestore?.collection("users")?.addSnapshotListener { querySnapshot, firebaseFirestoreException ->
//                // ArrayList 비워줌
//                townRanking.clear()
//
//                for(snapshot in querySnapshot!!.documents) {
//                    val item = snapshot.toObject(RankingData::class.java)
//                    townRanking.add(item!!)
//                }
//                notifyDataSetChanged()
//            }
//        }
//
//        val userProfileImg = itemView.findViewById<ImageView>(R.id.circleimageview_profile_town)
//        val userRanking = itemView.findViewById<TextView>(R.id.textview_ranking_town)
//        val userName = itemView.findViewById<TextView>(R.id.textview_name_town)
//        val userLevel = itemView.findViewById<TextView>(R.id.textview_level_town)
//        val userExp = itemView.findViewById<TextView>(R.id.textview_exp_town)
//
//        fun bind(rankingData: RankingData, context: Context) {
//
//            /* dogPhoto의 setImageResource에 들어갈 이미지의 id를 파일명(String)으로 찾고,
//            이미지가 없는 경우 안드로이드 기본 아이콘을 표시한다.*/
//
//            if (rankingData.profileImg != "") {
//                val resourceId = context.resources.getIdentifier(
//                    rankingData.profileImg,
//                    "drawable",
//                    context.packageName
//                )
//                userProfileImg?.setImageResource(resourceId)
//            } else {
//                userProfileImg?.setImageResource(R.mipmap.ic_launcher)
//            }
//
//            // 나머지 TextView와 String, Int 데이터를 연결
//            userRanking?.text = rankingData.ranking.toString()
//            userName?.text = rankingData.name
//            userLevel?.text = rankingData.level.toString()
//            userExp?.text = rankingData.exp.toString()
//        }
//    }
//}