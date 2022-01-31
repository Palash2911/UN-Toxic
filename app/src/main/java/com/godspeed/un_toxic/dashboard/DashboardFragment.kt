package com.godspeed.un_toxic.dashboard

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.godspeed.un_toxic.R
import com.godspeed.un_toxic.databinding.FragmentRewardsBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore


class DashboardFragment : Fragment() {

    private lateinit var dashboardViewModel: DashboardViewModel
    private var _binding: FragmentRewardsBinding? = null
    private val binding get() = _binding!!
    private lateinit var addsBtn: FloatingActionButton
    private lateinit var recv: RecyclerView
    private lateinit var userList: ArrayList<UserData>
    private lateinit var userAdapter: UserAdapter
    private lateinit var database: FirebaseFirestore
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        dashboardViewModel = ViewModelProvider(this).get(DashboardViewModel::class.java)
        _binding = FragmentRewardsBinding.inflate(inflater, container, false)
        database = FirebaseFirestore.getInstance()
        auth = FirebaseAuth.getInstance();
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        userList = ArrayList()
        /**set find Id*/
        addsBtn = view.findViewById(R.id.addingBtn)
        recv = view.findViewById(R.id.mRecycler)
        /**set Adapter*/
        userAdapter = UserAdapter(this, userList)
        /**setRecycler view Adapter*/
        Log.d("User size", userAdapter.itemCount.toString())
        recv.layoutManager = LinearLayoutManager(requireContext())
        recv.adapter = userAdapter

        database.collection("Profiles/" + FirebaseAuth.getInstance().uid.toString() + "/Goals")
            .get().addOnSuccessListener { snapshot ->
            for (goal in snapshot) {
                val userData = UserData(
                    goal.data["usergoal"] as String,
                    goal.data["usercost"] as String,
                    goal.data["saved"] as String
                );
                userList.add(userData);
            }
            userAdapter.notifyDataSetChanged();
        }

        addsBtn.setOnClickListener { addInfo() }
    }


    private fun addInfo() {
        val inflter = LayoutInflater.from(requireContext())
        val v = inflter.inflate(R.layout.add_item, null)

        /**set view*/
        val Goal = v.findViewById<EditText>(R.id.GOAL)
        val Cost = v.findViewById<EditText>(R.id.COST)

        val addDialog = AlertDialog.Builder(requireContext())

        addDialog.setView(v)
        addDialog.setPositiveButton("Ok") { dialog, _ ->
            val goal = Goal.text.toString()
            val cost = (Cost.text.toString());
            val userData = UserData(goal, cost, "0");
            userList.add(userData)
            database.collection("/Profiles/" + FirebaseAuth.getInstance().uid.toString() + "/Goals")
                .document(goal).set(userData).addOnSuccessListener {
                Toast.makeText(context, "Goal ADDED !! ", Toast.LENGTH_SHORT).show()
            }
            userAdapter.notifyDataSetChanged()

            dialog.dismiss()
        }
        addDialog.setNegativeButton("Cancel") { dialog, _ ->
            dialog.dismiss()
            Toast.makeText(requireContext(), "Cancel", Toast.LENGTH_SHORT).show()
        }
        addDialog.create()
        addDialog.show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}