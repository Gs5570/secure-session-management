import { useContext } from "react";
import AuthContext from "../context/AuthPorvider";

const useAuth = () => {
    return useContext(AuthContext)
}

export default useAuth;