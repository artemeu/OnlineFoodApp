import axios from 'axios';

class OrderDataService {

    getAllOrders() {
        console.log('execute started');
        return axios.get("http://localhost:8034/order/all")
    }

    getOrderDetail(orderId) {
        console.log('execute started');
        return axios.get(`http://localhost:8034/order/${orderId}`);
    }

}

export default new OrderDataService();