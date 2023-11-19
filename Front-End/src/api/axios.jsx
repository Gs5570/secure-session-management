/**
 * Allow us to set the url of the back end application so we do not have to retype it.
 */

import axios from "axios";
export default axios.create({
    baseURL: 'http://localhost:8080/' // address of backend API
})