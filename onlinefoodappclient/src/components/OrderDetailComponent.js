import React, { Component } from 'react';
import OrderDataService from '../api/OrderDataService';
import AuthenticationService from '../Authentication/services/AuthenticationService';

class OrderDetailComponent extends Component {
    constructor(props) {
        super(props);
        this.state = {
            meals: []
        }
        AuthenticationService.setupAxiosInterceptorsForSavedToken();
    }

    getOrderDetail() {
        OrderDataService.getOrderDetail(this.props.match.params.orderId)
            .then(response => {
                this.setState({ meals: response.data });
                console.log(this.state.meals)
            });
    };

    componentDidMount() {
        this.getOrderDetail();
    }

    return = () => {
        this.props.history.push('/orders');
    }

    render() {
        return (
            <div>
                {this.state.meals.length === 0 && <div className="card-body">Sayfa Bulunamadi</div>}

                <div className="container">
                    <table className="table">
                        <thead>
                            <tr>
                                <th></th>
                                <th></th>
                            </tr>
                        </thead>
                        <tbody>
                            {
                                this.state.meals.map(
                                    meal =>
                                        <tr key={meal.code}>
                                            <td className="tBold">{meal.name} </td>
                                            {/* <td>{meal.detail}</td> */}
                                            <td>{meal.price.toString()} TL</td>
                                        </tr>
                                )
                            }
                            <tr>
                                <td> </td>
                                <td> <button className="btn btn-success ml20" onClick={this.return}>Geri Dön</button></td>
                            </tr>
                        </tbody>
                    </table>
                    {/* <div className="input-grp">
                        <button className="btn btn-success ml20">Geri Dön</button>
                    </div> */}
                </div>
            </div>
        )
    }
}

export default OrderDetailComponent;