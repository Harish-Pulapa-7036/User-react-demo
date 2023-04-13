import axios from 'axios';
import { useState, useEffect } from 'react';
import Popup from '../Popup-notify/popup';
import './addform.css'
import { useDispatch, useSelector } from 'react-redux';
import { addUser } from '../redux/action';


const Form = () => {
  const editUser = useSelector((state) => { return state.userData.user })
  const dispatch = useDispatch();
  const [user, setuser] = useState({});
  const [ispop, setpop] = useState(false);
  const [popdata, setpopdata] = useState('')
  const [err, seterr] = useState({})

  const handlesubmit = (e) => {

    e.preventDefault()
    if (!(err.username_err && err.username_er && err.email_err && err.email_er && err.mob_err && err.mob_er) && (user.user_name && user.email && user.mobile)) {
      let query
      if (e.target.value === "Update") {
        query = "Edit"
      }
      else query = "Insert"
      axios.post(`http://localhost:8081/crud/${query}`,
        JSON.stringify(user)
      )
        .then((res) => {
          console.log(res.data);
          dispatch(addUser(user))
          setpop(true)
          setpopdata(res.data)
          console.log(popdata);
        })
        .catch((e) => {
          console.log("hi");
          console.log(e);
          setpop(true)
          setpopdata(e)
        });
    }
  }
  useEffect(() => {
    if (editUser.sl) {
      setuser({ sl: editUser?.sl, user_name: editUser?.user_name, email: editUser?.email, mobile: editUser?.mobile })

    }
  }, [])
  const handleinput = (e) => {
    if (e.target.name === "user_name") {
      let username = e.target.value;
      let regex = new RegExp("^[A-Za-z0-9]+$");
      if (!(regex.test(username))) {
        seterr({ ...err, username_err: "UserName should contain letters and numbers" });
      }

      else {
        seterr({ ...err, username_err: "" });
        setuser({ ...user, user_name: username })
      }
    }
    if (e.target.name === "email") {
      let email = e.target.value;
      let regex = new RegExp('^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z]{2,4})+$');

      if (!(regex.test(email))) {
        seterr({ ...err, email_err: "Email is not valid" });
      }
      else {
        seterr({ ...err, email_err: "" });
        setuser({ ...user, email: email })
      }
    }
    if (e.target.name === "mobile") {
      let mobile = e.target.value;
      if (mobile.length > 10) {
        seterr({ ...err, mob_err: "Mobile number should not above 10 characters" });
      }
      else if (mobile.length < 10) {
        seterr({ ...err, mob_err: "Mobile number should not less than 10 characters" });
      }
      else if (mobile.length === 10) {
        seterr({ ...err, mob_err: "" });
        setuser({ ...user, mobile: mobile })
      }
    }
  }

  const handleempty = (e) => {
    if (e.target.name === "user_name") {
      let username = e.target.value;
      if (username === '') {
        seterr({ ...err, username_er: "UserName should not be empty" });
      }
      else {
        seterr({ ...err, username_er: "" });
      }
    }
    if (e.target.name === "email") {
      let email = e.target.value;
      if (email === '') {
        seterr({ ...err, email_er: "Email should not be empty" });
      }
      else {

        seterr({ ...err, email_er: "" });
      }
    }
    if (e.target.name === "mobile") {

      let mobile = e.target.value;
      if (mobile.length === 0) {
        seterr({ ...err, mob_er: "Mobile number should not be empty" });
      }

      else {
        seterr({ ...err, mob_er: "" });
      }
    }
  }
  return (
    <div id="contain">
      <form id="addform" >
        <div>
          <label>Serial Number</label> <br />
          <input type="text" defaultValue={editUser.sl} name="sl" id="sl" placeholder="Serial Number" readOnly />
        </div>
        <div>
          <label>User Name<span style={{ color: "red" }}>*</span></label> <br />
          <input
            defaultValue={editUser.user_name}
            name="user_name"
            id="user_name"
            placeholder="User Name"
            onInput={handleinput}
            onBlur={handleempty}

          />
          {err.username_err && <div style={{ color: "red", paddingBottom: 10, fontSize: "1rem" }}>{err.username_err}</div>}
          {err.username_er && <div style={{ color: "red", paddingBottom: 10, fontSize: "1rem" }}>{err.username_er}</div>}
        </div>
        <div>
          <label>Email<span style={{ color: "red" }}>*</span></label> <br />

          <input type="text" name="email" defaultValue={editUser.email} id="email" placeholder="Email" onInput={handleinput} onBlur={handleempty} />
          {err.email_err && <div style={{ color: "red", paddingBottom: 10, fontSize: "1rem" }}>{err.email_err}</div>}
          {err.email_er && <div style={{ color: "red", paddingBottom: 10, fontSize: "1rem" }}>{err.email_er}</div>}
        </div>
        <div>
          <label>Mobile <span style={{ color: "red" }}>*</span></label> <br />
          <input type="number" defaultValue={editUser.mobile} name="mobile" id="mobile" placeholder="Mobile" onInput={handleinput} onBlur={handleempty} />
          {err.mob_err && <div style={{ color: "red", paddingBottom: 10, fontSize: "1rem" }}>{err.mob_err}</div>}
          {err.mob_er && <div style={{ color: "red", paddingBottom: 10, fontSize: "1rem" }}>{err.mob_er}</div>}
        </div>

        <input type="submit" value={editUser.sl ? "Update" : "Add"} id="add" onClick={handlesubmit} />
      </form>
      {
        ispop && <Popup popdata={popdata} setpop={setpop} />
      }

    </div>
  )
}

export default Form;