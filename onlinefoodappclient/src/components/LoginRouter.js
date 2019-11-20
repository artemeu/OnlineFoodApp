import React, { Component } from 'react';
import AuthenticationService from '../Authentication/services/AuthenticationService';
import { Route, Redirect } from 'react-router-dom';

class LoginRouter extends Component {
    render() {
        if (AuthenticationService.isUserLoggedIn()) {
            return <Redirect to="/" />
        }
        else {
            return <Route{...this.props} />
        }
    }
}

export default LoginRouter;