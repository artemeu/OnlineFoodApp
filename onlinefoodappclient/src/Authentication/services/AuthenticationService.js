import axios from 'axios';

class AuthenticationService {

    executeJwtAuthentication(username, password) {
        return axios.post('http://localhost:8034/customer/authenticate', { username, password });
    };

    registerSuccessfullLoginJwt(username, token) {
        sessionStorage.setItem('authenticatedUser', username);
        localStorage.setItem('token', token);
        this.setupAxiosInterceptorsJwt(token);
    }

    logOut() {
        localStorage.removeItem('token');
        sessionStorage.removeItem('authenticatedUser');
    }

    isUserLoggedIn() {
        let user = sessionStorage.getItem('authenticatedUser');
        if (user === null) return false;
        return true;
    }

    setupAxiosInterceptors(username, password) {
        axios.interceptors.request.use((config) => {
            if (this.isUserLoggedIn())
                config.headers.authorization = this.createBasicAuthentication(username, password);
            return config;
        })
    }

    setupAxiosInterceptorsJwt = (token) => {
        axios.interceptors.request.use((config) => {
            if (this.isUserLoggedIn())
                config.headers.authorization = this.createJwtAuthentication(token);
            return config;
        })
    }

    setupAxiosInterceptorsForSavedToken() {
        if (this.isUserLoggedIn())
            this.setupAxiosInterceptorsJwt(localStorage.getItem('token'));
    }

    createJwtAuthentication(token) {
        return 'Bearer ' + token;
    };
}

export default new AuthenticationService();