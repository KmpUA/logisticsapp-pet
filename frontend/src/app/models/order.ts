export class Order {
    constructor(
        public cityFrom?: string,
        public cityTo?: string,
        public cargoDescription?: string,
        public cargoSize?: string,
        public cargoWeight?: number,
        public trucker?: string,
        public created?: string,
        public modified?: string,
        public startDeliver?: string,
        public id?: number,
        public endDeliver?: string
    ) { }
}
