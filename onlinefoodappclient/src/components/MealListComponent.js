import React, { Component } from 'react';
import MealDataService from "../api/MealDataService";
import CartDataService from '../api/CartDataService';
import AuthenticationService from '../Authentication/services/AuthenticationService';

class MealListComponent extends Component {
    constructor(props) {
        super(props);
        this.state = {
            meals: [],
            message: null
        }
        AuthenticationService.setupAxiosInterceptorsForSavedToken();
    }

    componentDidMount() {
        this.refreshMeals();
    };

    refreshMeals = () => {
        MealDataService.retrieveAllMeals()
            .then(response => {
                this.setState({ meals: response.data });
            })
    };

    increment = (code) => {
        let val = document.getElementById(code).value
        if (val != 10)
            document.getElementById(code).value++
    }

    decrement = (code) => {
        let val = document.getElementById(code).value
        if (val != 1)
            document.getElementById(code).value--
    }

    order = (code) => {
        if (AuthenticationService.isUserLoggedIn()) {

            CartDataService.createCart(code)
                .then(response => {
                    this.setState({ message: `Sepete Eklendi` });
                    setTimeout(() => this.setState({ message: null }), 500);
                })

        } else {
            this.props.history.push('/login');
        }

    }

    resetValue = (code) => {
        document.getElementById(code).value = 1;
    }

    render() {
        return (
            <div>
                {this.state.message != null && <div className="alert alert-success">{this.state.message}</div>}
                <div className="container">
                    <table className="table">
                        <thead>
                            <tr>
                                <th></th>
                                <th></th>
                                <th></th>
                                <th></th>
                                <th></th>
                            </tr>
                        </thead>
                        <tbody>
                            {
                                this.state.meals.map(
                                    meal =>
                                        <tr key={meal.code}>
                                            <td><img alt="" className="pic" src={meal.photo} /></td>
                                            <td className="tBold">{meal.name} </td>
                                            <td>{meal.detail}</td>
                                            <td>{meal.price.toString()} TL</td>
                                            <td>
                                                <div className="input-grp">
                                                    {/* <span className="input-group-btn">
                                                        <button type="button" className="btn btn-light btn-number" onClick={() => this.decrement(meal.code)}>-</button>
                                                    </span>
                                                    <input type="text" className="center mli3" id={meal.code} value="1" readOnly />
                                                    <span className="input-group-btn">
                                                        <button className="btn btn-light btn-number" onClick={() => this.increment(meal.code)}>+</button>
                                                    </span> */}
                                                    <button className="btn btn-success ml20" onClick={() => this.order(meal.code)}>Sipariş Ver</button>
                                                </div>
                                            </td>
                                        </tr>
                                )
                            }
                        </tbody>
                    </table>
                </div>
            </div>
        )
    }
}

export default MealListComponent;;