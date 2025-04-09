import { AbstractControl, FormGroup } from '@angular/forms';


export class FormUtilidades{
  static namePattern = '([a-zA-Z]+) ([a-zA-Z]+)';
  static emailPattern = '^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$';
  static notOnlySpacesPattern = '^[a-zA-Z0-9]+$';
  static passwordPattern='/^(?=.*[A-Z])(?=.*\d)(?=.*[!@#$%^&*()_+])[A-Za-z0-9!@#$%^&*()_+]{8,}$/'  //debe tener minimo una mayuscula un numero y un caracter y minimo de 8 digitos

 static igualPassword(field1:string,field2:string){

    return (formGroup:AbstractControl) => {
      const field1value=formGroup.get(field1)?.value;
      const field2value=formGroup.get(field2)?.value;
      return field1value=== field2value ?null : {passwordnoequal:true}

    }

  }
  static isValidField(form:FormGroup,fieldname:string): Boolean|null{
    return (form.controls[fieldname].errors &&
      form.controls[fieldname].touched);

  }

  static getFieldErros(form:FormGroup,fieldname:string):string | null{

    if(!form.controls[fieldname]) return null;

    const errors=form.controls[fieldname].errors ?? { }

    for (const key of Object.keys(errors)) {

      switch(key){
          case 'required':
            return "Este campo es requerido";
            case 'minlength':
            return `Minimo de ${errors['minlength'].requiredLength} caracteres`
            case 'min':
            return `Valor minimo de ${errors['min'].min}`
             case 'pattern':
                const patternError = errors['pattern'];
                // Control de patrón de nombre
                if (patternError && patternError.requiredPattern === this.namePattern) {
                  return "Nombre y Apellido incorrectos";
                }
                // Control de patrón de correo electrónico
                if (patternError && patternError.requiredPattern === this.emailPattern) {
                  return "El correo es incorrecto";
                }
                // Control de patrón de solo caracteres alfanuméricos
                if (patternError && patternError.requiredPattern === this.notOnlySpacesPattern) {
                  return "No se admiten espacios";
                }
                // Control de patrón de contraseña
                if (patternError && patternError.requiredPattern === this.passwordPattern) {
                  return "La contraseña debe contener al menos una mayúscula, un número y un carácter especial.";
                }
            break

              default:
                return `Error de validacion no controlado ${key}`;
      }


    }
      return null;
  }

}
