package mentalhealth.app

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.NavController
import androidx.navigation.Navigation


class HomePageFragment : Fragment(), View.OnClickListener {

    lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home_page, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        view.findViewById<Button>(R.id.weekly_stats_btn).setOnClickListener(this)
        view.findViewById<Button>(R.id.chatbot_btn).setOnClickListener(this)
        view.findViewById<Button>(R.id.profile_btn).setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.weekly_stats_btn -> navController!!.navigate(R.id.action_homePageFragment_to_weeklyStatsFragment)
            R.id.chatbot_btn-> navController!!.navigate(R.id.action_homePageFragment_to_chatBotFragment)
            R.id.profile_btn -> navController!!.navigate(R.id.action_homePageFragment_to_profileFragment)
        }
    }



}