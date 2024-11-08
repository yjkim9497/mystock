import axios from "axios"
import { LoginInfo, SignupInfo } from "../types/user"
const BASE_URL = import.meta.env.VITE_BASE_URL

export const login = async (data: LoginInfo): Promise<void> => {
    try {
        const response = await axios({
            method: "post",
            url: `${BASE_URL}/user/login`,
            data: {
                email: data.email,
                password: data.password
            }
        })
        console.log(response)
        const accessToken = response.headers['authorization'];

        if (accessToken) {
            const token = accessToken.split(' ')[1];  // 'Bearer ' 부분을 제외하고 token만 추출
            console.log('Access Token:', token);

            // 토큰을 필요한 곳에 저장하거나 요청에 사용
            sessionStorage.setItem("accessToken", token)
        }

        return response.data
    } catch (error) {
        console.log(error)
    }
}

export const signup = async (data: SignupInfo): Promise<void> => {
    try {
        const response = await axios({
            method: 'post',
            url: `${BASE_URL}/user/signup`,
            data: {
                email: data.email,
                name: data.name,
                password: data.password
            }
        })
        return response.data
    } catch (error) {
        console.log(error)
    }
}