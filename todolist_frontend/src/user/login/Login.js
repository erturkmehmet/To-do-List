import React, { Component } from "react";
import "./Login.css";
import { ACCESS_TOKEN } from "../../constants";
import { USER_ID } from "../../constants";
import { login } from "../../util/APIUtils";
import { Link, Redirect } from "react-router-dom";
import Alert from "react-s-alert";

class Login extends Component {
  componentDidMount() {
    if (this.props.location.state && this.props.location.state.error) {
      setTimeout(() => {
        Alert.error(this.props.location.state.error, {
          timeout: 5000,
        });
        this.props.history.replace({
          pathname: this.props.location.pathname,
          state: {},
        });
      }, 100);
    }
  }

  updateState() {
    //this.setState({ shown: false });
  }

  render() {
    if (this.props.authenticated) {
      return (
        <Redirect
          to={{
            pathname: "/",
            state: { from: this.props.location },
          }}
        />
      );
    }

    return (
      <div className="login-container">
        <div className="login-content">
          <h1 className="login-title">Login</h1>

          <LoginForm {...this.props} />
          <span className="signup-link">
            New user? <Link to="/signup">Sign up!</Link>
          </span>
        </div>
      </div>
    );
  }
}

class LoginForm extends Component {
  constructor(props) {
    super(props);
    this.state = {
      email: "",
      password: "",
      fireRedirect: false,
    };
    this.handleInputChange = this.handleInputChange.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
  }

  handleInputChange(event) {
    const target = event.target;
    const inputName = target.name;
    const inputValue = target.value;

    this.setState({
      [inputName]: inputValue,
    });
  }

  handleSubmit(event) {
    debugger;
    event.preventDefault();

    const loginRequest = Object.assign({}, this.state);

    login(loginRequest)
      .then((response) => {        
        localStorage.setItem(USER_ID, response.userId);
        Alert.success("You're successfully logged in!");
        this.setState({ fireRedirect: true });
        window.location.reload();
      })
      .catch((error) => {
        login(loginRequest)
      .then((response) => {  
        //Alert.error((error && error.message) || 'Oops! Something went wrong. Please try again!');
        localStorage.setItem(USER_ID, response.userId);
        Alert.success("You're successfully logged in!");
        this.setState({ fireRedirect: true });
        window.location.reload();
      })
      });
  }

  render() {
    const { fireRedirect } = this.state;
    return (
      <div>
        <form onSubmit={this.handleSubmit}>
          <div className="form-item">
            <input
              type="email"
              name="email"
              className="form-control"
              placeholder="Email"
              value={this.state.email}
              onChange={this.handleInputChange}
              required
            />
          </div>
          <div className="form-item">
            <input
              type="password"
              name="password"
              className="form-control"
              placeholder="Password"
              value={this.state.password}
              onChange={this.handleInputChange}
              required
            />
          </div>
          <div className="form-item">
            <button type="submit" className="btn btn-block btn-primary">
              Login
            </button>
          </div>
        </form>
        {fireRedirect && <Redirect to={"/"} />}
      </div>
    );
  }
}

export default Login;
