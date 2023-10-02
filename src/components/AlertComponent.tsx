import success from '../assets/checked_icon.png';
import danger from '../assets/danger_icon.png';
import info from '../assets/info_icon.png';

import AlertStyles from './AlertComponent.module.css';

const Alert = (prop: AlertProp) => {
    let iconPath: string;
    let color: string;
    let text: string = prop.text ? prop.text : "Un problème est survenu";

    switch (prop.icon) {
        case "danger":
            iconPath = danger;
            break;
        case "info":
            iconPath = info;
            break;
        case "success":
            iconPath = success;
            break;
        default:
            iconPath = danger;
    }

    switch (prop.color) {
        case "danger":
            color = AlertStyles.danger;
            break;
        case "info":
            color = AlertStyles.info;
            break;
        case "success":
            color = AlertStyles.success;
            break;
        default:
            color = AlertStyles.danger;
    }

    return (
        <p className={`${color} ${AlertStyles.container}`}>
            <img className={AlertStyles.icon} src={iconPath} alt="Icone" />
            <span>{text}</span>
        </p>
    );
}

interface AlertProp {
    color?: string,
    text?: string,
    icon?: string
}

export default Alert;
