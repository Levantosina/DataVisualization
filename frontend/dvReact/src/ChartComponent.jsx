import React, { useEffect, useState } from 'react';
import ApexCharts from 'apexcharts';
import { getMeasurements } from './services/client.js';
import {errorNotification} from "./services/notification.js"; // Importing the API function

const ChartComponent = () => {
    const [measurements, setMeasurements] = useState([]);
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState("");

    const fetchMeasurements = async () => {
        setLoading(true);
        getMeasurements().then(res => {
            setMeasurements(res.data);
        }).catch(err => {
            setError(err.response.data.message)
            errorNotification(
                err.code
            )
        }).finally(() => {
            setLoading(false)
        })
    }

    useEffect(() => {
        fetchMeasurements();
    }, [])

    useEffect(() => {
        let chart = null;

        if (measurements.length > 0) {
            const options = {
                chart: {
                    type: 'line'
                },
                series: [{
                    name: 'Measurements',
                    data: measurements.map(measurement => measurement.current)
                }],
                xaxis: {
                    categories: measurements.map(measurement => measurement.timestamp)
                }
            };

            chart = new ApexCharts(document.querySelector("#chart"), options);
            chart.render();
        }

        // Cleanup function
        return () => {
            if (chart) {
                chart.destroy();
            }
        };
    }, [measurements]);

    if (loading) {
        return <div>Loading...</div>;
    }

    if (error) {
        return <div>Error: {error}</div>;
    }

    return <div id="chart" style={{ width: '100%', height: '400px' }}></div>;
}

export default ChartComponent;
