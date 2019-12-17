import React, { Component } from 'react';
import OrderDataService from '../../api/OrderDataService';
import moment from "moment";
import AuthenticationService from './AuthenticationService'
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
        this.interval = setInterval(() => this.refreshOrders(), 5000);
    }

    componentWillUnmount() {
        clearInterval(this.interval);
    }

    render() {
        return (
            <div>
                <div className="container">

                    <table className="table">
                        <thead>
                            <tr>
                                {<th>Sipariş Id</th>}
                                {<th>Sipariş Tarihi</th>}
                                {<th>Sipariş Durum/İçeriği</th>}
                            </tr>
                        </thead>
                        <tbody>
                            {
                                this.state.orders.map(
                                    order =>
                                        <tr key={order.orderId}>
                                            <td className="tBold">{order.orderId} </td>
                                            <td>{moment(order.placementDate).format('DD-MM-YYYY')}</td>
                                            {/* <td>{order.price.toString()} TL</td> */}
                                            {/* {order.status === null && <td>Hazırlanıyor</td>}
                                            {order.status === true && <td>Teslimat Aşamasında</td>}
                                            {order.status === false && <td>Teslim Edildi</td>} */}
                                            <td>

                                                {order.status === null && <button className="btn btn-warning" onClick={() => this.getOrderDetail(order.orderId)}>Beklemede</button>}
                                                {order.status === true && <button className="btn btn-info" onClick={() => this.getOrderDetail(order.orderId)}>Teslımat Aşamasında</button>}
                                                {order.status === false && <button className="btn btn-success" onClick={() => this.getOrderDetail(order.orderId)}>Teslım Edildi</button>}

                                            </td>
                                        </tr>
                                )
                            }
                        </tbody>
                    </table>
                    {this.state.orders.length === 0 && <div className="card-body">Hiç Sipariş Yok</div>}
                </div>
            </div>
        )
    }
}

export default OrderComponent;