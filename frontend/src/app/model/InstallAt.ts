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

export class InstallAtPostDto{
    constructor(
        public installDate : string,
        public removeDate: string,
        public description: string,
        public u_id : string,
        public r_id : string,
        public d_id : string
    ){

    }            
}