import React, { Component } from "react";

class TodoItemListItem extends Component {
  constructor(props) {
    super(props);
    this.onClickClose = this.onClickClose.bind(this);
    this.onClickDone = this.onClickDone.bind(this);
  }
  onClickClose() {
    var index = parseInt(this.props.index, 10);
    this.props.removeItem(index);
  }
  onClickDone() {
    var index = parseInt(this.props.index, 10);
    this.props.markTodoDone(index);
  }
  render() {
    var todoClass = this.props.item.done ? "done" : "undone";
    var iconClass = this.props.item.done
      ? "glyphicon glyphicon-ok icon"
      : "glyphicon glyphicon-minus icon";
    return (
      <li className="list-group-item ">
        <div className={todoClass}>
          <span
            className={iconClass}
            aria-hidden="true"
            onClick={this.onClickDone}
          ></span>
          {this.props.item.value}
          <button type="button" className="close" onClick={this.onClickClose}>
            &times;
          </button>
        </div>
      </li>
    );
  }
}

export default TodoItemListItem;
