package th.ac.comsci.studen.ui.save

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import th.ac.comsci.studen.R
import java.lang.Exception
import java.net.URL
import java.util.concurrent.Executors

class SaveFragment : Fragment() {

    private lateinit var dashboardViewModel: SaveViewModel

    private var sid: EditText? = null
    private var sfullname: EditText? = null
    private var smajor: EditText? = null
    private var ssex: EditText? = null
    private var save: Button? = null
    private var state: TextView? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?


    ): View? {
        dashboardViewModel =
            ViewModelProviders.of(this).get(SaveViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_save, container, false)
        sid       = root.findViewById(R.id.std_id)
        sfullname = root.findViewById(R.id.std_fullname)
        smajor    = root.findViewById(R.id.std_major)
        ssex      = root.findViewById(R.id.std_sex)
        save      = root.findViewById(R.id.save_button)
        state     = root.findViewById(R.id.text_state)
        save!!.setOnClickListener {
            var url = "http://172.18.21.77/mobile/insert_student.php?id=" + sid!!.text.toString() +
                    "&fullname=" + sfullname!!.text.toString() +
                    "&major=" + smajor!!.text.toString() +
                    "&sex=" + ssex!!.text.toString()
            //  url = "http://172.18.21.77"

            //การลงข้อมูลใน Database
            Executors.newSingleThreadExecutor().execute ({
                val response = try{
                    val res = URL(url).readText()
                    state!!.text = res.toString()


                }
                catch (e: Exception){
                    e.printStackTrace()
                }


        //val textView: TextView = root.findViewById(R.id.text_dashboard)
        dashboardViewModel.text.observe(this, Observer {
         //   textView.text = it
        })
        return root
    }
}