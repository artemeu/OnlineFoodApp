import React, { Component } from 'react';
import CartDataService from '../api/CartDataService';
import AuthenticationService from '../Authentication/services/AuthenticationService';

class ShoppingCartComponent extends Component {
    constructor(props) {
        super(props);
        this.state = {
            meals: [],
            totalPrice: null
        }
        AuthenticationService.setupAxiosInterceptorsForSavedToken();
    }

    menuyeDon = () => {
        this.props.history.push("/meals")

    }

    componentDidMount() {
        this.refreshMeals();
    };

    refreshMeals = () => {
        CartDataService.getAllCart()
            .then(response => {
                console.log(response.data)
                this.setState({ meals: response.data });
            })
    };

    remove = (code) => {
        CartDataService.removeMeal(code)
            .then(response => {
                this.refreshMeals();
            })
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

    submitCart = (meal) => {
        CartDataService.sumbitCart(meal)
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

                                            <div className="col-4 col-sm-4 col-md-4">
                                                <div className="quantity">
                                                    <input type="button" value="+" className="plus" onClick={() => this.increment(meal.code)} />
                                                    <input type="text" max="99" min="1" value="1" id={meal.code} className="qty"
                                                        size="4" readOnly />
                                                    <input type="button" value="-" className="minus" onClick={() => this.decrement(meal.code)} />
                                                </div>
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
                            <button className="btn btn-success pull-right" onClick={() => this.submitCart(this.state.meals)}>Onayla</button>
                            <div className="pull-right" style={{ margin: "5px" }}>
                                Toplam: <b>{this.state.totalPrice}</b>
                            </div>
                        </div>
                    </div>}


                </div>
            </div>
        )
    }
}

export default ShoppingCartComponent;