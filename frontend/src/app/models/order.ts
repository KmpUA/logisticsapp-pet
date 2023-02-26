export class Order {
    constructor(
        public cityFrom?: string,
        public cityTo?: string,
        public cargoDescription?: string,
        public cargoWeight?: number,
        public trucker?: number,
        public created?: string,
        public modified?: string,
        public startDeliver?: string,
        public id?: number,
        public endDeliver?: string
    ) { }
}