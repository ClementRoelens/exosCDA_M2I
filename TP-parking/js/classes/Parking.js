import { Session } from "./Session.js";
// import { Vehicule } from "./vehicule.js";

export class Parking {
    constructor() {
        this.sessions = [];
        this.pricesThresholds = [
            {
                threshold: 0,
                price: 0.8
            },
            {
                threshold: 15,
                price: 1.3
            },
            {
                threshold: 30,
                price: 1.7
            },
            {
                threshold: 45,
                price: 6
            }
        ];
        this.historic = [];
    }

    startSession(vehicule) {
        const vehiculesId = this.sessions.map(session => session.vehicule.id);
        if (!vehiculesId.includes(vehicule.id)){
            this.sessions.push(new Session(vehicule));
            return true;
        }
        return false;
    }

    endSession(id) {
        for (let i = 0; i < this.sessions.length; i++) {
            if (this.sessions[i].vehicule.id === id) {
                const splicedSession = this.sessions.splice(i, 1)[0];
                const duration = Date.now() - splicedSession.startSessionDateTime;
                this.historic.push({
                    id : splicedSession.vehicule.id,
                    startDate : new Date(splicedSession.startSessionDateTime),
                    endDate : new Date()
                });
                for (let j = 0; j < this.pricesThresholds.length - 1; j++) {
                    if (duration / 60000 < this.pricesThresholds[j + 1].threshold) {
                        return this.pricesThresholds[j].price;
                    }
                }
                return this.pricesThresholds[this.pricesThresholds.length - 1].price;
            }
        }
        return -1;
    }
}