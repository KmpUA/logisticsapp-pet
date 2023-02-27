export class Order {
    constructor(
        public id?: number,
        public cityFrom?: number,
        public cityTo?: number,
        public cargoDescription?: string,
        public cargoWeight?: number,
        public trucker?: number,
        public customer?: number,
        public created?: string,
        public modified?: string,
        public startDeliver?: string,
        public endDeliver?: string,
        public complited?: boolean
    ) { }
}
