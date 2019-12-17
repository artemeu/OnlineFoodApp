import React, { Component } from 'react';
import AuthenticationService from "./AuthenticationService";

class Login extends Component {
    constructor() {
        super();
        this.state = {
            username: "mesutcan",
            password: "123",
            isLoggedIn: null
        };
    }

    render() {
        return (
            <div className="login">
                <h1>Login</h1>
                <div className="container">
                    {this.state.isLoggedIn && <div>Başarılı giriş!</div>}
                    {this.state.isLoggedIn != null && !this.state.isLoggedIn &&
                        <div className="alert alert-warning">Kullanıcı veya şifre hatalı!</div>}
                    User Name : <input type="text" name="username" value={this.state.username}
                        onChange={this.handleChange} />
                    Password : <input type="text" name="password" value={this.state.password}
                        onChange={this.handleChange} />
                    <button className="btn btn-success" onClick={this.loginClicked}>Login</button><br />
                    <label className="check">
                        <input type="checkbox" id="check" />
                        <span className="checkmark"></span>
                        Beni Hatırla
                            </label>
                </div>
            </div>
        )
    }

    handleChange = (event) => {
        this.setState({ [event.target.name]: event.target.value });
    };

    loginClicked = (event) => {
        var remember = document.getElementById("check").checked;
        AuthenticationService.executeJwtAuthentication(this.state.username, this.state.password)
            .then(response => {
                AuthenticationService.registerSuccessfullLoginJwt(this.state.username, response.data.token, remember);
                this.props.history.push(`/meallist`);
            })
            .catch(error => {
                this.setState({ isLoggedIn: false });
                console.log("FAILED");
            })
    }
}

export default Login;