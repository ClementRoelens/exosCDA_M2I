import { SIGN_IN_URL, SIGN_UP_URL } from "../firebaseConfig";

export const signUp = async (credentials : {email:string, password:string}) => {
    try {
        const response = await fetch(SIGN_UP_URL, {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(credentials)
        });

        if (!response.ok) {
            throw new Error("Erreur lors de l'inscription");
        }
        const data = await response.json();
        console.log(data);

        return { success: true, res: data };
    }

    catch (error: any) {
        console.error(error.message)

        return { success: false, res: error.message };
    }
};

export const signIn = async (credentials: { email: string, password: string }): Promise<{ success: boolean, res: string }> => {
    try {
        const response = await fetch(SIGN_IN_URL, {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(credentials)
        });

        if (!response.ok) {
            throw new Error("Erreur lors de l'authentification");
        }
        const data = await response.json();
        console.log(data);

        return { success: true, res: data.idToken };
    }

    catch (error: any) {
        console.error(error.message)

        return { success: false, res: error.message };
    }
};