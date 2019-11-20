import React, { Component } from 'react';
import ShoppingCartComponent from './ShoppingCartComponent';
import ShoppingCartService from '../services/ShoppingCartService';
// import PropTypes from "prop-types";

class MealListComponent extends Component {
    constructor() {
        super();
        this.state = {
            meals:
                [
                    { code: 'MNT1', name: 'Mantı', price: 12, photo: 'https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcTb1kYeg_hLCRfdDJY8993QPPf_j0BxIqOLF-x1XXOTXiuz1-nz', detail: 'Süper bi yemek!' },
                    { code: 'CRB1', name: 'Çorba', price: 6, photo: 'https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcQksEHmneA7QLNELK_VC1-uRED9i7YJWj-Tjq2vBlMglsrX0pHY', detail: 'Süper bi yemek!' },
                    { code: 'PD1', name: 'Pide', price: 19, photo: 'https://iasbh.tmgrup.com.tr/111ad1/650/344/0/12/700/383?u=http://i.sabah.com.tr/sbh/2016/12/21/1482317898915.jpg', detail: 'Süper bi yemek!' }
                ]
        }
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
                                                    <span className="input-group-btn">
                                                        <button type="button" className="btn btn-light btn-number" onClick={() => this.decrement(meal.code)}>-</button>
                                                    </span>
                                                    <input type="text" className="center mli3" id={meal.code} value="1" readOnly />
                                                    <span className="input-group-btn">
                                                        <button className="btn btn-light btn-number" onClick={() => this.increment(meal.code)}>+</button>
                                                    </span>
                                                    <button className="btn btn-success ml20" onClick={() => this.order(meal.code, document.getElementById(meal.code).value)}>Sipariş Ver</button>
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

    order = (code, value) => {
        console.log(code, value)
        this.resetValue(code);
    }

    resetValue = (code) => {
        document.getElementById(code).value = 1;
    }

}


// MealListComponent.defaultProps = {
//     by: 1
// };

// MealListComponent.propTypes = {
//     by: PropTypes.number
// };

export default MealListComponent;;