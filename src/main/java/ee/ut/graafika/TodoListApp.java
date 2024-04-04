package ee.ut.graafika;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TodoListApp extends Application {
	
	private List<String> todos = new ArrayList<>();
	private ListView<String> todoListView;
	
	@Override
	public void start(Stage primaryStage) {
		loadTodosFromFile(); // Load todos from file when app starts
		
		TextField todoInput = new TextField();
		todoInput.setPromptText("Enter a new todo");
		Button addButton = new Button("Add");
		addButton.setOnAction(e -> {
			String newTodo = todoInput.getText();
			todos.add(newTodo);
			saveTodosToFile();
			todoInput.clear();
			refreshTodoListView();
		});
		
		todoListView = new ListView<>();
		refreshTodoListView();
		
		Button editButton = new Button("Edit");
		editButton.setOnAction(e -> {
			String selectedTodo = todoListView.getSelectionModel().getSelectedItem();
			if (selectedTodo != null) {
				String editedTodo = showEditDialog(selectedTodo);
				if (editedTodo != null) {
					todos.set(todoListView.getSelectionModel().getSelectedIndex(), editedTodo);
					saveTodosToFile();
					refreshTodoListView();
				}
			}
		});
		
		VBox root = new VBox(10);
		root.getChildren().addAll(todoInput, addButton, todoListView, editButton);
		
		primaryStage.setScene(new Scene(root, 300, 250));
		primaryStage.setTitle("Todo List App");
		primaryStage.show();
	}
	
	private void refreshTodoListView() {
		todoListView.getItems().clear();
		todoListView.getItems().addAll(todos);
	}
	
	private String showEditDialog(String todo) {
		// You can implement a dialog box here to allow editing todos
		// For simplicity, I'll just return the todo as it is
		return todo;
	}
	
	private void loadTodosFromFile() {
		try (BufferedReader reader = new BufferedReader(new FileReader("todos.txt"))) {
			String line;
			while ((line = reader.readLine()) != null) {
				todos.add(line);
			}
		} catch (IOException e) {
			// Handle file IO exception
			e.printStackTrace();
		}
	}
	
	private void saveTodosToFile() {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter("todos.txt"))) {
			for (String todo : todos) {
				writer.write(todo);
				writer.newLine();
			}
		} catch (IOException e) {
			// Handle file IO exception
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
