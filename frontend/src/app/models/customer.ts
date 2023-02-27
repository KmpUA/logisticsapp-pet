import { OrderResponse } from "./order-response";

export class Customer {
    constructor(
        public id?: number,
        public firstName?: string,
        public lastName?: string,
        public email?: string,
        public phone?: string,
        public status?: string,
        public role?: string,
        public orders?: OrderResponse[] | null
    ) { }
}
