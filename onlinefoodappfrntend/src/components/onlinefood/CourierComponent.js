import React, { Component } from 'react';
import CourierDataService from '../../api/CourierDataService';
import AuthenticationService from './AuthenticationService'
class CourierComponent extends Component {
    constructor() {
        super();
        this.state = {
            orders: []
        }
        AuthenticationService.setupAxiosInterceptorsForSavedToken();
    }

    deliver = (orderId) => {
        CourierDataService.deliver(orderId)
            .then(response => {
                this.refreshCourier();
            })
    }

    refreshCourier = () => {
        CourierDataService.getAll()
            .then(response => {
                this.setState({ orders: response.data });
                console.log(this.state.orders)
            })
    }

    componentDidMount() {
        this.refreshCourier();
        this.interval = setInterval(() => this.refreshCourier(), 5000);
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
                                {<th>Adı</th>}
                                {<th>Soyadı</th>}
                                {<th>Tutar</th>}
                                {<th>Adres</th>}
                            </tr>
                        </thead>
                        <tbody>
                            {
                                this.state.orders.map(
                                    order =>
                                        <tr key={order[0]}>
                                            <td>{(order[1])}</td>
                                            <td>{(order[2])}</td>
                                            <td>{(order[3])}.00 TL</td>
                                            <td>{(order[4])}</td>
                                            <td>
                                                <button className="btn btn-warning" onClick={() => this.deliver(order[0])}>Teslim Et</button>
                                            </td>
                                        </tr>
                                )
                            }
                        </tbody>
                    </table>
                    {this.state.orders.length === 0 && <div className="card-body">Beklemede Ürün Yok</div>}
                </div>
            </div>
        )
    }
}

export default CourierComponent;