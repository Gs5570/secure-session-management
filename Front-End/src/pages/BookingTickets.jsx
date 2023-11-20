import GuessPage from '../pages/GuessPage'

import { FaBus } from "react-icons/fa";
import { useState } from "react";
import NavBar from '../components/Navbar';
import busInfo from "../components/buses.json"

import "../styles/bookingTickets.css"

export default function BookingTickets(){
    const [busData, setBusData] = useState(busInfo.buses)
    return(
        <>
        <NavBar />
        <h1 className = "title"> Enjoy your trip</h1>
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
                            <button>Book</button>
                        </div>
                        </div>
                    </div>
                })}
            </div>

        </>
    )
}