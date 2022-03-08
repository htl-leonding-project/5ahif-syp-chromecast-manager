import { Room } from "./room";
import { User } from "./User";
import { Device } from "./device";

export class InstallAt{
    constructor(
        public id: number,
        public installDate : Date,
        public description: string,
        public user : User,
        public room : Room,
        public device : Device 
    ){
    }
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