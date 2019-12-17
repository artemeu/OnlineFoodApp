import React, { Component } from 'react';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import HeaderComponent from "./HeaderComponent";
import AuthenticatedRoute from '../AuthRoute/AuthenticatedRoute';
import ExactPathRoute from '../AuthRoute/ExactPathRoute';
import AdminAuthRoute from '../AuthRoute/AdminAuthRoute';
import CookAuthRoute from '../AuthRoute/CookAuthRoute';
import CourierAuthRoute from '../AuthRoute/CourierAuthRoute';
import Login from "./Login";
import MealList from "./MealList";
import FooterComponent from "./FooterComponent";
import LogoutComponent from "./LogoutComponent";
import ErrorComponent from "./ErrorComponent";
import MealComponent from "./MealComponent";
import OrderComponent from './OrderComponent';
import OrderDetailComponent from './OrderDetailComponent';
import CourierComponent from './CourierComponent';

class OnlineFoodApp extends Component {
    render() {
        return (
            <div className="onlineFoodApp">
                <Router>
                    <>
                        <HeaderComponent />
                        <Switch>
                            <ExactPathRoute path="/" exact component={MealList} />
                            <Route path="/login" component={Login} />
                            <AdminAuthRoute path="/meallist/:code" component={MealComponent} />
                            <AdminAuthRoute path="/meallist" component={MealList} />
                            <CookAuthRoute path="/orders" component={OrderComponent} />
                            <CookAuthRoute path="/details/:orderId" component={OrderDetailComponent} />
                            <CourierAuthRoute path="/courier" component={CourierComponent} />
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