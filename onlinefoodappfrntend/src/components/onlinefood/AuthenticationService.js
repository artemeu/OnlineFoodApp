import axios from 'axios';
class AuthenticationService {

    executeJwtAuthentication(username, password) {
        return axios.post('http://localhost:8034/admin/authenticate', { username, password });
    };

    registerSuccessfullLoginJwt(username, token, remember, authority) {
        if (remember == true) {
            authority.forEach(element => {
                localStorage.setItem(element.authority, element.authority)
            });
            localStorage.setItem('authenticatedUser', username);
            localStorage.setItem('token', token);
            this.setupAxiosInterceptorsJwt(token);
        }
        else {
            authority.forEach(element => {
                sessionStorage.setItem(element.authority, element.authority)
            });
            sessionStorage.setItem('authenticatedUser', username);
            localStorage.setItem('token', token);
            this.setupAxiosInterceptorsJwt(token);
        }

    }

    logout() {
        let local = localStorage.getItem('authenticatedUser');
        if (local != null) {
            localStorage.removeItem('token');
            localStorage.removeItem('Admin');
            localStorage.removeItem('Cook');
            localStorage.removeItem('Courier');
            localStorage.removeItem('authenticatedUser');
        } else {
            localStorage.removeItem('token');
            sessionStorage.removeItem('authenticatedUser');
            sessionStorage.removeItem('Admin');
            sessionStorage.removeItem('Cook');
            sessionStorage.removeItem('Courier');
        }
    }

    isUserLoggedIn() {
        let session = sessionStorage.getItem('authenticatedUser');
        let local = localStorage.getItem('authenticatedUser');
        if (session != null) {
            return true
        }
        else if (local != null) {
            return true;
        }
        else {
            return false;
        }
    }

    isUserCook() {
        let sessionCook = sessionStorage.getItem('Cook');
        let localCook = localStorage.getItem('Cook');
        if (sessionCook != null) {
            return true
        }
        else if (localCook != null) {
            return true;
        }
        else {
            return false;
        }
    }

    isUserCourier() {
        let sessionCook = sessionStorage.getItem('Courier');
        let localCook = localStorage.getItem('Courier');
        if (sessionCook != null) {
            return true
        }
        else if (localCook != null) {
            return true;
        }
        else {
            return false;
        }
    }

    isUserAdmin() {
        let sessionAdmin = sessionStorage.getItem('Admin');
        let localAdmin = localStorage.getItem('Admin');
        if (sessionAdmin != null) {
            return true
        }
        else if (localAdmin != null) {
            return true;
        }
        else {
            return false;
        }
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

export default new AuthenticationService()