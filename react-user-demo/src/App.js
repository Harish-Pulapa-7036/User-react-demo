import logo from "./logo.svg";
import "./App.css";

import { BrowserRouter, Navigate, Route, Routes } from 'react-router-dom'
import ViewTable from "./Componenets/viewpage/viewtable";
import Home from "./Componenets/Home/Home";
import Header from "./Componenets/Home/Header";

function App() {

  return (
    <BrowserRouter>
      <Header/>
      <Routes>
        <Route path='/' element={<Home />} />
        <Route path='/view' element={<ViewTable />} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;
