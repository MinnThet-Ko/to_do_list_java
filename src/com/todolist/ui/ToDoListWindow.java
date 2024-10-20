package com.todolist.ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class ToDoListWindow extends JFrame {

	private static final long serialVersionUID = 9140779320708086835L;

	private String[] columnName = { "Task List" };

	private List<String> taskList;
	private JPanel inputPanel;
	private JLabel inputLabel;
	private JTextField inputTextField;
	private JButton inputButton;

	private JScrollPane scrollPane;
	private JTable taskListTable;
	private DefaultTableModel tableModel;

	private JPanel buttonPanel;
	private JButton deleteButton;
	private JButton clearButton;

	public ToDoListWindow() {

		this.taskList = new ArrayList<String>();

		this.setLocation(10, 10);
		this.setSize(400, 400);
		this.setLayout(new BorderLayout());
		initializeInputPanel();
		initializeTable();
		initializeButtonPanel();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);

	}

	public void initializeInputPanel() {

		this.inputPanel = new JPanel();
		this.inputPanel.setLayout(new BorderLayout());
		this.inputPanel.setSize(300, 50);
		this.inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		this.inputLabel = new JLabel("Task:");
		this.inputTextField = new JTextField(20);
		this.inputButton = new JButton("Create");

		this.inputPanel.add(this.inputLabel, BorderLayout.WEST);
		this.inputPanel.add(this.inputTextField, BorderLayout.CENTER);
		this.inputPanel.add(this.inputButton, BorderLayout.EAST);

		this.add(this.inputPanel, BorderLayout.NORTH);
		addInputButtonHandler();

	}

	public void initializeTable() {

		this.taskListTable = new JTable();
		this.tableModel = new DefaultTableModel(initializeTaskData(), this.columnName);
		this.taskListTable.setModel(tableModel);

		this.scrollPane = new JScrollPane(this.taskListTable);
		this.add(this.scrollPane, BorderLayout.CENTER);
	}

	public String[][] initializeTaskData() {
		System.out.println("Task list size: " + this.taskList.size());
		String[][] taskArray = new String[this.taskList.size()][this.columnName.length];
		for (int i = 0; i < this.taskList.size(); i++) {
			taskArray[0][i] = taskList.get(i);
		}
		System.out.println(taskArray.toString());
		return taskArray;
	}

	public void initializeButtonPanel() {
		this.buttonPanel = new JPanel(new BorderLayout());
		this.deleteButton = new JButton("Delete");
		this.clearButton = new JButton("Clear");

		this.buttonPanel.add(this.deleteButton, BorderLayout.WEST);
		this.buttonPanel.add(this.clearButton, BorderLayout.EAST);
		this.add(this.buttonPanel, BorderLayout.SOUTH);

		addDeleteButtonHandler();
		addClearButtonHandler();
	}

	public void addInputButtonHandler() {
		this.inputButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("In addInputButtonHandler");
				String inputTask = inputTextField.getText();
				List<String> inputTaskAsList = new ArrayList<String>();
				inputTaskAsList.add(inputTextField.getText());
				if (inputTask.isEmpty()) {
					JOptionPane.showMessageDialog(ToDoListWindow.this, "The input is empty.");
				} else {
					tableModel.addRow(inputTaskAsList.toArray());
					tableModel.fireTableDataChanged();
				}

			}
		});
	}

	public void addDeleteButtonHandler() {
		this.deleteButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Delete Clicked");
				int selectedRow = taskListTable.getSelectedRow();
				tableModel.removeRow(selectedRow);
				tableModel.fireTableDataChanged();
			}
		});
	}

	public void addClearButtonHandler() {
		this.clearButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (tableModel.getRowCount() > 0) {
					for (int i = tableModel.getRowCount() - 1; i > -1; i--) {
						tableModel.removeRow(i);
					}
				}

			}
		});
	}
}
