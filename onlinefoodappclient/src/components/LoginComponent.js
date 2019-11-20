import React, { Component } from 'react';
import AuthenticationService from '../Authentication/services/AuthenticationService';


class LoginComponent extends Component {
    constructor() {
        super();
        this.state = {
            username: 'artemeu',
            password: '123',
            isLoggedIn: null
        };
    }
    render() {
        return (
            <div className="limiter">
                <div className="container-login100">
                    <div className="wrap-login100 p-l-55 p-r-55 p-t-35 p-b-50">
                        <div className="login100-form">

                            <span className="login100-form-title p-b-33">
                                Kullanıcı Girişi
				        	</span>
                            {this.state.isLoggedIn != null && !this.state.isLoggedIn && <span className="alert alert-warning mg-top">Kullanıcı Adı veya Şifre Hatalı</span>}
                            <div className="wrap-input100">
                                <input className="input100" type="text" name="username" placeholder="Kullanıcı Adı" value={this.state.username} onChange={this.handleChange} />
                                <span className="focus-input100-1"></span>
                                <span className="focus-input100-2"></span>
                            </div>

                            <div className="wrap-input100">
                                <input className="input100" type="text" name="password" placeholder="Şifre" value={this.state.password} onChange={this.handleChange} />
                                <span className="focus-input100-1"></span>
                                <span className="focus-input100-2"></span>
                            </div>

                            <div className="container-login100-form-btn m-t-20">
                                <button className="login100-form-btn" onClick={this.loginClicked}>Giriş Yap</button>
                            </div>

                        </div>

                    </div>
                </div>
            </div>
        )
    }

    handleChange = (event) => {
        this.setState({ [event.target.name]: event.target.value })
    }

    loginClicked = () => {
        if (this.state.username === "artemeu" && this.state.password === "123") {
            this.props.history.push(`/meals`);
            AuthenticationService.registerSuccessfullLogin(this.state.username, this.state.password);
        }
        else {
            this.setState({ isLoggedIn: false })
        }
    }

    logoutClicked = () => {
        AuthenticationService.logOut();
    }

}

export default LoginComponent;