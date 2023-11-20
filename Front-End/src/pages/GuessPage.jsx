import NavBar from "../components/Navbar"
import { FaBus } from "react-icons/fa";
import { useState } from "react";

import busInfo from "../components/buses.json"

import "../styles/guessPage.css"
export default function GuessPage(){

    const [busData, setBusData] = useState(busInfo.buses)
    console.log(busData);

    // console.log(busInfo);
    return(
        <>
            <NavBar />
            <h1 className="welcome"> Welcome to the Guess page</h1>
            
            <div>
                {busData.map((bus,index)=>{
                    return <div className = "bus-container" key = {index}>
                        <div className= "buss-elt-container">
                        <FaBus className="icon"/>
                        <div className="bus-info-container">
                            <p>Name: {bus.busName}</p>
                            <p>Destination: {bus.destination}</p>
                            <p>Date: {bus.date}</p>
                            <p>Status: {bus.status}</p>
                            
                        </div>
                        </div>
                    </div>
                })}
            </div>
        </>
    )
}