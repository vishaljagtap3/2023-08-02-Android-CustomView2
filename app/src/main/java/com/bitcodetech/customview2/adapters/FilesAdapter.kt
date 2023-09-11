package com.bitcodetech.customview2.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bitcodetech.customview2.R
import com.bitcodetech.customview2.databinding.FileViewBinding
import com.bitcodetech.customview2.views.FileView
import java.io.File

class FilesAdapter(
    var files: Array<File>
) : RecyclerView.Adapter<FilesAdapter.FileViewHolder>() {

    interface OnFileClickListener {
        fun onFileClick(position: Int, file: File, fileView: FileView, adapter: FilesAdapter)
    }

    var onFileClickListener: OnFileClickListener? = null


    inner class FileViewHolder(
        val fileView: FileView
    ) : RecyclerView.ViewHolder(fileView) {
        init {
            fileView.setOnClickListener {
                onFileClickListener?.onFileClick(
                    adapterPosition,
                    files[adapterPosition],
                    fileView,
                    this@FilesAdapter
                )
            }
        }
    }


    override fun getItemCount() = files.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FileViewHolder {
        return FileViewHolder(
            FileView(parent.context)
        )
    }

    override fun onBindViewHolder(holder: FileViewHolder, position: Int) {
        holder.fileView.file = files[position]
    }

}