package com.takehome.falgunisahatakehome.view.screens

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.takehome.falgunisahatakehome.R
import com.takehome.falgunisahatakehome.model.DetailScreenModel
import com.takehome.falgunisahatakehome.adapter.ListAdapter
import com.takehome.falgunisahatakehome.model.UserRepoResponse
import com.takehome.falgunisahatakehome.view.MainActivity
import com.takehome.falgunisahatakehome.viewmodel.MainViewModel

class FragmentUserInformationScreen : Fragment(), ListAdapter.ClickListener {
    private lateinit var mainViewModel: MainViewModel
    private lateinit var btnSend: Button
    private lateinit var recyclerview: RecyclerView
    private lateinit var userName: TextView
    private lateinit var userImage: ImageView
    private lateinit var searchText: EditText
    private var totalNumberOfForks = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val userInformation = LayoutInflater.from(container!!.context)
            .inflate(R.layout.layout_fragment_user_information_screen, container, false)
        activity?.onBackPressedDispatcher?.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    requireActivity().finish()
                }
            })
        return userInformation
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainViewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]
        btnSend = view.findViewById(R.id.searchButton)
        setUpViews(view)
        subscribe()
        btnSend.setOnClickListener {
            val searchItem = searchText.text ?: null
            mainViewModel.getUserData(searchItem.toString())
            mainViewModel.getRepoList(searchItem.toString())
        }
    }

    /**
     * @method to set the views
     */
    private fun setUpViews(view: View) {
        userName = view.findViewById(R.id.userNameText)
        userImage = view.findViewById(R.id.avatarImg)
        searchText = view.findViewById(R.id.searchText)
        recyclerview = view.findViewById(R.id.repoRecyclerview)
        recyclerview.layoutManager = LinearLayoutManager(activity)
    }

    /**
     * @method to register all the subscriber
     */
    private fun subscribe() {
        val dialog = AlertDialog.Builder(requireActivity()).setMessage(R.string.loading).create()
        activity?.let {
            mainViewModel.getRepositoryInstance().isLoading.observe(it) { isLoading ->
                if (isLoading) dialog.show() else dialog.cancel()
            }
        }

        activity?.let {
            val errorDialog =
                AlertDialog.Builder(requireActivity()).setMessage(R.string.loading).create()
            mainViewModel.getRepositoryInstance().isError.observe(it) { isError ->
                if (isError) errorDialog.show() else errorDialog.cancel()
            }
        }

        activity?.let {
            mainViewModel.getRepositoryInstance().userData.observe(it) { data ->
                userName.text = data.name
                Glide.with(this).load(data.avatarUrl).into(userImage)
            }
        }
        activity?.let {
            mainViewModel.getRepositoryInstance().userRepo.observe(it) { data ->
                val adapter = ListAdapter(data, this)
                // Setting the Adapter with the recyclerview
                recyclerview.adapter = adapter
                recyclerview.isNestedScrollingEnabled = false
                totalNumberOfForks = 0
                for (item in data) totalNumberOfForks += item.forks
            }
        }
    }

    override fun onItemClick(item: UserRepoResponse) {
        mainViewModel.sendClickEvents(DetailScreenModel(item, totalNumberOfForks))
        (activity as MainActivity).replaceWithDetailScreen()

    }
}