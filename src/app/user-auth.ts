export class UserAuth {
    email : String;
    password : String = '';

    constructor() {
        this.email = '';
        this.password = '';
    }

    get getEmail() {
        return this.email;
    }

    get getPassword() {
        return this.password;
    }

    set setEmail(email: String) {
        this.email = email;
    }   

    set setPassword(password: String) {
        this.password = password;
    }
}
