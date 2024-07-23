package com.example.teacher

import android.content.Intent
import android.os.Bundle
import android.widget.AdapterView
import android.widget.Button
import android.widget.ListView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.teacher.ui.theme.TeacherTheme

class MainActivity : ComponentActivity() {

    private lateinit var btnAddTeacher: Button
    private lateinit var listViewTeachers: ListView
    private lateinit var teacherAdapter: TeacherAdapter
    private lateinit var teacherList: ArrayList<Teacher>

    private val addTeacherLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == RESULT_OK) {
            val data = result.data
            data?.let {
                val name = it.getStringExtra("name")
                val age = it.getStringExtra("age")
                val department = it.getStringExtra("department")
                val status = it.getStringExtra("status")

                val position = it.getIntExtra("position", -1)
                if (name != null && age != null && department != null && status != null) {
                    if (position == -1) {
                        // Add new Teacher
                        teacherList.add(Teacher(name, age, department, status))
                    } else {
                        // Update existing Teacher
                        teacherList[position] = Teacher(name, age, department, status)
                    }
                    teacherAdapter.notifyDataSetChanged()
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnAddTeacher = findViewById(R.id.btnAddTeacher)
        listViewTeachers = findViewById(R.id.listViewTeachers)

        teacherList = ArrayList()
        teacherAdapter = TeacherAdapter(this, teacherList)
        listViewTeachers.adapter = teacherAdapter

        btnAddTeacher.setOnClickListener {
            val intent = Intent(this, AddTeacherActivity::class.java)
            addTeacherLauncher.launch(intent)
        }

        listViewTeachers.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
            val teacher = teacherList[position]
            val intent = Intent(this, AddTeacherActivity::class.java).apply {
                putExtra("name", teacher.name)
                putExtra("age", teacher.age)
                putExtra("department", teacher.department)
                putExtra("status", teacher.status)
                putExtra("position", position)
            }
            addTeacherLauncher.launch(intent)
        }
    }
}