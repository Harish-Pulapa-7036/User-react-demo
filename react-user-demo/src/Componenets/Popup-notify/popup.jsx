import { useEffect } from 'react';
import './popup.css'
const Popup = ({ popdata, setpop }) => {
    useEffect(() => {
        setTimeout(() => {
            setpop(false)
            document.location.reload(false);
        }, 1500);
    })

    return (
        <div id="popup-contain">

            {popdata}
            <div>
                <img src="https://raw.githubusercontent.com/Harish-Pulapa-7036/CONTACTS-MANAGER-APP/contact-manager/frontend/src/Components/TableNav/images/importComp.png" alt="Tick mark" width='20%' />
            </div>

        </div>
    )
}
export default Popup;