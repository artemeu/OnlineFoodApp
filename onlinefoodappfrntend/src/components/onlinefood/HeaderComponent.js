import React, { Component } from 'react';
import AuthenticationService from "./AuthenticationService";
import { Link } from "react-router-dom";
import { withRouter } from 'react-router';

class HeaderComponent extends Component {
    render() {
        const isUserLoggedIn = AuthenticationService.isUserLoggedIn();
        const isUserAdmin = AuthenticationService.isUserAdmin();
        const isUserCook = AuthenticationService.isUserCook();
        const isUserCourier = AuthenticationService.isUserCourier();

        return (
            <header>
                <nav className="navbar navbar-expand-md navbar-dark bg-dark">
                    <div><a href="http://localhost:5000" className="navbar-brand">Online Food App</a></div>
                    <ul className="navbar-nav">
                        {isUserLoggedIn && isUserAdmin && <li><Link className="nav-link" to="/meallist">Meals</Link></li>}
                        {isUserLoggedIn && !isUserCourier && <li><Link className="nav-link" to="/orders">Orders</Link></li>}
                        {isUserLoggedIn && !isUserCook && < li > <Link className="nav-link" to="/courier">Courier</Link></li>}
                    </ul>
                    <ul className="navbar-nav navbar-collapse justify-content-end">
                        {!isUserLoggedIn && <li><Link className="nav-link" to="/login">Login</Link></li>}
                        {isUserLoggedIn && <li><Link className="nav-link" to="/logout" onClick={AuthenticationService.logout}>Logout</Link></li>}
                    </ul>
                </nav>
            </header >
        );
    }
}

export default withRouter(HeaderComponent);