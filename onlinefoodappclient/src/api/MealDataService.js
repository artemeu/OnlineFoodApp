import axios from 'axios';

class MealDataService {
    retrieveAllMeals() {
        console.log('execute started');
        return axios.get('http://localhost:8034/meals/all');
    };
}

export default new MealDataService();