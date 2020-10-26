package com.example.zoosumx2.menu

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.zoosumx2.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import kotlinx.android.synthetic.main.fragment_home.*
import java.util.*

class HomeFragment : Fragment() {

    var fbAuth: FirebaseAuth? = null
    var fbFirestore: FirebaseFirestore? = null

    fun newInstance(): HomeFragment {
        val args = Bundle()

        val frag = HomeFragment()
        frag.arguments = args

        return frag
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fbAuth = FirebaseAuth.getInstance()
        fbFirestore = FirebaseFirestore.getInstance()

        var completeMission: Int

        // firestore에서 데이터 가져온 후 textview에 띄우기
        fbFirestore?.collection("users")?.document(fbAuth?.uid.toString())
            ?.addSnapshotListener { documentSnapshot, firebaseFirestoreException ->
                if (documentSnapshot == null) return@addSnapshotListener
                textview_username_home?.text = documentSnapshot.data?.get("nickname").toString()
                textview_island_name_home?.text =
                    documentSnapshot.data?.get("islandName").toString()
                textview_mypoint_home?.text = documentSnapshot.data?.get("rewardPoint").toString()
                textview_ranking_home?.text = documentSnapshot.data?.get("rank").toString()

                if (documentSnapshot.data?.get("mission").toString().toInt() == 0) {
                    completeMission = 0
                } else if ((documentSnapshot.data?.get("mission").toString()
                        .toInt() % 4) == 0
                ) { // 4의 배수
                    completeMission = 4
                } else {
                    completeMission = documentSnapshot.data?.get("mission").toString().toInt() % 4
                }
                textview_complete_mission_home?.text = completeMission.toString()

                val exp = documentSnapshot.data?.get("exp").toString().toInt()

                when (documentSnapshot.data?.get("level").toString().toInt()) {
                    1 -> {
                        imageview_medal_home?.setImageResource(R.drawable.icon_level1)
                        when {
                            (exp in 0..0) -> imageview_island_home?.setImageResource(R.drawable.icon_trashsum8)
                            (exp in 1..10) -> imageview_island_home?.setImageResource(R.drawable.icon_trashsum6)
                            (exp in 11..20) -> imageview_island_home?.setImageResource(R.drawable.icon_trashsum5)
                            (exp in 21..30) -> imageview_island_home?.setImageResource(R.drawable.icon_trashsum4)
                            (exp in 31..40) -> imageview_island_home?.setImageResource(R.drawable.icon_trashsum2)
                            (exp in 41..50) -> imageview_island_home?.setImageResource(R.drawable.icon_trashsum1)
                            (exp in 51..60) -> imageview_island_home?.setImageResource(R.drawable.icon_cleansum0)
                            (exp in 61..70) -> imageview_island_home?.setImageResource(R.drawable.icon_cleansum1)
                            (exp in 71..80) -> imageview_island_home?.setImageResource(R.drawable.icon_cleansum2)
                            (exp in 81..90) -> imageview_island_home?.setImageResource(R.drawable.icon_cleansum3)
                            (exp in 91..99) -> imageview_island_home?.setImageResource(R.drawable.icon_cleansum4)
                        }
                    }
                    2 -> imageview_medal_home?.setImageResource(R.drawable.icon_level2)
                    3 -> imageview_medal_home?.setImageResource(R.drawable.icon_level3)
                    4 -> imageview_medal_home?.setImageResource(R.drawable.icon_level4)
                    else -> imageview_medal_home?.setImageResource(R.drawable.icon_level5)
                }
            }

    }
}
