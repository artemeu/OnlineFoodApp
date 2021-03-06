import React, { Component } from 'react';
import CartDataService from '../api/CartDataService';
import AuthenticationService from '../Authentication/services/AuthenticationService';

class ShoppingCartComponent extends Component {
    constructor(props) {
        super(props);
        this.state = {
            meals: [],
            totalPrice: 0
        }
        AuthenticationService.setupAxiosInterceptorsForSavedToken();
    }

    menuyeDon = () => {
        this.props.history.push("/meals")

    }

    totalPrice = (meals) => {
        let price = 0;
        Object.entries(meals).map(([make, result]) => {
            price = price + result.price
        })
        this.setState({ totalPrice: price });
    }

    componentDidMount() {
        this.refreshMeals();
    };

    refreshMeals = () => {
        CartDataService.getAllCart()
            .then(response => {
                console.log(response.data)
                this.setState({ meals: response.data });
                if (response.data.length != 0)
                    this.totalPrice(response.data)
            })
    };

    remove = (code) => {
        CartDataService.removeMeal(code)
            .then(response => {
                this.refreshMeals();
            })
    }

    submitCart = () => {
        CartDataService.sumbitCart()
            .then(response => {
                this.refreshMeals();
            });
        this.setState({ message: `Siparis Oluşturuldu` });
        setTimeout(() => this.setState({ message: null }), 3000);
    };



    render() {
        return (
            <div className="container">
                <div className="card shopping-cart">
                    {this.state.message != null && <div className="alert alert-success">{this.state.message}</div>}
                    <div className="card-header bg-dark text-light">
                        <div className="float-l">Sepetim</div>
                        <button className="btn btn-outline-light btn-sm float-right" style={{ alignItems: "right" }} onClick={this.menuyeDon} >Menüye Dön</button>
                        <div className="clearfix"></div>
                    </div>

                    {this.state.meals.length === 0 && <div className="card-body">Urun yok</div>}

                    {
                        this.state.meals.length != 0 &&
                        this.state.meals.map(
                            meal =>
                                <div className="card-body" key={meal.code}>
                                    <div className="row">
                                        <div className="col-12 col-sm-12 col-md-2 text-center">
                                            <img className="img-responsive" src={meal.photo} alt="prewiew" width="120" height="80" />
                                        </div>
                                        <div className="col-12 text-sm-center col-sm-12 text-md-left col-md-6">
                                            <h5 className="product-name"><strong>{meal.name}</strong></h5>
                                            <h4>
                                                <small>{meal.detail}</small>
                                            </h4>
                                        </div>
                                        <div className="col-12 col-sm-12 text-sm-center col-md-4 text-md-right row">
                                            <div className="col-3 col-sm-3 col-md-6 text-md-right" style={{ paddingTop: "10px" }}>
                                                <h6><strong>{meal.price}.00 <span className="text-muted">TL</span></strong></h6>
                                            </div>
                                            <div className="col-2 col-sm-2 col-md-2 text-right">
                                                <button className="btn btn-danger" onClick={() => this.remove(meal.code)}>Sil</button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                        )
                    }

                    {this.state.meals.length != 0 && <div className="card-footer">
                        <div className="pull-right" style={{ margin: "10px" }}>
                            <button className="btn btn-success pull-right" onClick={this.submitCart}>Onayla</button>
                            <div className="pull-right" style={{ margin: "5px" }}>
                                Toplam: <b>{this.state.totalPrice}.00 TL</b>
                            </div>
                        </div>
                    </div>}

                </div>
            </div>
        )
    }
}

export default ShoppingCartComponent;