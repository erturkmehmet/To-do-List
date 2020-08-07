import React, { Component } from "react";
import TodoItemListItem from "./TodoItemListItem";

class TodoItemBody extends Component {
  render() {
    var items = this.props.items.map((item, index) => {
      return (
        <TodoItemListItem
          key={index}
          item={item}
          index={index}
          removeItem={this.props.removeItem}
          markTodoDone={this.props.markTodoDone}
        />
      );
    });
    return <ul className="list-group"> {items} </ul>;
  }
}

export default TodoItemBody;
