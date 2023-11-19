import React from 'react'
import ReactDOM from 'react-dom/client'
import App from './App.jsx'
import './index.css'
import {BrowserRouter, Routes, Route} from "react-router-dom"; 
import { AuthProvider } from './context/AuthPorvider.jsx';
import 'bootstrap/dist/css/bootstrap.min.css'

ReactDOM.createRoot(document.getElementById('root')).render(
  
    <BrowserRouter>
      <AuthProvider>
        <Routes>
          <Route path = "/*" element = {<App />}/>
        </Routes>
      </AuthProvider>
    </BrowserRouter>
    
)
