package com.example.traveler.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.example.traveler.R
import com.example.traveler.explore_fragments.Fragment_Popular
import com.example.traveler.explore_fragments.Fragment_Rating
import com.example.traveler.explore_fragments.Fragment_Recent

class ExploreFragment : Fragment() {

    private lateinit var popularTextView: TextView
    private lateinit var ratingTextView: TextView
    private lateinit var recentTextView: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_explore, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize views
        popularTextView = view.findViewById(R.id.explore_popular_tv)
        ratingTextView = view.findViewById(R.id.explore_rating_tv)
        recentTextView = view.findViewById(R.id.explore_recent_tv)

        // Load default fragment
        replaceFrameWithFragment(Fragment_Popular())
        highlightSelectedTab(popularTextView)

        // Click listeners for each tab
        popularTextView.setOnClickListener {
            replaceFrameWithFragment(Fragment_Popular())
            highlightSelectedTab(popularTextView)
        }

        ratingTextView.setOnClickListener {
            replaceFrameWithFragment(Fragment_Rating())
            highlightSelectedTab(ratingTextView)
        }

        recentTextView.setOnClickListener {
            replaceFrameWithFragment(Fragment_Recent())
            highlightSelectedTab(recentTextView)
        }
    }

    private fun replaceFrameWithFragment(fragment: Fragment) {
        val transaction = childFragmentManager.beginTransaction()
        transaction.replace(R.id.frameLayout, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    /**
     * Highlight the selected tab and reset others
     */
    private fun highlightSelectedTab(selectedTextView: TextView) {
        val defaultColor = ContextCompat.getColor(requireContext(), R.color.black)
        val selectedColor = ContextCompat.getColor(requireContext(), R.color.blue_500) // Change this color as you like

        // Reset all to default
        popularTextView.setTextColor(defaultColor)
        ratingTextView.setTextColor(defaultColor)
        recentTextView.setTextColor(defaultColor)

        // Highlight the selected one
        selectedTextView.setTextColor(selectedColor)
    }
}
