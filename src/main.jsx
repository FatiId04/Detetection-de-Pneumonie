import React from 'react';
import ReactDOM from 'react-dom/client';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import Log from './Log';
import Home1 from './Home1';
import Hist from './Hist';
import './index.css';


ReactDOM.createRoot(document.getElementById('root')).render(
  <React.StrictMode>
  <Router>
    <Routes>
     <Route path="/" element={<Log/>}/>
     <Route path="/Home1" element={<Home1/>}/>
     <Route path="/Hist" element={<Hist/>}/>

    </Routes>
  </Router>
  </React.StrictMode>
);
