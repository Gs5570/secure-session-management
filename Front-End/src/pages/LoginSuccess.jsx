import { Link } from "react-router-dom"

export default function LoginSuccess(){
    return(
    <div>
        <section>
            <h1>you are logged in successfully</h1>

            <ol>
                <p>Go to:</p>
                <li> <Link to ="/bookingTickets" > Ticket booking page </Link></li>
                <li> <Link to ="/adminPage" > Admin Page </Link></li>
            </ol>
        </section>
    </div>)
}