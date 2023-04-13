import { Link } from "react-router-dom"
import './header.css'
const Header=()=>{
    return (
        <div id="header">
            <nav>
                <Link to='/' ><button id="header-link">Home</button></Link>
            
            </nav>
        </div>
    )
}
export default Header