import axios from 'axios'

class CartDataService {

    createCart(code) {
        console.log('execute started');
        return axios.post(`http://localhost:8034/cart/${code}`);
    };

    getAllCart() {
        console.log('execute started');
        return axios.get("http://localhost:8034/cart/all");
    }

    removeMeal(code) {
        console.log('execute started');
        return axios.delete(`http://localhost:8034/cart/${code}`);
    };

    sumbitCart() {
        console.log('execute started');
        return axios.post(`http://localhost:8034/cart/submit`);
    }

}

export default new CartDataService();