import { SalonModel } from "./salon-model";
import { UeaModel } from "./uea-model";
import { UnidadModel } from "./unidad-model";
import { UserModel } from "./user-model";

export class GrupoModel {
    clavegrupo : String = '';
    uea : UeaModel = new UeaModel();
    unidad : UnidadModel = new UnidadModel();
    horario : String = '';
    profesor : UserModel = new UserModel();
    cupounidad : number = 0;
    salon : SalonModel = new SalonModel();
    inscritos : number = 0; 
}
