class AuthenticationService {
    registerSuccessfullLogin(username, password) {
        localStorage.setItem("authenticatedUser", username);
    }

    logOut() {
        localStorage.removeItem("authenticatedUser");
    }

    isUserLoggedIn() {
        let user = localStorage.getItem("authenticatedUser");
        if (user == null) return false;
        return true;
    }
}

export default new AuthenticationService();