package net.project.springboot.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "task")
public class Task {

  @Id
  @Column(name = "task_id")
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int taskId;
  @Column(name = "task_title", length = 50)
  private String taskTitle;
  @Column(name = "task_description", length = 50)
  private String taskDescription;
  @Column(name = "due_date")
  private Date dueDate;

  public Task(String taskTitle, String taskDescription, Date dueDate) {
    this.taskTitle = taskTitle;
    this.taskDescription = taskDescription;
    this.dueDate = dueDate;
  }
}
