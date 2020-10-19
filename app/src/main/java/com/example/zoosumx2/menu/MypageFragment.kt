package com.example.zoosumx2.menu

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.zoosumx2.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_mypage.*


class MypageFragment : Fragment() {

    var fbAuth: FirebaseAuth? = null
    var fbFirestore: FirebaseFirestore? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_mypage, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fbAuth = FirebaseAuth.getInstance()
        fbFirestore = FirebaseFirestore.getInstance()

        fbFirestore?.collection("users")?.document(fbAuth?.uid.toString())
            ?.addSnapshotListener { documentSnapshot, firebaseFirestoreException ->
                if (documentSnapshot == null) return@addSnapshotListener
                textview_username_mypage?.text = documentSnapshot.data?.get("nickname").toString()
                textview_island_name_mypage?.text =
                    documentSnapshot.data?.get("islandName").toString()

                // 경험치에 따라 레벨 측정
                val exp = documentSnapshot.data?.get("exp").toString().toInt()

                val level = when {
                    (exp in 0..99) -> 1
                    (exp in 100..299) -> 2
                    (exp in 300..599) -> 3
                    (exp in 600..999) -> 4
                    else -> 5
                }

                // 레벨 업 된 경우
//                if(level != documentSnapshot.data?.get("level")) {
//
//                }

                textview_mylevel_mypage?.text = level.toString()
                // firestore에 사용자 레벨 저장
                fbFirestore?.collection("users")?.document(fbAuth?.uid.toString())
                    ?.update("level", level)
            }

        // 포인트 내역
        linearlayout_point_mypage.setOnClickListener {
            val intent = Intent(context, PointActivity::class.java)
            startActivity(intent)
        }

        // 주섬주섬 상점
        linearlayout_store_mypage.setOnClickListener {
            val intent = Intent(context, StoreActivity::class.java)
            startActivity(intent)
        }

        // 우리동네 현황
        linearlayout_town_mypage.setOnClickListener {
            val intent = Intent(context, TownActivity::class.java)
            startActivity(intent)
        }

        // 환경설정
        linearlayout_setting_mypage.setOnClickListener {
            val intent = Intent(context, SettingActivity::class.java)
            startActivity(intent)
        }

    }
}