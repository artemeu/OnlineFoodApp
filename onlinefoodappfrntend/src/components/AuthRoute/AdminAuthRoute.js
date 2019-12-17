import React, { Component } from 'react';
import AuthenticationService from "../onlinefood/AuthenticationService";
import { Route, Redirect } from "react-router-dom";

class AdminAuthRoute extends Component {
    render() {
        if (AuthenticationService.isUserLoggedIn() && AuthenticationService.isUserAdmin()) {
            return <Route {...this.props} />
        } else {
            return <Redirect to="/login" />
        }
    }
}

export default AdminAuthRoute;