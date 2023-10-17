// Import the functions you need from the SDKs you need
import { initializeApp } from "firebase/app";
import { getAnalytics } from "firebase/analytics";
// TODO: Add SDKs for Firebase products that you want to use
// https://firebase.google.com/docs/web/setup#available-libraries

// Your web app's Firebase configuration
// For Firebase JS SDK v7.20.0 and later, measurementId is optional
const firebaseConfig = {
  apiKey: "AIzaSyAJy8Zw2MmENRA_2v8c5eeqQ8vuTCi9nYc",
  authDomain: "react-redux-9b323.firebaseapp.com",
  databaseURL: "https://react-redux-9b323-default-rtdb.europe-west1.firebasedatabase.app",
  projectId: "react-redux-9b323",
  storageBucket: "react-redux-9b323.appspot.com",
  messagingSenderId: "145336086474",
  appId: "1:145336086474:web:642594e39fdc69a876a933",
  measurementId: "G-S9M5LSX1WP"
};


export const BASE_DB_URL = firebaseConfig.databaseURL;
export const SIGN_UP_URL = "https://identitytoolkit.googleapis.com/v1/accounts:signUp?key="+firebaseConfig.apiKey;
export const SIGN_IN_URL = "https://identitytoolkit.googleapis.com/v1/accounts:signInWithPassword?key="+firebaseConfig.apiKey;

// Initialize Firebase
const app = initializeApp(firebaseConfig);
const analytics = getAnalytics(app);