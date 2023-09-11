package com.bitcodetech.customview2.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bitcodetech.customview2.R
import com.bitcodetech.customview2.databinding.FileViewBinding
import java.io.File

class FilesAdapter(
    val files : Array<File>
) : RecyclerView.Adapter<FilesAdapter.FileViewHolder>(){

    interface OnFileClickListener {
        fun onFileClick(position: Int, file : File, view: View, adapter : FilesAdapter)
    }

    var onFileClickListener : OnFileClickListener? = null


    inner class FileViewHolder(view : View) : RecyclerView.ViewHolder(view) {

        val fileViewBinding : FileViewBinding

        init {
            fileViewBinding = FileViewBinding.bind(view)

            fileViewBinding.root.setOnClickListener {
                onFileClickListener?.onFileClick(
                    adapterPosition,
                    files[adapterPosition],
                    fileViewBinding.root,
                    this@FilesAdapter
                )
            }

        }

        /*val txtFileName : TextView
        val imgFile : ImageView
        init{
            txtFileName = view.findViewById(R.id.txtFileName)
            imgFile = view.findViewById(R.id.imgFile)
        }*/
    }

    override fun getItemCount() = files.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FileViewHolder {
        return FileViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.file_view, null)
        )
    }

    override fun onBindViewHolder(holder: FileViewHolder, position: Int) {
        val file = files[position]
        holder.fileViewBinding.txtFileName.text = file.name

        when {
            file.isDirectory ->
                holder.fileViewBinding.imgFile.setImageResource(R.drawable.folder)
            file.absolutePath.endsWith("mp3") ->
                holder.fileViewBinding.imgFile.setImageResource(R.drawable.audio)
            file.absolutePath.endsWith("mp4") ->
                holder.fileViewBinding.imgFile.setImageResource(R.drawable.video)
            file.absolutePath.endsWith("png") ->
                holder.fileViewBinding.imgFile.setImageResource(R.drawable.image)
            file.absolutePath.endsWith("jpeg") ->
                holder.fileViewBinding.imgFile.setImageResource(R.drawable.image)
        }
    }


}