import { Component, OnInit } from '@angular/core';
import { Todo } from '../todo.model';
import { TodoService } from '../todo.service';
import {MatTableDataSource} from "@angular/material/table";
import { MatPaginator } from '@angular/material/paginator';
import { ViewChild } from '@angular/core';

@Component({
  selector: 'app-todo-list',
  templateUrl: './todo-list.component.html',
  styleUrls: ['./todo-list.component.css']
})
export class TodoListComponent implements OnInit {
  dataSource = new MatTableDataSource<Todo>([]);
  @ViewChild(MatPaginator) paginator!: MatPaginator;
  newTodo: Todo = new Todo();

  constructor(private todoService: TodoService) { }

  ngOnInit(): void {
    this.loadTodos();
  }

  loadTodos(): void {
    this.todoService.getTodos().subscribe(data => {
      this.dataSource.data = data;
      this.dataSource.paginator = this.paginator;
    });
  }

  addTodo(): void {
    this.todoService.addTodo(this.newTodo).subscribe(data => {
      const currentData = this.dataSource.data;
      this.dataSource.data = [...currentData, data];
      this.newTodo = new Todo();
    });
  }

  deleteTodo(id: number | undefined): void {
    if (id !== undefined) {
      this.todoService.deleteTodo(id).subscribe(() => {
        const filteredData = this.dataSource.data.filter(todo => todo.id !== id);
        this.dataSource.data = filteredData;
      });
    }
  }

}


