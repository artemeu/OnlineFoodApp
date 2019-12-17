import axios from 'axios';
class AuthenticationService {

    executeJwtAuthentication(username, password) {
        return axios.post('http://localhost:8034/admin/authenticate', { username, password });
    };

    registerSuccessfullLoginJwt(username, token, remember) {
        if (remember == true) {
            localStorage.setItem('authenticatedUser', username);
            localStorage.setItem('token', token);
            this.setupAxiosInterceptorsJwt(token);
        }
        else {
            sessionStorage.setItem('authenticatedUser', username);
            localStorage.setItem('token', token);
            this.setupAxiosInterceptorsJwt(token);
        }

    }

    logout() {
        let local = localStorage.getItem('authenticatedUser');
        if (local != null) {
            localStorage.removeItem('token');
            localStorage.removeItem('authenticatedUser');
        } else {
            localStorage.removeItem('token');
            sessionStorage.removeItem('authenticatedUser');
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