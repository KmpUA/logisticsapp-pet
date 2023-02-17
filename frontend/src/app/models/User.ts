export class User {
    constructor(
        public id?: number,
        public firstName?: string,
        public lastName?: string,
        public email?: string,
        public phone?: string,
        public created?: string,
        public modified?: string,
        public status?: string,
        public role?: string,
    ) { }
}