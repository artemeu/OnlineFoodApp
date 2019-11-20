import React, {Component} from 'react';
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
                {/*User Name : <input type="text" name="username" value={this.state.username} onChange={this.handleUsernameChange}/>
                Password : <input type="text" name="password" value={this.state.password} onChange={this.handlePasswordChange}/>*/}
                {/*<div>Kullanıcı veya şifre hatalı!</div>
                <div>Başarılı giriş!</div>*/}
                {/*<LoginMessage isLoggedIn={this.state.isLoggedIn}/>*/}
                {/*///////////////////////////////////////////////////////*/}
                {/*{this.state.isLoggedIn && <div>Başarılı giriş!</div>}
                {this.state.isLoggedIn != null && !this.state.isLoggedIn && <div>Kullanıcı veya şifre hatalı!</div>}
                User Name : <input type="text" name="username" value={this.state.username}
                                   onChange={this.handleChange}/>
                Password : <input type="text" name="password" value={this.state.password} onChange={this.handleChange}/>
                <button onClick={this.loginClicked}>Login</button>*/}

                <h1>Login</h1>
                <div className="container">
                    {this.state.isLoggedIn && <div>Başarılı giriş!</div>}
                    {this.state.isLoggedIn != null && !this.state.isLoggedIn &&
                    <div className="alert alert-warning">Kullanıcı veya şifre hatalı!</div>}
                    User Name : <input type="text" name="username" value={this.state.username}
                                       onChange={this.handleChange}/>
                    Password : <input type="text" name="password" value={this.state.password}
                                      onChange={this.handleChange}/>
                    <button className="btn btn-success" onClick={this.loginClicked}>Login</button>
                </div>
            </div>
        )
    }

    handleChange = (event) => {
        //console.log(this.state);
        this.setState({[event.target.name]: event.target.value});
    };

    /*handleUsernameChange = (event) => {
        this.setState({username: event.target.value});
    };

    handlePasswordChange = (event) => {
        this.setState({password: event.target.value});
    };*/

    loginClicked = (event) => {
        /*if (this.state.username === 'mesutcan' && this.state.password === '123') {
            console.log("SUCCESSFULL");
            this.props.history.push(`/welcome/${this.state.username}`);
            //this.setState({isLoggedIn : true});
            AuthenticationService.registerSuccessfullLogin(this.state.username, this.state.password);

        } else {
            this.setState({isLoggedIn: false});
            console.log("FAILED");
        }*/

        /*AuthenticationService.executeBasicAuthentication(this.state.username, this.state.password)
            .then(response=>{
                this.props.history.push(`/welcome/${this.state.username}`);
                AuthenticationService.registerSuccessfullLogin(this.state.username, this.state.password);
            })
            .catch(error=> {
                this.setState({isLoggedIn: false});
                console.log("FAILED");
        })*/

        AuthenticationService.executeJwtAuthentication(this.state.username, this.state.password)
            .then(response=>{
                AuthenticationService.registerSuccessfullLoginJwt(this.state.username, response.data.token);
                this.props.history.push(`/welcome/${this.state.username}`);
            })
            .catch(error=> {
                this.setState({isLoggedIn: false});
                console.log("FAILED");
            })
    }
}

/*function LoginMessage(props){
    if(props.isLoggedIn===null)
        return null;
    else if(props.isLoggedIn)
        return <div>Başarılı giriş!</div>;
    else if(!props.isLoggedIn)
        return <div>Kullanıcı veya şifre hatalı!</div>;
}*/

export default Login;