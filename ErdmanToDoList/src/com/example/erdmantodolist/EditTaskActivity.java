package com.example.erdmantodolist;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class EditTaskActivity extends Activity {
	private ToDoList toDoList;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_task);
		toDoList = new ToDoList(this);
		
		//Get Order Information from the PurchaseActivity
		Intent intent = getIntent();
		int taskId = intent.getIntExtra("com.example.Erdmantodolist.taskId",0);
		if (taskId !=0) {
			populateTaskData(taskId);
			
		}
	}
		
	private void populateTaskData(int taskId)  {
		//Bind to UI Fields
		TextView textID = (TextView)findViewById(R.id.textID);
		CheckBox checkPriority = (CheckBox)findViewById(R.id.checkBoxPriority);
		EditText editDate = (EditText)findViewById(R.id.editDueDate);
		EditText editTask = (EditText)findViewById(R.id.editTask);
		CheckBox checkBoxDone = (CheckBox)findViewById(R.id.checkBoxDone);
		
		//Create Task
		Task t = toDoList.getTask(taskId);
		
		//Display Data
		textID.setText(String.valueOf(t.getId()));
		checkPriority.setChecked(t.getPriority());
		editDate.setText(t.getDate());
		editTask.setText(t.getTaskDetails());
		checkBoxDone.setChecked(t.getCompleted());
		
		
		}
	
	//Handle Button Clicks
		public void handleClick (View v)  {
			switch (v.getId()) {
			case R.id.buttonAddTask:
				break;
			case R.id.buttonEditTask:
				break;
			case R.id.buttonDeleteTask:
				break;
			case R.id.buttonMenu:
				Intent intent = new Intent (this, MainActivity.class);
				startActivity(intent);
				break;
			}
		}
		
		private void editTaskData()  {
			//Bind UI to fields
			TextView textID = (TextView)findViewById(R.id.textID);
			CheckBox checkPriority = (CheckBox)findViewById(R.id.checkBoxPriority);
			EditText editDate = (EditText)findViewById(R.id.editDueDate);
			EditText editTask = (EditText)findViewById(R.id.editTask);
			CheckBox checkBoxDone = (CheckBox)findViewById(R.id.checkBoxDone);
			
			//Check if box has data:
			if(textID.getText().toString().length()> 0) {
				int id = Integer.valueOf(textID.getText().toString());
				Task t = toDoList.getTask(id);
				
				t.setPriority(checkPriority.isChecked());
				t.setDate(editDate.getText().toString());
				t.setTask(editTask.getText().toString());
				t.setCompleted(checkBoxDone.isChecked());
				
				toDoList.editTask(t);
				
				Intent intent = new Intent(this, MainActivity.class);
				startActivity(intent);
				
			}
		}
		
		private void addToDo()  {
			//Bind to UI Fields
			TextView textID = (TextView)findViewById(R.id.textID);
			CheckBox checkPriority = (CheckBox)findViewById(R.id.checkBoxPriority);
			EditText editDate = (EditText)findViewById(R.id.editDueDate);
			EditText editTask = (EditText)findViewById(R.id.editTask);
			CheckBox checkBoxDone = (CheckBox)findViewById(R.id.checkBoxDone);
			
			Task t = new Task();
			
			//Handle Date Data
			t.setPriority(checkPriority.isChecked());
			t.setDate(editDate.getText().toString());
			t.setTask(editTask.getText().toString());
			t.setCompleted(checkBoxDone.isChecked());
			
			Task newTask = toDoList.createTask(t);
			
			//Display the ID of the Task
			textID.setText(String.valueOf(newTask.getId()));
			//textID.setText("Test");
			
		}
		
		private void deleteTask()  {
			//Bind UI to fields
			TextView textID = (TextView)findViewById(R.id.textID);
			
			//check if box has data:
			if (textID.getText().toString().length()> 0) {
				int id = Integer.valueOf(textID.getText().toString());
				Task t = toDoList.getTask(id);
				toDoList.deleteTask(t);
				Intent intent = new Intent(this, MainActivity.class);
				startActivity(intent);
			}
		}
		

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.edit_task, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	
}
