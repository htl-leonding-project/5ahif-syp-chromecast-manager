import { Room } from "./room";
import { User } from "./User";
import { Device } from "./device";

export interface InstallAt{
        id: number,
        installDate : Date,
        removeDate: Date,
        description: string,
        user : User,
        room : Room,
        device : Device 
}

export class InstallAtDto{
    constructor(
        public id: number,
        public deviceName: string,
        public deviceBrand: string,
        public installedFrom: string,
        public installDate: string
        ){            
    }
}

export interface InstallAtPostDto{
    id: number,
    installDate : string,
    removeDate: string,
    description: string,
    u_id : number,
    r_id : number,
    d_id : number            
}