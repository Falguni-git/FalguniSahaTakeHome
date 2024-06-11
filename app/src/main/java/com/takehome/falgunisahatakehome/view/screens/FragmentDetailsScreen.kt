package com.takehome.falgunisahatakehome.view.screens

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.takehome.falgunisahatakehome.R
import com.takehome.falgunisahatakehome.viewmodel.MainViewModel

class FragmentDetailsScreen : Fragment() {
    private lateinit var mainViewModel: MainViewModel
    private lateinit var userName: TextView
    private lateinit var description: TextView
    private lateinit var updated: TextView
    private lateinit var stargazers: TextView
    private lateinit var fork: TextView
    private lateinit var totalForks: TextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val userInformation = LayoutInflater.from(container!!.context).inflate(R.layout.layout_user_detail_screen,container,false)
        return userInformation
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainViewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]
        setUpViews(view)
        activity?.let {
            mainViewModel.userDetail.observe(it) { data ->
                userName.text = buildString {
                    append(userName.text.toString())
                    append(data.details.name)
                }
                description.text = buildString {
                    append(description.text.toString())
                    append(data.details.description)
                }
                updated.text = buildString {
                    append(updated.text.toString())
                    append(data.details.updatedAt)
                }
                stargazers.text = buildString {
                    append(stargazers.text.toString())
                    append(data.details.stargazersCount.toString())
                }
                fork.text = buildString {
                    append(fork.text.toString())
                    append(data.details.forks.toString())
                }
                totalForks.text = buildString {
                    append(totalForks.text.toString())
                    append(data.totalForks.toString())
                }
                //text color is changed if totalforks>5000 to show the star badge
                if(data.totalForks>5000) totalForks.setTextColor(Color.RED)
            }
        }
    }

    private fun setUpViews(view: View) {
        userName = view.findViewById(R.id.name)
        description = view.findViewById(R.id.description)
        updated = view.findViewById(R.id.updated)
        stargazers = view.findViewById(R.id.stargazersCount)
        fork = view.findViewById(R.id.fork)
        totalForks = view.findViewById(R.id.totalFork)
    }
}