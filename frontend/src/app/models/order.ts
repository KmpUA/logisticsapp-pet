import { User } from "./user";

export class Order {
    constructor(
        public cityFrom?: string,
        public cityTo?: string,
        public cargoDescription?: string,
        public cargoWeight?: number,
        public trucker?: User | number,
        public customer?: User | number,
        public created?: string,
        public completed?: boolean,
        public modified?: string,
        public startDeliver?: string,
        public id?: number,
        public endDeliver?: string
    ) { }
}