import React, { Component } from "react";
import "./TodoItem.css";
import { deleteTodoItem } from "../util/APIUtils";
import { addTodoItem } from "../util/APIUtils";
// import { getTodoItems } from '../util/APIUtils';
import TodoHeader from "./TodoHeader";
import TodoItemBody from "./ToDoItemBody";
import TodoForm from "./TodoForm";

var todoItems = [];
var todoListId = 0;
class TodoItem extends Component {
  constructor(props) {
    super(props);

    todoListId = 123;

    this.state = {
      todoItems: [],
      todoItem: {},
    };

    // .then(data => todoItems = data)
    // todoItems = this.state.todoItems; y
    todoItems = this.state.todoItems;
    //   this.state.todoItems.forEach(function (todoList) {
    //     todoItems.unshift({
    //       listId: todoListId,
    //       index: todoItems.length+1,
    //       id: todoList.id,
    //       value: todoList.name,
    //       done: todoList.completed
    //   });
    // });

    this.addItem = this.addItem.bind(this);
    this.removeItem = this.removeItem.bind(this);
    this.markTodoDone = this.markTodoDone.bind(this);
  }

  componentDidMount() {}

  componentWillUnmount() {}

  addItem(todoItemValue) {
    const addTodoItemRequest = Object.assign({}, this.state);
    addTodoItem(addTodoItemRequest, todoListId, todoItemValue.newItemValue)
      .then((data) => {
        todoItems.unshift({
          listId: todoListId,
          index: this.state.todoItems.length + 1,
          id: data.id,
          value: todoItemValue.newItemValue,
          done: false,
        });
        this.setState({ todoItems: todoItems });
      })
      .catch((item) => {
        todoItems.unshift({
          listId: todoListId,
          index: this.state.todoItems.length + 1,
          id: this.state.todoItems.length + 1,
          value: this.state.todoItems.length + 1,
          done: false,
        });
        this.setState({ todoItems: todoItems });
      });
    // const getTodoItemsRequest = Object.assign({}, this.state)
    // getTodoItems(getTodoItemsRequest, todoListId)
    // .then(data => todoItems: data)
  }
  removeItem(itemIndex) {
    var todo = todoItems[itemIndex];

    if (todo.id !== 0) {
      const deleteTodoItemRequest = Object.assign({}, this.state);
      deleteTodoItem(deleteTodoItemRequest, todo.listId, todo.id);
    }

    todoItems.splice(itemIndex, 1);
    this.setState({ todoItems: todoItems });
  }
  markTodoDone(itemIndex) {
    var todo = todoItems[itemIndex];
    todoItems.splice(itemIndex, 1);
    todo.done = !todo.done;
    todo.done ? todoItems.push(todo) : todoItems.unshift(todo);

    this.setState({ todoItems: todoItems });
  }
  render() {
    return (
      <div id="main">
        <TodoHeader />
        <TodoItemBody
          items={todoItems}
          removeItem={this.removeItem}
          markTodoDone={this.markTodoDone}
        />
        <TodoForm addItem={this.addItem} />
      </div>
    );
  }
}

export default TodoItem;
