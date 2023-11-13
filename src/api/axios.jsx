/**
 * Allow us to set the url of the back end application so we do not have to retype it.
 */

import axios from "axios";
export default axios.create({
    baseURL: '' // address of backend API
})