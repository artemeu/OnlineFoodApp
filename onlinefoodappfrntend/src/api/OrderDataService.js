import axios from 'axios';

class OrderDataService {

    getAllOrders() {
        console.log('execute started');
        return axios.get("http://localhost:8034/order/findAll")
    }

    getOrderDetail(orderId) {
        console.log('execute started');
        return axios.get(`http://localhost:8034/order/${orderId}`);
    }

    getOrderStatus(orderId) {
        console.log('execute started');
        return axios.get(`http://localhost:8034/order/status/${orderId}`);
    }

    approve(orderId) {
        return axios.post(`http://localhost:8034/order/approve/${orderId}`);
    }

}

export default new OrderDataService();