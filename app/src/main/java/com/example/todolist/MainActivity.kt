package com.example.todolist

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*

lateinit var inputTask : EditText
private lateinit var TaskListView : ListView
var taskList = ArrayList<String>()
private var Adapter : ArrayAdapter<String>? = null

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        inputTask =findViewById(R.id.input_task)
        TaskListView =findViewById(R.id.todolist)

        add_task.setOnClickListener {
            addTask()

        }
    }

     fun addTask() {
        val text= inputTask.text.toString()
        if (text.isEmpty()){
            Toast.makeText(this , "please enter an item", Toast.LENGTH_SHORT).show()
        }
        else {
            taskList.add(text)
            Adapter = ArrayAdapter(this, R.layout.item_list , R.id.title , taskList)
            TaskListView.adapter= Adapter
        }//else
    }//addTask

     fun deleteTask(view: View){
        val parent = view.parent as View
        val taskTextView = parent.findViewById<TextView>(R.id.title)
        val task = taskTextView.text.toString()
        taskList.removeAt(taskList.indexOf(task))
        Adapter?.notifyDataSetChanged()

    }
fun updateTask(view: View) {
    val parent = view.parent as View
    val taskTextView = parent.findViewById<TextView>(R.id.title).text.toString()
    var index= taskList.indexOf(taskTextView)
    var taskEditText= EditText(this)
    var dialog= AlertDialog.Builder(this)
        .setTitle("update")
        .setView(taskEditText)
        .setPositiveButton("update" , DialogInterface.OnClickListener{
         dialog, which ->
         if(taskEditText.text.isEmpty()){
             Toast.makeText(this , "please enter an item", Toast.LENGTH_SHORT).show() }
         else { taskList.set(index ,taskEditText.text.toString()) }
       Adapter?.notifyDataSetChanged()
        })
        .setNegativeButton("cancel" , null)
        .create()
    dialog.show()
}//update

}//class