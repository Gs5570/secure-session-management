import {useForm} from "react-hook-form";
import {yupResolver} from "@hookform/resolvers/yup";
import * as yup from "yup";
import "../styles/login.css"
import { useState } from "react";
import axios from "../api/Axios";
import AdminPage from "./AdminPage";

export default function Admin(){

    const [loggedAdmin, setLoggedAdmin] = useState({
        username: null,
        password: null,
      });
      const [successAdminLogin, setSuccessAdminLogin] = useState(false);
      console.log(successAdminLogin);

      //end point for the login int back-end ( the path )
    const loginURL = "/adminLogin";

    /**
     * code related to form submission 
    */

    //useFrom

    //input validation(schema validation)
    const registerSchema = yup.object().shape({
        username: yup.string()
            .required("Username is require"),
        password: yup.string()
            .min(5, 'Password is too short - should be 5 chars minimum.')
            .required("password is require")
            .matches(/[a-z]+/, "One lowercase character")
            .matches(/[A-Z]+/, "One uppercase character")
            .matches(/[@$!%*#?&]+/, "One special character")
            .matches(/\d+/, "One number"),
        
    })

    //useFrom
    const {
        register, 
        handleSubmit, 
        formState,
        reset,
    } =useForm({resolver:yupResolver(registerSchema)});

    const {errors} = formState;

    /**
     * Form submission
     * will automatically receive access to the form data through the parameter "loginData"*/ 
    const onSubmit = async (loginData) => {
        await setLoggedAdmin((prevState) => {
          return {
            ...prevState,
            username: loginData.username,
            password: loginData.password,
          };
        });
        console.log(loggedAdmin);
    
        if((loggedAdmin.password && loggedAdmin.username) != null){
            setSuccessAdminLogin(true)
        }
    
        //connect to back-end & submit form data to back-end
        try{
    
            //expected response from back end
            const response = await axios.post(
                loginURL,
                JSON.stringify({username: loggedAdmin.username, password: loggedAdmin.password}),
                {
                    Headers: { 'Content-Type': 'application/json'},
                    // withCredentials: true
                }
            )
    
            console.log(response);
    
            // response from the server saved  in the data property
            console.log(response.data)
            // full json object response
            console.log(JSON.stringify(response));
    
          console.log(response);
    
          // response from the server saved  in the data property
          console.log(response.data);
          // full json object response
          console.log(JSON.stringify(response));
    
          // clear input from registration fields.
          reset();
        } catch (err) {
          if (!err?.response) {
            console.log("No server response");
          }
          console.log(err);
        }
      };
    

    return(
        <>
            {successAdminLogin ? (<AdminPage />) : (
                <div>
            <form>
                <h1>Welcome</h1>
                <span><p>Admin Page</p></span>
                <section className="labels-container">
                    <label>
                        Username: <input 
                            type="text"
                            {...register("username")
                            }/>
                    </label>
                    <span className="error">{errors.username?.message}</span>
                    <label>
                        Password: <input 
                            type="password" 
                            {...register("password")
                            }/>
                    </label>
                    <span className="error">{errors.password?.message}</span>
                    
                </section>
                

                <div className="buttons-container">
                    <button type="button" onClick={handleSubmit(onSubmit)}>Log in</button>
                    
                </div>
                
            </form>
        </div>
            )}
        </>
    )
}