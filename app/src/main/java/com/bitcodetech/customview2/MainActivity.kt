package com.bitcodetech.customview2

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.bitcodetech.customview2.adapters.FilesAdapter
import com.bitcodetech.customview2.databinding.ActivityMainBinding
import com.bitcodetech.customview2.views.FileView
import java.io.File

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var files: Array<File>
    private lateinit var filesAdapter: FilesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initFiles()
        initViews()
        initListeners()

    }

    private fun initFiles() {
        /*val extStorageDir = File("/storage/emulated/0/")
        files = extStorageDir!!.listFiles()*/
        files = Environment.getExternalStorageDirectory().listFiles()
    }

    private fun initViews() {
        filesAdapter = FilesAdapter(files)
        binding.recyclerFiles.adapter = filesAdapter
        binding.recyclerFiles.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }

    private fun initListeners() {
        filesAdapter.onFileClickListener = MyOnFileClickListener()
    }

    private inner class MyOnFileClickListener : FilesAdapter.OnFileClickListener {
        override fun onFileClick(position: Int, file: File, view: FileView, adapter: FilesAdapter) {
            mt("${file.name} ${position}")

            if (file.isDirectory) {
                supportActionBar!!.title = file.name
                supportActionBar!!.setDisplayHomeAsUpEnabled(true)
                supportActionBar!!.setHomeAsUpIndicator(R.drawable.back)

                adapter.files = file.listFiles()
                adapter.notifyDataSetChanged()

            } else {
                val intent = Intent(Intent.ACTION_VIEW)
                intent.setDataAndType(
                    Uri.parse(file.absolutePath),
                    when {
                        file.endsWith("mp3") ->
                            "audio/mp3"
                        file.endsWith("mp4") ->
                            "video/mp4"
                        file.endsWith("png") ->
                            "image/png"
                        else ->
                            "image/png"
                    }
                )
                startActivity(intent)
            }
        }
    }

    private fun mt(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }
}