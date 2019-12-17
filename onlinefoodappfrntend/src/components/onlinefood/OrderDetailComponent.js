import React, { Component } from 'react';
import OrderDataService from '../../api/OrderDataService';
import AuthenticationService from './AuthenticationService';

class OrderDetailComponent extends Component {
    constructor(props) {
        super(props);
        this.state = {
            meals: [],
            flag: null
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

    getOrderStatus() {
        OrderDataService.getOrderStatus(this.props.match.params.orderId)
            .then(response => {
                if (response.data === false) {
                    this.setState({ btnName: `Teslimata Yönlendir` });
                    this.setState({ className: `btn btn-warning` });
                } else {
                    this.setState({ btnName: `Sipariş Hazır` });
                    this.setState({ className: `btn btn-success` });
                }
                this.setState({ flag: response.data })
            })
    }

    componentDidMount() {
        this.getOrderDetail();
        this.getOrderStatus();
    }

    return = () => {
        this.props.history.push('/orders');
    }

    approve = (orderId) => {
        OrderDataService.approve(orderId)
            .then(response => {
                this.getOrderStatus();
            })
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
                                <td><button className="btn btn-success ml20" onClick={this.return}>Geri Dön</button> </td>
                                <td><button className={this.state.className} onClick={() => this.approve(this.props.match.params.orderId)}>{this.state.btnName}</button></td>
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