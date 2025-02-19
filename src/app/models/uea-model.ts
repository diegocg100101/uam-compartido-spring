import { TrimestreModel } from "./trimestre-model";
import { TroncoModel } from "./tronco-model";
import { UnidadModel } from "./unidad-model";

export class UeaModel {
    clave : String = '';
    nombre : String = '';
    unidad : UnidadModel = new UnidadModel();
    creditos : number = 0;
    tronco : TroncoModel = new TroncoModel();
    trimestre : TrimestreModel = new TrimestreModel();
}
