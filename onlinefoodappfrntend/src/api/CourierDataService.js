import axios from 'axios';

class CourierDataService {

    getAll() {
        console.log('execute started');
        return axios.get("http://localhost:8034/courier/all")
    }

    deliver(orderId) {
        console.log('execute started');
        return axios.post(`http://localhost:8034/courier/${orderId}`)
    }

}

export default new CourierDataService();