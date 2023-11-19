import {useForm} from "react-hook-form";
import {yupResolver} from "@hookform/resolvers/yup";
import * as yup from "yup";
import "../styles/login.css"
import { useState } from "react";

export default function Admin(){

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
    function onSubmit(loginData){
        console.log(loginData);
        
        reset();
        
    }
    

    return(
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
    )
}