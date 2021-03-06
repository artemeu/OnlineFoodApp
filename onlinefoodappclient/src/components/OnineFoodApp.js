import React, { Component } from 'react';
import { BrowserRouter as Router, Switch, Route } from 'react-router-dom'
import HeaderComonent from './HeaderComponent';
import LoginComponent from './LoginComponent';
import FooterComponent from './FooterComponent';
import MealListComponent from './MealListComponent';
import AuthenticatedRoute from '../Authentication/AuthenticatedRoute';
import LoginRouter from './LoginRouter';
import ErrorComponent from './ErrorComponent';
import ShoppingCartComponent from './ShoppingCartComponent';
import OrderComponent from './OrderComponent';
import OrderDetailComponent from './OrderDetailComponent';

class OnlineFoodApp extends Component {
    render() {
        return (
            <div className="onlineFoodApp">
                <Router>
                    <>
                        <HeaderComonent />
                        <Switch>
                            <Route path="/" exact component={MealListComponent} />
                            <Route path="/meals" component={MealListComponent} />
                            <AuthenticatedRoute path="/shoppingcart" component={ShoppingCartComponent} />
                            <AuthenticatedRoute path="/orders" component={OrderComponent} />
                            <AuthenticatedRoute path="/details/:orderId" component={OrderDetailComponent} />
                            <LoginRouter path="/login" component={LoginComponent} />
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