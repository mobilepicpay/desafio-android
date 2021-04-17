package com.picpay.desafio.android

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.picpay.desafio.android.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private lateinit var binding: ActivityMainBinding

    private lateinit var adapter: UserListAdapter

    private val url = "http://careers.picpay.com/tests/mobdev/"

   //private val gson: Gson by lazy { GsonBuilder().create() }

   //private val okHttp: OkHttpClient by lazy {
   //    OkHttpClient.Builder()
   //        .build()
   //}

   //private val retrofit: Retrofit by lazy {
   //    Retrofit.Builder()
   //        .baseUrl(url)
   //        .client(okHttp)
   //        .addConverterFactory(GsonConverterFactory.create(gson))
   //        .build()
   //}

   //private val service: PicPayService by lazy {
   //    retrofit.create(PicPayService::class.java)
   //}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = UserListAdapter()
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        binding.userListProgressBar.visibility = View.VISIBLE
        //service.getUsers()
        //    .enqueue(object : Callback<List<User>> {
        //        override fun onFailure(call: Call<List<User>>, t: Throwable) {
        //            val message = getString(R.string.error)
//
        //            binding.userListProgressBar.visibility = View.GONE
        //            binding.recyclerView.visibility = View.GONE
//
        //            Toast.makeText(this@MainActivity, message, Toast.LENGTH_SHORT)
        //                .show()
        //        }
//
        //        override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
        //            binding.userListProgressBar.visibility = View.GONE
//
        //            adapter.users = response.body()!!
        //        }
        //    })
    }
}
