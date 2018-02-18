package com.example.sally.rxjavakotlin.ui.home

import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.sally.rxjavakotlin.R
import com.example.sally.rxjavakotlin.data.model.RepositoryModel
import com.example.sally.rxjavakotlin.utils.formatDateTime
import java.util.ArrayList
import kotlinx.android.synthetic.main.item_repository.view.*


/**
 * Created by Sally Salem on 2/3/18.
 */
class RepositoryAdapter(val repositoryList: ArrayList<RepositoryModel>, val itemClickListener: OnItemSelectedListener) : RecyclerView.Adapter<RepositoryAdapter.RepositoryItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryAdapter.RepositoryItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return RepositoryItemViewHolder(inflater.inflate(R.layout.item_repository, parent, false), itemClickListener)
    }

    override fun onBindViewHolder(holder: RepositoryAdapter.RepositoryItemViewHolder, position: Int) {
        val repositoryModel = repositoryList[position]
        holder.bindData(repositoryModel)
    }

    override fun getItemCount() = repositoryList.size

    class RepositoryItemViewHolder(itemView: View, val itemClickListener: OnItemSelectedListener) : RecyclerView.ViewHolder(itemView) {

        fun bindData(repositoryModel: RepositoryModel) {
            val repositoryName = repositoryModel.name
            val repositoryDescription = repositoryModel.description
            val repositoryUpdateTime = repositoryModel.updatedAt
            val isfrok = repositoryModel.isFork

            itemView.tv_repository_name.setText(repositoryName)
            itemView.tv_repository_description.setText(repositoryDescription)
            itemView.tv_repository_updated_time.setText(repositoryUpdateTime.formatDateTime())
            itemView.setBackgroundColor(if (isfrok!!) ContextCompat.getColor(itemView.context, R.color.green_item_bg) else ContextCompat.getColor(itemView.context, R.color.red_item_bg))
            itemView.setOnClickListener(object : View.OnClickListener {
                override fun onClick(v: View?) {
                    v?.let { itemClickListener.onClick(it, adapterPosition) }
                }
            })
        }
    }
}
