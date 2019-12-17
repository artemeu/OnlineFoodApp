import React, { Component } from 'react';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import HeaderComponent from "./HeaderComponent";
import AuthenticatedRoute from "./AuthenticatedRoute";
import Login from "./Login";
import MealList from "./MealList";
import FooterComponent from "./FooterComponent";
import LogoutComponent from "./LogoutComponent";
import ErrorComponent from "./ErrorComponent";
import WelcomeComponent from "./WelcomeComponent";
import MealComponent from "./MealComponent";
import OrderComponent from './OrderComponent';
import OrderDetailComponent from './OrderDetailComponent';
import CourierComponent from './CourierComponent';
import LoginRouter from './LoginRouter';

class OnlineFoodApp extends Component {
    render() {
        return (
            <div className="onlineFoodApp">
                <Router>
                    <>
                        <HeaderComponent />
                        <Switch>
                            <AuthenticatedRoute path="/" exact component={MealList} />
                            <LoginRouter path="/login" component={Login} />
                            <AuthenticatedRoute path="/welcome/:name" component={WelcomeComponent} />
                            <AuthenticatedRoute path="/meallist/:code" component={MealComponent} />
                            <AuthenticatedRoute path="/meallist" component={MealList} />
                            <AuthenticatedRoute path="/orders" component={OrderComponent} />
                            <AuthenticatedRoute path="/details/:orderId" component={OrderDetailComponent} />
                            <AuthenticatedRoute path="/courier" component={CourierComponent} />
                            <AuthenticatedRoute path="/logout" component={LogoutComponent} />
                            <Route component={ErrorComponent} />
                        </Switch>
                        <FooterComponent />
                    </>
                </Router>
            </div>
        )
    }
}

export default OnlineFoodApp;