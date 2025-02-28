import { AbstractControl, ValidationErrors, ValidatorFn } from '@angular/forms';

export function passwordMatchValidator(): ValidatorFn {
  return (control: AbstractControl): ValidationErrors | null => {
    const newPassword = control.get('newpassword');
    const confirmPassword = control.get('confirmpassword');

    return newPassword && confirmPassword && newPassword.value !== confirmPassword.value
      ? { 'passwordMismatch': true }
      : null;
  };
}