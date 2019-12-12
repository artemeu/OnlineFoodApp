import React, { Component } from 'react';
import OrderDataService from '../api/OrderDataService';
import moment from "moment";
import AuthenticationService from '../Authentication/services/AuthenticationService';

class OrderComponent extends Component {
    constructor() {
        super();
        this.state = {
            orders: []
        }
        AuthenticationService.setupAxiosInterceptorsForSavedToken();
    }

    getOrderDetail = (orderId) => {
        this.props.history.push(`/details/${orderId}`);
    }

    refreshOrders = () => {
        OrderDataService.getAllOrders()
            .then(response => {
                console.log(response.data)
                this.setState({ orders: response.data });
            })
    }

    componentDidMount() {
        this.refreshOrders();
    }

    render() {
        return (
            <div>
                <div className="container">
                    <table className="table">
                        <thead>
                            <tr>
                                <th>Sipariş Id</th>
                                <th>Sipariş Tarihi</th>
                                <th>Sipariş Tutarı</th>
                                <th>Sipariş Durumu</th>
                                <th>Sipariş İçeriği</th>
                            </tr>
                        </thead>
                        <tbody>
                            {
                                this.state.orders.map(
                                    order =>
                                        <tr key={order.orderId}>
                                            <td className="tBold">{order.orderId} </td>
                                            <td>{moment(order.placementDate).format('DD-MM-YYYY')}</td>
                                            <td>{order.price.toString()} TL</td>
                                            {order.status === null && <td>Hazırlanıyor</td>}
                                            {order.status === true && <td>Teslimat Aşamasında</td>}
                                            {order.status === false && <td>Teslim Edildi</td>}
                                            <td>
                                                <div className="input-grp">
                                                    <button className="btn btn-success ml20" onClick={() => this.getOrderDetail(order.orderId)}>Detay</button>
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

export default OrderComponent;