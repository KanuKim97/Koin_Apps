package com.example.koin_apps

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.koin_apps.databinding.FragmentCommunityBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CommunityFragment : Fragment() {
    private lateinit var communityBinding: FragmentCommunityBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        communityBinding = FragmentCommunityBinding.inflate(inflater, container, false)
        return communityBinding.root
    }

}