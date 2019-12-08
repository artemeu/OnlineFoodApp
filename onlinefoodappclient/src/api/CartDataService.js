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

    sumbitCart(meal) {
        console.log('execute started');
        return axios.post(`http://localhost:8034/cart/submit`, meal);
    }

}

export default new CartDataService();