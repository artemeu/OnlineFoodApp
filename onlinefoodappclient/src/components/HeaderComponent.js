import React, { Component } from 'react';
import { Link, withRouter } from 'react-router-dom'
import AuthenticateService from '../Authentication/services/AuthenticationService';

class HeaderComonent extends Component {
    render() {

        const isUserLoggedIn = AuthenticateService.isUserLoggedIn();

        return (
            <header>
                <nav className="navbar navbar-expand-md navbar-dark bg-dark">
                    <div><a href="http://localhost:5500" className="navbar-brand">Online Food App</a></div>

                    <ul className="navbar-nav">
                        <li><Link className="nav-link font-family-size" to="/meals">Yemekler</Link></li>
                        {isUserLoggedIn && <li><Link className="nav-link font-family-size" to="/shoppingcart">Sepetim</Link></li>}
                    </ul>

                    <ul className="navbar-nav navbar-collapse justify-content-end">
                        {!isUserLoggedIn && <li><Link className="nav-link font-family-size" to="/login" >Giriş Yap</Link></li>}
                        {isUserLoggedIn && <li><Link className="nav-link font-family-size" to="/" onClick={AuthenticateService.logOut}>Çıkış</Link></li>}
                    </ul>
                </nav>
            </header>
        )
    }
}

export default withRouter(HeaderComonent);