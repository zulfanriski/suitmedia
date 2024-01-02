package com.example.suitmedia

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.suitmedia.adapter.UserAdapter
import com.example.suitmedia.data.User

class thirdscreen : AppCompatActivity(), UserAdapter.OnItemClickListener {
    private lateinit var viewModel: thirdscreenViewModel
    private lateinit var adapter: UserAdapter
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_thirdscreen)

        viewModel = ViewModelProvider(this).get(thirdscreenViewModel::class.java)
        val loadingIndicator = findViewById<ProgressBar>(R.id.loadingIndicator)
        recyclerView = findViewById(R.id.recyclerview)
        recyclerView.layoutManager = LinearLayoutManager(this)

        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout)
        adapter = UserAdapter(this)
        recyclerView.adapter = adapter

        viewModel.users.observe(this, Observer {
            if (it.isEmpty()) {
                setContentView(R.layout.activity_thirdscreen_error)
                val back = findViewById<Button>(R.id.home)
                back.setOnClickListener {
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                }
            } else {
                adapter.submitList(it)
            }
            loadingIndicator.visibility = View.GONE
        })

        viewModel.getUsers()

        swipeRefreshLayout.setOnRefreshListener {
            refreshData()
        }

        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val visibleItemCount = layoutManager.childCount
                val totalItemCount = layoutManager.itemCount
                val firstVisibleItem = layoutManager.findFirstVisibleItemPosition()

                if (!viewModel.isLoading && !viewModel.isLastPage) {
                    if (visibleItemCount + firstVisibleItem >= totalItemCount && firstVisibleItem >= 0) {
                        viewModel.getUsers()
                        loadingIndicator.visibility = View.VISIBLE
                    }
                }
            }
        })
    }

    private fun refreshData() {
        viewModel.isLastPage = false
        viewModel.currentPage = 1
        viewModel.getUsers()
        swipeRefreshLayout.isRefreshing = false
    }

    override fun onItemClicked(user: User) {
        val intent = Intent()
        intent.putExtra("selectedUserName", user.first_name)
        setResult(RESULT_OK, intent)
        finish()
    }
}
