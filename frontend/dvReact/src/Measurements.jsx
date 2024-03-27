import ChartComponent from './ChartComponent';
import { useEffect } from 'react';
import { getMeasurements } from "./services/client.js";

const Measurements = () => {

    useEffect(() => {
        getMeasurements()
            .then(res => {
                console.log(res);
            })
            .catch(err => {
                console.log(err);
            });
    }, []);

    return (
        <div className="App">
            <ChartComponent />
        </div>
    );
}

export default App;
