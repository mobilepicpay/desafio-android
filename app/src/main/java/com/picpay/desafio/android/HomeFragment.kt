package me.mqueiroz.picpay

import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.squareup.moshi.Moshi
import kotlinx.android.synthetic.main.fragment_home.*
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class HomeFragment : Fragment(R.layout.fragment_home) {

    private lateinit var adapter: UserListAdapter

    private val url = "http://careers.picpay.com/tests/mobdev/"

    private val moshi: Moshi by lazy {
        Moshi.Builder()
            .build()
    }

    private val okHttp: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .build()
    }

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(url)
            .client(okHttp)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

    private val service: PicPayService by lazy {
        retrofit.create(PicPayService::class.java)
    }

    override fun onResume() {
        super.onResume()

        adapter = UserListAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context)

        user_list_progress_bar.visibility = View.VISIBLE
        service.getUsers()
            .enqueue(object : Callback<List<User>> {
                override fun onFailure(call: Call<List<User>>, t: Throwable) {
                    val message = getString(R.string.error)

                    user_list_progress_bar.visibility = View.GONE

                    Toast.makeText(context, message, Toast.LENGTH_SHORT)
                        .show()
                }

                override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                    user_list_progress_bar.visibility = View.GONE

                    adapter.users = response.body()!!
                }
            })
    }
}