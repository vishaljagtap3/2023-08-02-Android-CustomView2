package com.bitcodetech.customview2.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.bitcodetech.customview2.R
import com.bitcodetech.customview2.databinding.FileViewBinding
import java.io.File

class FileView(
    context: Context,
    attributeSet: AttributeSet?
) : LinearLayout(context, attributeSet){

    private val fileViewBinding : FileViewBinding

    init {
        val view = LayoutInflater.from(context).inflate(R.layout.file_view, null)
        this.addView(view)
        fileViewBinding = FileViewBinding.bind(view)
    }

    constructor(context: Context) : this(context, null)

    var file : File? = null
    set(value) {
        if(value == null) {
            return
        }

        field = value
        fileViewBinding.txtFileName.text = value!!.name
        when {
            value.isDirectory ->
                fileViewBinding.imgFile.setImageResource(R.drawable.folder)
            value.name.endsWith("mp3") ->
                fileViewBinding.imgFile.setImageResource(R.drawable.audio)
            value.name.endsWith("mp4") ->
                fileViewBinding.imgFile.setImageResource(R.drawable.video)
            value.name.endsWith("png") ->
                fileViewBinding.imgFile.setImageResource(R.drawable.image)
            value.name.endsWith("jpeg") ->
                fileViewBinding.imgFile.setImageResource(R.drawable.image)
        }

        if(value.isFile) {
            fileViewBinding.txtFileSize.text = "${value.length()}"
        }

    }

}