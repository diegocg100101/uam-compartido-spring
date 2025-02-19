import { DepartamentoModel } from "./departamento-model";
import { DivisionModel } from "./division-model";
import { RolModel } from "./rol-model";
import { UnidadModel } from "./unidad-model";

export class UserModel {
    email : String = '';
    password : String = '';
    noeconomico: String = '';
    nombre: String = '';
    apellidopaterno: String = '';
    apellidomaterno: String = '';
    rol : RolModel = new RolModel();
    unidad : UnidadModel = new UnidadModel();
    departamento : DepartamentoModel = new DepartamentoModel();
    division : DivisionModel = new DivisionModel();
}
