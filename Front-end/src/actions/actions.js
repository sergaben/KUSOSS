import { RESET_VALUES, AUTHORIZATION } from './types';
const resetValues = (nickname,matchPassword,password) =>{
    const undefinedValues = {nickname:'',matchPassword:'',password:''}
    return{
        type:RESET_VALUES,
        payload:undefinedValues
    }
}


export default resetValues;
