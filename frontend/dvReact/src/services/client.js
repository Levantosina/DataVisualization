import axios from 'axios';

const getMeasurements = async () => {
    try {
        return await axios.get(`${import.meta.env.VITE_API_BASE_URL}/api/v1/measurements`)
    }
    catch (e){
        throw e;
    }
}

export default getMeasurements;
