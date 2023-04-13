import { useEffect, useState } from "react";
import axios from "axios";
import "./viewtable.css";
import { Link, useNavigate } from "react-router-dom";
import Popup from "../Popup-notify/popup";

import { useDispatch, useSelector } from "react-redux";
import { DeleteUser, editViewData, getUsers } from "../redux/action";

const ViewTable = () => {
  const user_data = useSelector((state) => { return state.userData.users })
  const dispatch = useDispatch();
  const navigate=useNavigate() 
  const [ispop, setpop] = useState(false);
  const [popdata, setpopdata] = useState('')

  useEffect(() => {
    console.log("hiii");

    
    axios
      .get("http://localhost:8081/crud/Viewpage")
      .then((res) => {
        dispatch(getUsers(res.data))
      })
      .catch((e) => console.log(e));
  }, []);

  const edit = (id) => {
    axios
      .get("http://localhost:8081/crud/Editview?sl=" + id)
      .then((res) => {
        dispatch(editViewData(res.data))
        navigate('/')

      })
      .catch((e) => console.log(e));
  }
  const del = (id) => {

    axios
      .get("http://localhost:8081/crud/Delete?sl=" + id)
      .then((res) => {
        dispatch(DeleteUser())
        
        setpop(true)
        setpopdata(res.data)
      })
      .catch((e) => {
        console.log(e);
        setpop(true)
        setpopdata(e.message)
      });
  }
  return (
    <div>
      <table id="user">
        <thead>
          <tr>
            <th>Serial Number</th>
            <th>UserName</th>
            <th>Email</th>
            <th>Mobile</th>
            <th>Edit</th>
            <th>Delete</th>
          </tr>
        </thead>
        <tbody>
          {user_data?.map((item, i) => {
            return (
              <tr key={i}>
                <td style={{ textAlign: "center" }}>{item.sl}</td>
                <td>{item.user_name}</td>
                <td>{item.email}</td>
                <td>{item.mobile}</td>
                <td style={{ textAlign: "center" }}>
                  <button id={item.sl} onClick={(e) => { edit(e.target.id) }}>Edit</button>
                </td>
                <td style={{ textAlign: "center" }}>
                  <button id={item.sl} onClick={(e) => { del(e.target.id) }}>Delete</button>
                </td>
              </tr>
            );
          })}
        </tbody>
      </table>
      <Link to='/' style={{ textDecoration: "none" }}>Home</Link>
      {
        ispop && <Popup popdata={popdata} setpop={setpop} />
      }
    </div>
  );
};

export default ViewTable;
