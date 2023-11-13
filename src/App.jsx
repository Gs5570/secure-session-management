import { useState } from 'react'
import { Route,Routes } from 'react-router-dom'


//pages
import Login from './pages/Login'
import Register from './pages/register'
import Admin from './pages/Admin'


import './App.css'

function App() {
 

  return (
    <>
     <Routes>
      <Route path="/" element={ <Login /> }/>
      <Route path="/register" element ={ <Register /> }/>
      <Route path="/adminPage" element ={ <Admin /> }/>
     </Routes> 
    </>
  )
}

export default App
