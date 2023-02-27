import { Trucker } from "./trucker";
import { User} from "./user"

export class OrderResponse {
    constructor(
        public id?: number,
        public cityFrom?: number,
        public cityTo?: number,
        public cargoDescription?: string,
        public cargoWeight?: number,
        public trucker?: Trucker | null | number,
        public customer?: User | null | number,
        public created?: string,
        public modified?: string,
        public startDeliver?: string,
        public endDeliver?: string,
        public complited?: boolean
    ) { }
}
