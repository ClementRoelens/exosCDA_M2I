export class Session {
    #_startSessionDateTime

    constructor(vehicule) {
        this.vehicule = vehicule;
        this.#_startSessionDateTime = Date.now();
    }

   get startSessionDateTime() {
    return this.#_startSessionDateTime;
   }

   set startSessionDateTime(value){
    this.#_startSessionDateTime = value;
   }
}