import { RouteProp } from "@react-navigation/native";
import { StackNavigationProp } from "@react-navigation/stack";

export interface RootStackParamList {
    [key:string] : any;
    HomeScreen : undefined;
    DetailScreen:{
        contact : {
            firstname : string,
            lastname:string,
            phonenumber:string
        }
    };
}

export interface HomeNavigationProps extends StackNavigationProp<RootStackParamList,"HomeScreen">{}
export interface DetailNavigationProps extends StackNavigationProp<RootStackParamList,"DetailScreen">{}

export interface HomeRouteProps extends RouteProp<RootStackParamList,"HomeScreen">{}
export interface DetailRouteProps extends RouteProp<RootStackParamList,"DetailScreen">{}