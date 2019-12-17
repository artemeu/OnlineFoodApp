import React, { Component } from 'react';
import AuthenticationService from "../onlinefood/AuthenticationService";
import { Route, Redirect } from "react-router-dom";

class CourierAuthRoute extends Component {
    render() {
        if (AuthenticationService.isUserLoggedIn() && AuthenticationService.isUserCourier() || AuthenticationService.isUserAdmin()) {
            return <Route {...this.props} />
        } else {
            return <Redirect to="/login" />
        }
    }
}

export default CourierAuthRoute;