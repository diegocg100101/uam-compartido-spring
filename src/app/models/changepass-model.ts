export class ChangePass {
    email;
    oldpassword;
    newpassword;
    confirmpassword;

    constructor(email : string | null, oldpassword: string | null, newpassword: string | null, confirmpassword: string | null) {
        this.email = email;
        this.oldpassword = oldpassword;
        this.newpassword = newpassword;
        this.confirmpassword = confirmpassword;
    }
}