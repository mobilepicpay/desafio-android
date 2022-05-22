package com.picpay.desafio.android.presenter

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.picpay.desafio.android.R
import com.picpay.desafio.android.databinding.ActivityMainBinding
import com.picpay.desafio.android.domain.repository.ContactRepository
import com.picpay.desafio.android.presenter.adapter.ContactAdapter
import com.picpay.desafio.android.presenter.adapter.ContactHeaderAdapter
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private var binder: ActivityMainBinding? = null

    private val repository by inject<ContactRepository>()

    private lateinit var contactsAdapter: ContactAdapter

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
        lifecycleScope.launch {
            repository.getContacts().onSuccess {
                contactsAdapter.submitList(it)
            }.onFailure {
                val message = getString(R.string.error)
                Toast.makeText(this@MainActivity, message, Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binder = null
    }
}
