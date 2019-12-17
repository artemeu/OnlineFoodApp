import React, { Component } from 'react';
import AuthenticationService from "../onlinefood/AuthenticationService";
import { Route, Redirect } from "react-router-dom";

class ExactPathRoute extends Component {
    render() {
        if (AuthenticationService.isUserLoggedIn() && AuthenticationService.isUserAdmin()) {
            return <Route {...this.props} />
        } else if (AuthenticationService.isUserLoggedIn() && AuthenticationService.isUserCook()) {
            return <Redirect to="/orders" />
        } else if (AuthenticationService.isUserLoggedIn() && AuthenticationService.isUserCourier()) {
            return <Redirect to="/courier" />
        } else {
            return <Redirect to="/login" />
        }
    }
}

export default ExactPathRoute;