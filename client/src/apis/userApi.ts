import axios from "axios"
import { LoginInfo } from "../types/user"
const BASE_URL = import.meta.env.VITE_BASE_URL

export const login = async (data : LoginInfo) : Promise<void> => {
    try {
        const response = await axios({
            method: "post",
            url: `${BASE_URL}/login`,
            data: {
                email: data.email,
                password: data.password
            }
        })
        return response.data
    } catch(error){
        console.log(error)
    }
}