package com.picpay.desafio.android.presenter

import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.picpay.desafio.android.R
import com.picpay.desafio.android.data.PicPayService
import com.picpay.desafio.android.databinding.ActivityMainBinding
import com.picpay.desafio.android.domain.model.User
import com.picpay.desafio.android.presenter.adapter.ContactAdapter
import com.picpay.desafio.android.presenter.adapter.ContactHeaderAdapter
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.security.SecureRandom
import java.security.cert.CertificateException
import java.security.cert.X509Certificate
import javax.net.ssl.SSLContext
import javax.net.ssl.SSLSocketFactory
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

class MainActivity : AppCompatActivity() {

    private var binder: ActivityMainBinding? = null

    private lateinit var contactsAdapter: ContactAdapter

    private val url = "https://609a908e0f5a13001721b74e.mockapi.io/picpay/api/"

    private val gson: Gson by lazy { GsonBuilder().create() }

    private val okHttp: OkHttpClient by lazy {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            OkHttpClient.Builder()
                .build()
        } else {
            getUnsafeOkHttpClient().build()
        }
    }

    private fun getUnsafeOkHttpClient(): OkHttpClient.Builder =
        try {
            // Create a trust manager that does not validate certificate chains
            val trustAllCerts: Array<TrustManager> = arrayOf(
                object : X509TrustManager {
                    @Throws(CertificateException::class)
                    override fun checkClientTrusted(chain: Array<X509Certificate?>?,
                                                    authType: String?) = Unit

                    @Throws(CertificateException::class)
                    override fun checkServerTrusted(chain: Array<X509Certificate?>?,
                                                    authType: String?) = Unit

                    override fun getAcceptedIssuers(): Array<X509Certificate> = arrayOf()
                }
            )
            // Install the all-trusting trust manager
            val sslContext: SSLContext = SSLContext.getInstance("SSL")
            sslContext.init(null, trustAllCerts, SecureRandom())
            // Create an ssl socket factory with our all-trusting manager
            val sslSocketFactory: SSLSocketFactory = sslContext.socketFactory
            val builder = OkHttpClient.Builder()
            builder.sslSocketFactory(sslSocketFactory,
                trustAllCerts[0] as X509TrustManager)
            builder.hostnameVerifier { _, _ -> true }
            builder
        } catch (e: Exception) {
            throw RuntimeException(e)
        }

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(url)
            .client(okHttp)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    private val service: PicPayService by lazy {
        retrofit.create(PicPayService::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binder = ActivityMainBinding.inflate(layoutInflater).also {
            contactsAdapter = ContactAdapter()
            val contactsHeaderAdapter = ContactHeaderAdapter()
            val concatAdapter = ConcatAdapter(contactsHeaderAdapter, contactsAdapter)
            it.recyclerView.apply {
                layoutManager = LinearLayoutManager(this@MainActivity)
                adapter = concatAdapter
            }
        }
        setContentView(binder?.root)
    }

    override fun onResume() {
        super.onResume()
        service.getUsers()
            .enqueue(object : Callback<List<User>> {
                override fun onFailure(call: Call<List<User>>, t: Throwable) {
                    val message = getString(R.string.error)
                    Toast.makeText(this@MainActivity, message, Toast.LENGTH_SHORT)
                        .show()
                }
                override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                    contactsAdapter.submitList(response.body()!!)
                }
            })
    }

    override fun onDestroy() {
        super.onDestroy()
        binder = null
    }
}
